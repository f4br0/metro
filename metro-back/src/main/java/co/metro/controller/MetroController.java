package co.metro.controller;

import co.metro.business.MetroBusinessBean;
import co.metro.dto.ConsultaHistorialDto;
import co.metro.dto.LoginRequestDto;
import co.metro.dto.RutaOptimaRequestDto;
import co.metro.dto.RutaOptimaResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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
