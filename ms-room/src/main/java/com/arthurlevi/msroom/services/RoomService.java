package com.arthurlevi.msroom.services;

import com.arthurlevi.msroom.enums.StatusRoom;
import com.arthurlevi.msroom.exceptions.FindByNameException;
import com.arthurlevi.msroom.models.RoomModel;
import com.arthurlevi.msroom.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class    RoomService {

    final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomModel> findAll(){
        return roomRepository.findAll();
    }

    public RoomModel findById(UUID id){

        try {
            return roomRepository.findById(id).get();
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("Room not found");
        }
    }

    //adicionar validação por numero da Sala;
    public RoomModel created(RoomModel room){
        var validateName = roomRepository.findByName(room.getName());
        if(validateName == null) {
             roomRepository.save(room);
        }else {
            throw new FindByNameException("There is already a room registered with that name");
        }
        return room;
    }

    public RoomModel update(RoomModel roomUpdate){
        try {
            var room = roomRepository.findById(roomUpdate.getId()).orElse(null);
            methodForUpdate(roomUpdate, room);

            return roomRepository.save(room);
        }catch (NullPointerException e){
            throw new NullPointerException("Room not found");
        }
    }

    private void methodForUpdate(RoomModel roomUpdate,RoomModel room){
        if(roomUpdate.getCapacity() != null){
            room.setCapacity(roomUpdate.getCapacity());
        }
        if (roomUpdate.getName() != null){
            room.setName(roomUpdate.getName());
        }
        if (roomUpdate.getNumber() != null){
            room.setNumber(roomUpdate.getNumber());
        }
    }

    public RoomModel updateStatus(UUID id){
        try {
            RoomModel room = roomRepository.findById(id).orElse(null);

             switch (room.getStatusRoom()){
                case AVAILABLE -> room.setStatusRoom(StatusRoom.NOT_AVAILABLE);
                case NOT_AVAILABLE -> room.setStatusRoom(StatusRoom.AVAILABLE);
                default -> throw new IllegalStateException("Unexpected value: " + room.getStatusRoom());
        }
        return roomRepository.save(room);

        }catch (NullPointerException e){
            throw new NullPointerException("Room not found");
        }
    }

    public void delete(UUID id){
        roomRepository.findById(id)
                .orElseThrow(()-> new NullPointerException("Room not found"));

        roomRepository.deleteById(id);
    }

}
