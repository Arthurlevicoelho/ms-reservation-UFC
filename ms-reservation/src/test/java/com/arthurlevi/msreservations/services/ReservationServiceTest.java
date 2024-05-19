package com.arthurlevi.msreservations.services;

import com.arthurlevi.msreservations.dtos.RoomDto;
import com.arthurlevi.msreservations.dtos.UserDto;
import com.arthurlevi.msreservations.enums.StatusRoom;
import com.arthurlevi.msreservations.models.ReservationModel;
import com.arthurlevi.msreservations.repostories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {


    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    ReservationService reservationService;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
//        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    void findAll() {
        RoomDto room = new RoomDto(UUID.randomUUID(),"Sala dos professores",10,15, StatusRoom.AVAILABLE);
        UserDto user = new UserDto(UUID.randomUUID(), "arthurlevi1@gmail.com", "12342321", 123456);

        List<ReservationModel> reservationModelList = Arrays.asList(
                new ReservationModel(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now().plusHours(2),room.getId(),user.getId()),
                new ReservationModel(UUID.randomUUID(), LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(6),room.getId(),user.getId())
        );

        Mockito.when(reservationRepository.findAll()).thenReturn(reservationModelList);

        List<ReservationModel> result = reservationService.findAll();

        assertNotNull(result);
        assertEquals(reservationModelList,result);
        assertEquals(ReservationModel.class,result.get(0).getClass());
    }

    @Test
    void created() {
    }

    @Test
    void testValidateRoom() {
    }

    @Test
    void validationUserAndRoom() {
    }

    @Test
    void checkingFromNow() {
    }

    @Test
    void checkingReservationSchedule() {
    }

    @Test
    void findByIdRoom() {
    }

    @Test
    void deleteById() {
    }
}