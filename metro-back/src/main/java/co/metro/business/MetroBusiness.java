package co.metro.business;

import co.metro.dto.ConsultaHistorialDto;
import co.metro.dto.RutaOptimaRequestDto;
import co.metro.dto.LoginRequestDto;
import co.metro.dto.RutaOptimaResponseDto;

import java.util.List;

public interface MetroBusiness {

    List<RutaOptimaResponseDto> calcularRutaOptima(RutaOptimaRequestDto request);

    List<ConsultaHistorialDto> consultarHistorial();

    Boolean login(LoginRequestDto request);
}
