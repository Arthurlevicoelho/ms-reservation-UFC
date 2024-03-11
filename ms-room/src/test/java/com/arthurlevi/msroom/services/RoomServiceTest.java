package com.arthurlevi.msroom.services;

import com.arthurlevi.msroom.enums.StatusRoom;
import com.arthurlevi.msroom.exceptions.FindByNameException;
import com.arthurlevi.msroom.models.RoomModel;
import com.arthurlevi.msroom.repositories.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;


class RoomServiceTest {

    @InjectMocks
    RoomService roomService;
    @Mock
    RoomRepository roomRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        roomService = new RoomService(roomRepository);
    }

    @Test
    void findAllCase1() {

        List<RoomModel> roomModelList = Arrays.asList(
                new RoomModel(UUID.randomUUID(),"Sala dos professores",10,15, StatusRoom.AVAILABLE),
                new RoomModel(UUID.randomUUID(),"Sala do intervalo",12,60,StatusRoom.NOT_AVAILABLE)
        );

        Mockito.when(roomRepository.findAll()).thenReturn(roomModelList);

        List<RoomModel> result = roomService.findAll();

        assertEquals(roomModelList,result);
    }

    @Test
    void findAllCase2() {

        Mockito.when(roomRepository.findAll()).thenReturn(Arrays.asList());

        List<RoomModel> result = roomService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void findByIdCase1() {

        RoomModel room = new RoomModel(UUID.randomUUID(),"Sala dos professores",10,15, StatusRoom.AVAILABLE);

        when(roomRepository.findById(room.getId())).thenReturn(Optional.of(room));

        RoomModel result = roomService.findById(room.getId());

        assertNotNull(result);
        assertEquals(room,result);
    }
    @Test
    void findByIdCase2() {

        UUID randomUUID = UUID.randomUUID();

        when(roomRepository.findById(any(UUID.class))).thenReturn(Optional.empty());


        assertThrows(NoSuchElementException.class, () -> roomService.findById(randomUUID));
    }

    @Test
    void createdCase1() {
        RoomModel room = new RoomModel(UUID.randomUUID(),"Sala dos professores",10,15, StatusRoom.AVAILABLE);

        when(roomRepository.findByName(room.getName())).thenReturn(null);

        RoomModel result = roomService.created(room);

        verify(roomRepository,times(1)).save(room);
        assertThat(result.equals(room)).isTrue();
    }
    @Test
    void createdCase2() {
        RoomModel room = new RoomModel(UUID.randomUUID(),"Sala dos professores",10,15, StatusRoom.AVAILABLE);

        when(roomRepository.findByName(room.getName())).thenReturn(room);

        verify(roomRepository,never()).save(room);
        assertThrows(FindByNameException.class, () -> roomService.created(room));
    }

    @Test
    void updateCase1() {

        RoomModel room = new RoomModel(UUID.randomUUID(),"Sala dos professores",10,15, StatusRoom.AVAILABLE);

        when(roomRepository.findById(room.getId())).thenReturn(Optional.of(room));
        when(roomRepository.save(room)).thenReturn(room);
        RoomModel roomUpdate = new RoomModel(room.getId(),"Sala de estudos",10,60, StatusRoom.AVAILABLE);


        when(roomService.update(roomUpdate)).thenReturn(roomUpdate);
        RoomModel result = roomService.update(roomUpdate);

        verify(roomRepository, times(2)).findById(room.getId());
        verify(roomRepository, times(1)).save(room);

        assertNotNull(result);
        assertEquals(room.getId(), result.getId());
        assertEquals(room.getName(), result.getName());
        assertEquals(room.getCapacity(), result.getCapacity());
    }
    @Test
    void updateCase2() {

        RoomModel room = new RoomModel(UUID.randomUUID(),"Sala dos professores",10,15, StatusRoom.AVAILABLE);

        when(roomRepository.findById(room.getId())).thenReturn(null);
        when(roomRepository.save(room)).thenReturn(room);

        assertThrows(NullPointerException.class,() -> roomService.update(room));
    }

    @Test
    void updateStatusCase1() {
        RoomModel room = new RoomModel(UUID.randomUUID(),"Sala dos professores",10,15, StatusRoom.AVAILABLE);

        when(roomRepository.findById(room.getId())).thenReturn(Optional.of(room));
        when(roomRepository.save(room)).thenReturn(room);

        RoomModel result = roomService.updateStatus(room.getId());

        assertEquals(StatusRoom.NOT_AVAILABLE,result.getStatusRoom());
    }

    @Test
    void updateStatusCase2() {
        RoomModel room = new RoomModel(UUID.randomUUID(),"Sala dos professores",10,15, StatusRoom.AVAILABLE);

        when(roomRepository.findById(any())).thenReturn(null);

        assertThrows(NullPointerException.class, () -> roomService.updateStatus(any()));
    }


    @Test
    void deleteCase1() {

        RoomModel room = new RoomModel(UUID.randomUUID(),"Sala dos professores",10,15, StatusRoom.AVAILABLE);

        when(roomRepository.findById(room.getId())).thenReturn(Optional.of(room));

        roomService.delete(room.getId());

        verify(roomRepository,times(1)).findById(room.getId());
        verify(roomRepository,times(1)).deleteById(room.getId());
    }

    @Test
    void deleteCase2() {

        RoomModel room = new RoomModel(UUID.randomUUID(),"Sala dos professores",10,15, StatusRoom.AVAILABLE);

        when(roomRepository.findById(room.getId())).thenReturn(null);

        assertThrows(NullPointerException.class, () -> roomService.delete(room.getId()));
        verify(roomRepository,never()).deleteById(room.getId());
    }

}