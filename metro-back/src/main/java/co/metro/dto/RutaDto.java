package co.metro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RutaDto {

    private List<EstacionDto> estaciones;

    public void addEstacion(EstacionDto estacion) {
        estaciones.add(estacion);
    }


    @Override
    public String toString() {
        StringBuilder s1 = new StringBuilder();
        estaciones.stream().forEach(n -> s1.append(n.getName()).append(" "));
        return s1.toString().substring(0, s1.length() - 1);
    }
}
