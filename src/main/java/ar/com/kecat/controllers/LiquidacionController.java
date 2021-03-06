package ar.com.kecat.controllers;

import ar.com.kecat.models.Tarjeta;
import ar.com.kecat.repositories.LiquidacionRepository;
import ar.com.kecat.repositories.TarjetaRepository;
import ar.com.kecat.tasks.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class LiquidacionController {

    @Autowired
    private LiquidacionRepository liquidacionRepository;
    @Autowired
    private TarjetaRepository tarjetaRepository;
    @Autowired
    private ScheduledTasks scheduledTasks;

    @GetMapping("/tarjetas/{idTarjeta}/liquidaciones")
    @ResponseBody ResponseEntity getLiquidacionesPorTarjeta(@PathVariable Long idTarjeta){
        Tarjeta tarjeta = tarjetaRepository.findOne(idTarjeta);
        if(tarjeta == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarjeta no válida");

        return ResponseEntity.status(HttpStatus.OK).body(liquidacionRepository.findByTarjeta(tarjeta));
    }

    @GetMapping("/liquidaciones/procesar")
    @ResponseBody ResponseEntity procesarLiquidaciones(){
        scheduledTasks.procesarLiquidaciones();
        return ResponseEntity.status(HttpStatus.OK).body("Liquidaciones procesadas correctamente");
    }

}
