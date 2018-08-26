package co.metro.business;

import co.metro.dto.*;
import co.metro.model.Historial;
import co.metro.repository.HistorialRepository;
import co.metro.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MetroBusinessBeanTest {

    @InjectMocks
    private MetroBusinessBean metroBusinessBean;
    @Mock
    private HistorialRepository historialRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MapaMetroDto mapaMetro;

    @Test
    public void calcularRutaOptima() {
        Mockito.when(historialRepository.save(Mockito.any())).thenReturn(new Historial());
        Mockito.when(mapaMetro.getMapa()).thenReturn(new MapaMetroDto().getMapa());
        RutaOptimaRequestDto req = new RutaOptimaRequestDto();
        req.setOrigen("1");
        req.setDestino("10");
        RutaOptimaResponseDto result = metroBusinessBean.calcularRutaOptima(req).get(0);
        Assert.assertEquals("Opcion 1 -> RUTA_B: 1 2 11 + RUTA_D: 11 7 8 9 10", result.getDetalle());
        Assert.assertEquals(26, result.getTiempoEstimado());
    }

    @Test
    public void consultarHistorial() {
        List<Historial> historial = new ArrayList<>();
        Historial historial1 = new Historial();
        historial1.setId(1L);
        historial1.setOrigen("1");
        historial1.setDestino("2");
        historial.add(historial1);
        Mockito.when(historialRepository.findAll()).thenReturn(historial);
        ConsultaHistorialDto result = metroBusinessBean.consultarHistorial().get(0);
        Assert.assertEquals("1/2", result.getViaje());
        Assert.assertEquals(new Long("1"), result.getConsultado());

    }

    @Test
    public void login() {
//        Mockito.when(userRepository.findOneByUsuarioAndClave(Mockito.any(), Mockito.any()))
//                .thenReturn(new User());
        LoginRequestDto request = new LoginRequestDto("admin", "admin");
        Assert.assertEquals(true, metroBusinessBean.login(request));
    }
}