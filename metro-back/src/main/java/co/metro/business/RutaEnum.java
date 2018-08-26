package co.metro.business;

import lombok.Getter;

@Getter
public enum RutaEnum {
    RUTA0(""),
    RUTA_A("1 2 3 7 4 5 6 5 4 7 3 2 1"),
    RUTA_B("1 2 11 12 13 14 9 10 9 14 13 12 11 2 1"),
    RUTA_C("1 2 3 7 11 12 13 14 15 14 13 12 11 7 3 2 1"),
    RUTA_D("10 9 8 7 11 7 8 9 10"),
    RUTA_E("6 5 4 7 8 9 14 15 14 9 8 7 4 5 6"),
    RUTA_F("2 3 7 8 9 14 13 12 11 2 11 12 13 14 9 8 7 3 2");

    private final String descripcion;

    RutaEnum(String s) {
        this.descripcion = s;
    }
}
