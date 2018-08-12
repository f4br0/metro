package co.metro.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EstacionDto {

    private String name;
    private List<EstacionDto> estacionesProximas = new ArrayList<>();

    public EstacionDto(String name) {
        this.name = name;
    }

    public void addDestination(EstacionDto destination) {
        estacionesProximas.add(destination);
    }


}
