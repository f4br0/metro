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

    @Override
    public List<RutaOptimaResponseDto> calcularRutaOptima(RutaOptimaRequestDto requestDto) {
        historialRepository.save(new Historial(requestDto.getOrigen(), requestDto.getDestino()));
        List<RutaDto> rutas = new ArrayList<>();
        obtenerRutas(requestDto.getOrigen(),
                requestDto.getDestino(),
                new RutaDto(new ArrayList<>()),
                rutas,
                buildRutaMetro());
        //se orden las rutas de menos a mayor para obtener las mas optimas
        Collections.sort(rutas, (RutaDto p1, RutaDto p2) -> {
            return p1.getEstaciones().size() - p2.getEstaciones().size();
        });


        List<RutaOptimaResponseDto> opciones = new ArrayList<>();
        rutas.subList(0, rutas.size() > 1 ? 2 : rutas.size()).stream().forEach(g -> {
            StringBuilder detalle = new StringBuilder();
            detalle.append("Opcion " + (opciones.size() < 1 ? "1" : "2") + " -> ");
            String descripcionRuta = g.toString();
            String ruta = descripcionRuta;
            String rutaTpm = descripcionRuta;
            while (rutaTpm.length() > 0) {
                String rutaBus = buscarRuta(ruta);
                if (!RutaEnum.RUTA0.name().equals(rutaBus)) {
                    rutaTpm = rutaTpm.substring(ruta.length());
                    detalle.append(rutaBus + ": " + ruta + (rutaTpm.length() > 0 ? " + " : ""));
                    ruta = rutaTpm;
                } else
                    ruta = ruta.substring(0, ruta.length() - 2);
            }
            String opcion = detalle.toString();
            int tiempoEstimado = ((descripcionRuta.split("\\ ").length) * 4) - 4
                    + ((opcion.split("\\+").length * 2) - 2);
            opciones.add(new RutaOptimaResponseDto(opcion, tiempoEstimado));
        })
        ;
        return opciones;
    }

    @Override
    public List<ConsultaHistorialDto> consultarHistorial() {

        Map<String, Long> result = historialRepository.findAll().stream().map(h -> h.toString()).collect(
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

        finalMap.entrySet().stream().forEach(h -> {
            historial.add(new ConsultaHistorialDto(h.getKey(), h.getValue()));
        });
        return historial;
    }

    @Override
    public Boolean login(LoginRequestDto request) {
        return userRepository
                .findOneByUsuarioAndClave(
                        request.getUsuario(),
                        request.getClave()) != null
                ? true
                : false;
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

    private List<EstacionDto> buildRutaMetro() {
        List<EstacionDto> estacionesMetro = new ArrayList<>();
        EstacionDto estacion1 = new EstacionDto("1");
        EstacionDto estacion2 = new EstacionDto("2");
        EstacionDto estacion3 = new EstacionDto("3");
        EstacionDto estacion4 = new EstacionDto("4");
        EstacionDto estacion5 = new EstacionDto("5");
        EstacionDto estacion6 = new EstacionDto("6");
        EstacionDto estacion7 = new EstacionDto("7");
        EstacionDto estacion8 = new EstacionDto("8");
        EstacionDto estacion9 = new EstacionDto("9");
        EstacionDto estacion10 = new EstacionDto("10");
        EstacionDto estacion11 = new EstacionDto("11");
        EstacionDto estacion12 = new EstacionDto("12");
        EstacionDto estacion13 = new EstacionDto("13");
        EstacionDto estacion14 = new EstacionDto("14");
        EstacionDto estacion15 = new EstacionDto("15");

        estacion1.addDestination(estacion2);
        estacion10.addDestination(estacion9);
        estacion12.addDestination(estacion11);
        estacion12.addDestination(estacion13);

        estacion2.addDestination(estacion1);
        estacion2.addDestination(estacion11);
        estacion2.addDestination(estacion3);
        estacion11.addDestination(estacion2);
        estacion11.addDestination(estacion12);
        estacion11.addDestination(estacion7);

        estacion3.addDestination(estacion2);
        estacion3.addDestination(estacion7);
        estacion9.addDestination(estacion10);
        estacion9.addDestination(estacion8);
        estacion9.addDestination(estacion14);
        estacion13.addDestination(estacion12);
        estacion13.addDestination(estacion14);

        estacion4.addDestination(estacion7);
        estacion4.addDestination(estacion5);
        estacion7.addDestination(estacion3);
        estacion7.addDestination(estacion4);
        estacion7.addDestination(estacion8);
        estacion7.addDestination(estacion11);
        estacion14.addDestination(estacion9);
        estacion14.addDestination(estacion13);
        estacion14.addDestination(estacion15);

        estacion5.addDestination(estacion4);
        estacion5.addDestination(estacion6);
        estacion6.addDestination(estacion5);
        estacion8.addDestination(estacion7);
        estacion8.addDestination(estacion9);
        estacion15.addDestination(estacion14);

        estacionesMetro.add(estacion1);
        estacionesMetro.add(estacion2);
        estacionesMetro.add(estacion3);
        estacionesMetro.add(estacion4);
        estacionesMetro.add(estacion5);
        estacionesMetro.add(estacion6);
        estacionesMetro.add(estacion7);
        estacionesMetro.add(estacion8);
        estacionesMetro.add(estacion9);
        estacionesMetro.add(estacion10);
        estacionesMetro.add(estacion11);
        estacionesMetro.add(estacion12);
        estacionesMetro.add(estacion13);
        estacionesMetro.add(estacion14);
        estacionesMetro.add(estacion15);
        return estacionesMetro;
    }
}
