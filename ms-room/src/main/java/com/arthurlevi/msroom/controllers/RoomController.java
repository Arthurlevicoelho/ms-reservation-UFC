package com.arthurlevi.msroom.controllers;

import com.arthurlevi.msroom.dtos.RoomRecordDto;
import com.arthurlevi.msroom.enums.StatusRoom;
import com.arthurlevi.msroom.models.RoomModel;
import com.arthurlevi.msroom.services.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    @Operation(summary = "Retorna todas as salas")

    public ResponseEntity findAll() {

        List<RoomModel> rooms = roomService.findAll();
        return ResponseEntity.ok().body(rooms);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma sala por ID")
    public ResponseEntity<RoomModel> findRoomById(@PathVariable UUID id){

        var room =roomService.findById(id);

        return ResponseEntity.ok(room);
    }

    @PostMapping("/created")
    @Operation(summary = "Cria uma nova sala")

    public ResponseEntity<RoomModel> created(@RequestBody @Valid RoomRecordDto room){
        var roomModel = new RoomModel();

        BeanUtils.copyProperties(room, roomModel);
        roomModel.setStatusRoom(StatusRoom.AVAILABLE);

        roomModel = roomService.created(roomModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(roomModel);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza uma sala por ID")
    public ResponseEntity<Void> updateRoom(@PathVariable UUID id,@RequestBody RoomRecordDto room){
        var roomModel = new RoomModel();
        BeanUtils.copyProperties(room, roomModel);

        roomModel.setId(id);
        roomService.update(roomModel);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta uma sala por ID")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID id){
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/changingStatus/{id}")
    @Operation(summary = "Altera o status da sala")
    public ResponseEntity changingStatus(@PathVariable UUID id){

        roomService.updateStatus(id);
        return ResponseEntity.noContent().build();

    }

}
