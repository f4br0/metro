package co.metro.business;

import co.metro.dto.*;
import co.metro.enums.RutaEnum;
import co.metro.model.Historial;
import co.metro.repository.HistorialRepository;
import co.metro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MetroBusinessBean implements MetroBusiness {

    @Autowired
    private HistorialRepository historialRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapaMetroDto mapaMetro;

    @Override
    public List<RutaOptimaResponseDto> calcularRutaOptima(RutaOptimaRequestDto requestDto) {
        historialRepository.save(new Historial(requestDto.getOrigen(), requestDto.getDestino()));
        List<RutaDto> rutas = new ArrayList<>();

        obtenerRutas(requestDto.getOrigen(),
                requestDto.getDestino(),
                new RutaDto(new ArrayList<>()),
                rutas,
                mapaMetro.getMapa());

        //se orden las rutas de menos a mayor para obtener las mas optimas
        Collections.sort(rutas, (RutaDto p1, RutaDto p2) -> {
            return p1.getEstaciones().size() - p2.getEstaciones().size();
        });

        List<RutaOptimaResponseDto> opciones = new ArrayList<>();
        // se escogen las 2 mejores rutas y se contruye su detalle
        rutas.subList(0, rutas.size() > 1 ? 2 : rutas.size()).stream().forEach(g -> {
            StringBuilder detalle = new StringBuilder();
            detalle.append("Opcion " + (opciones.isEmpty() ? "1" : "2") + " -> ");
            String rutaCompleta = g.toString();
            String ruta = rutaCompleta;
            String rutaTpm = rutaCompleta;
            while (rutaTpm.length() > 2) {
                String rutaBus = buscarRuta(ruta);
                if (!RutaEnum.RUTA0.name().equals(rutaBus)) {
                    rutaTpm = rutaTpm.substring(ruta.length() - 2).trim();
                    detalle.append(rutaBus + ": " + ruta + (rutaTpm.length() > 2 ? " + " : ""));
                    ruta = rutaTpm;
                } else
                    ruta = ruta.substring(0, ruta.length() - 2).trim();
            }
            String opcion = detalle.toString();
            int tiempoEntreEstaciones = ((rutaCompleta.split("\\ ").length) * 4) - 4;
            int tiempoPorCambioRuta = (opcion.split("\\+").length * 2) - 2;
            int tiempoEstimado = tiempoEntreEstaciones + tiempoPorCambioRuta;
            opciones.add(new RutaOptimaResponseDto(opcion, tiempoEstimado));
        })
        ;
        return opciones;
    }

    @Override
    public List<ConsultaHistorialDto> consultarHistorial() {

        Map<String, Long> result = historialRepository.findAll().stream().map(Historial::toString).collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        );

        Map<String, Long> finalMap = new LinkedHashMap<>();

        //Sort a map and add to finalMap
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
        List<ConsultaHistorialDto> historial = new ArrayList<>();

        finalMap.entrySet().stream().forEach(h ->
                historial.add(new ConsultaHistorialDto(h.getKey(), h.getValue()))
        );
        return historial;
    }

    @Override
    public Boolean login(LoginRequestDto request) {
        return userRepository
                .findOneByUsuarioAndClave(
                        request.getUsuario(),
                        request.getClave()) != null
                ;
    }

    private String buscarRuta(String ruta) {
        return Arrays.stream(RutaEnum.values())
                .filter(r -> r.getDescripcion().contains(ruta))
                .findFirst().orElse(RutaEnum.RUTA0).name();
    }

    private void obtenerRutas(String actual,
                              String destino,
                              RutaDto recorrido,
                              List<RutaDto> rutas,
                              List<EstacionDto> estacionesMetro) {
        if (actual.equals(destino)) {
            recorrido.addEstacion(new EstacionDto(destino));
            rutas.add(recorrido);
            return;
        } else if (recorrido.getEstaciones().stream().anyMatch(e -> e.getName().equals(actual)))
            return;
        recorrido.addEstacion(new EstacionDto(actual));
        estacionesMetro.stream().filter(e -> e.getName().equals(actual)).findFirst().orElse(new EstacionDto(""))
                .getEstacionesProximas().stream()
                .forEach(n -> obtenerRutas(
                        n.getName(),
                        destino,
                        new RutaDto(new ArrayList<>(recorrido.getEstaciones())),
                        rutas,
                        estacionesMetro
                ));

    }

}
