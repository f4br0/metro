package co.metro.controller;

import co.metro.business.MetroBusinessBean;
import co.metro.dto.ConsultaHistorialDto;
import co.metro.dto.LoginRequestDto;
import co.metro.dto.RutaOptimaRequestDto;
import co.metro.dto.RutaOptimaResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class MetroController {

    @Autowired
    private MetroBusinessBean metroBusinessBean;


    @PostMapping("/generar-ruta-optima")
    public List<RutaOptimaResponseDto> rutaOptima(@RequestBody RutaOptimaRequestDto request) {
        return metroBusinessBean.calcularRutaOptima(request);
    }

    @GetMapping("/consultar-hitorial")
    public List<ConsultaHistorialDto> consultarHistorial() {
        return metroBusinessBean.consultarHistorial();
    }

    @PostMapping("/login")
    public Boolean login(@RequestBody LoginRequestDto request) {
        return metroBusinessBean.login(request);
    }
}
