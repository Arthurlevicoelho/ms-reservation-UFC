package com.arthurlevi.msreservations.controllers;

import com.arthurlevi.msreservations.dtos.ReservationRecordDto;
import com.arthurlevi.msreservations.models.ReservationModel;
import com.arthurlevi.msreservations.services.ReservationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

//    private static Logger logger = LoggerFactory.getLogger(ReservationController.class);
//
//    @Value("${test.config}")
//    private String testConfig;

    final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/configs")
    public ResponseEntity<Void> findConfig(){
 //       logger.info("CONFIG=" + testConfig);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<ReservationModel>> findAll(){
        List<ReservationModel> reservations = reservationService.findAll();
        return ResponseEntity.ok().body(reservations);
    }

    @PostMapping("/")
    public ResponseEntity created(@RequestBody ReservationRecordDto reservation){
        var reservationModel = new ReservationModel();

        BeanUtils.copyProperties(reservation,reservationModel);

        var reservationCreated = reservationService.created(reservationModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity findByIdRoom(@PathVariable UUID id){

        var listByIdRoom = this.reservationService.findByIdRoom(id);

        return ResponseEntity.ok().body(listByIdRoom);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReservation(@PathVariable UUID id){
        this.reservationService.deleteById(id);//logica para deletar reserva

        return ResponseEntity.noContent().build();
    }
}
