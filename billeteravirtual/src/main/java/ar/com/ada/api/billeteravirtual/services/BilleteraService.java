package ar.com.ada.api.billeteravirtual.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.Billetera;
import ar.com.ada.api.billeteravirtual.entities.Usuario;
import ar.com.ada.api.billeteravirtual.repo.BilleteraRepository;

/**
 * BilleteraService
 */
@Service
public class BilleteraService {

    @Autowired
    BilleteraRepository billeteraRepository;
    @Autowired
    UsuarioService usuarioService;

    public Billetera buscarPorId(Integer id) {
        Optional<Billetera> b = billeteraRepository.findById(id);
        if (b.isPresent())
            return b.get();
        return null;

    }

   /* public void crearTransferencia(Double importe, Integer billeteraIdOrigen, String emailDestino) {
        Billetera b = billeteraRepository.findById(billeteraIdOrigen).get();
        Usuario u = usuarioService.buscarPorEmail(emailDestino);
        u.getPersona().getBilletera();
        b.movimientoTransferencia(importe, b.getPersona().getUsuario(), u);
        billeteraRepository.save(b);
        billeteraRepository.save(u.getPersona().getBilletera());

    }*/
    public void transferencia (Billetera deBilletera, String email ,BigDecimal plata, String moneda, String concepto, String detalle){
        Billetera aBilletera;

        aBilletera = usuarioService.buscarPorEmail(email).getPersona().getBilletera();
        deBilletera.movimientoTransferencia(concepto,moneda,
        detalle);
    }

    public double consultarSaldo(Integer billeteraId) {
        Billetera b = billeteraRepository.findById(billeteraId).get();
        return b.cuentaPrincipal().getSaldo();
    }

    public Billetera save(Billetera b){
        return billeteraRepository.save(b);
    }
}
