package ar.com.kecat.controllers;

import ar.com.kecat.models.Liquidacion;
import ar.com.kecat.models.Tarjeta;
import ar.com.kecat.repositories.LiquidacionRepository;
import ar.com.kecat.repositories.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
public class LiquidacionController {

    @Autowired
    private LiquidacionRepository liquidacionRepository;
    @Autowired
    private TarjetaRepository tarjetaRepository;

    @GetMapping("/liquidaciones")
    @ResponseBody ResponseEntity<List<Liquidacion>> getLiquidaciones(){
        List<Liquidacion> liquidaciones = new ArrayList<>();
        liquidacionRepository.findAll().forEach(liquidaciones::add);
        return ResponseEntity.status(HttpStatus.OK).body(liquidaciones);
    }

    @GetMapping("/tarjetas/{idTarjeta}/liquidaciones")
    @ResponseBody ResponseEntity getLiquidacionesPorTarjeta(@PathVariable Long idTarjeta){
        Tarjeta tarjeta = tarjetaRepository.findOne(idTarjeta);
        if(tarjeta == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarjeta no válida");

        return ResponseEntity.status(HttpStatus.OK).body(liquidacionRepository.findByTarjeta(tarjeta));
    }

}
