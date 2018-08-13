package co.metro.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class MapaMetroDto {

    private List<EstacionDto> mapa;

    public MapaMetroDto() {
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

        this.mapa = estacionesMetro;
    }
}
