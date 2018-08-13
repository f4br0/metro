package co.metro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RutaOptimaResponseDto {
    private String detalle;
    private int tiempoEstimado;

}

