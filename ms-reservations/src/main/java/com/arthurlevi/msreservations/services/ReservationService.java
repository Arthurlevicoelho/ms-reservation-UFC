package com.arthurlevi.msreservations.services;


import com.arthurlevi.msreservations.dtos.RoomDto;
import com.arthurlevi.msreservations.dtos.UserDto;
import com.arthurlevi.msreservations.enums.StatusRoom;
import com.arthurlevi.msreservations.exceptions.ReservationNotFoundException;
import com.arthurlevi.msreservations.exceptions.UnavailableTimeException;
import com.arthurlevi.msreservations.exceptions.UserOrRoomNotFoundException;
import com.arthurlevi.msreservations.feignclients.RoomFeignClient;
import com.arthurlevi.msreservations.feignclients.UserFeignClient;
import com.arthurlevi.msreservations.models.ReservationModel;
import com.arthurlevi.msreservations.repostories.ReservationRepository;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

//    private static Logger logger = LoggerFactory.getLogger(ReservationService.class);
//
//    public static final String RESERVATION_ROOM ="reservationRoom";
    final ReservationRepository reservationRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private RoomFeignClient roomFeignClient;

    public ReservationService(ReservationRepository reservationRepository, UserFeignClient userFeignClient, RoomFeignClient roomFeignClient) {
        this.reservationRepository = reservationRepository;
        this.userFeignClient = userFeignClient;

        this.roomFeignClient = roomFeignClient;
    }

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationModel> findAll() {
        return reservationRepository.findAll();
    }

    public ReservationModel created(ReservationModel reservation) {

        validationUserAndRoom(reservation);
        testValidateRoom(reservation);
        var checkReservationsMade = checkingReservationSchedule(reservation);
        var now = checkingFromNow(reservation);

        if((!checkReservationsMade && now) || (checkReservationsMade && !now) || !(checkReservationsMade && now)) {

            throw new UnavailableTimeException("Schedule not available");

        }
        return reservationRepository.save(reservation);

    }

    public void testValidateRoom(ReservationModel reservation){

        RoomDto room = roomFeignClient.findRoomById(reservation.getIdRoom()).getBody();
        if(room.getStatusRoom() == StatusRoom.NOT_AVAILABLE){
            throw new UserOrRoomNotFoundException("Room not available");
        }


    }

 //   @CircuitBreaker(name = RESERVATION_ROOM, fallbackMethod = "alternativeMethod")
    public void validationUserAndRoom(ReservationModel reservation){

        UserDto userDto = userFeignClient.findUserById(reservation.getIdUser()).getBody();
        RoomDto roomDto = roomFeignClient.findRoomById(reservation.getIdRoom()).getBody();


        if(userDto == null || roomDto == null){
            throw new UserOrRoomNotFoundException();
        }

    }


    public Boolean checkingFromNow(ReservationModel reservation) {

        LocalDateTime now = LocalDateTime.now();
        if(reservation.getStartAt().isAfter(now) && reservation.getEndAt().isAfter(now)
                && reservation.getStartAt().isBefore(reservation.getEndAt())
                && !reservation.getStartAt().equals(reservation.getEndAt())) {

            return true;

        } else {
            return false;
        }
    }

    public Boolean checkingReservationSchedule(ReservationModel reservation) {

        List<ReservationModel> rooms = this.reservationRepository.findByIdRoom(reservation.getIdRoom());
        for (ReservationModel reservationByRoom : rooms) {//Conferindo se a hora já nao esta reservada no sistema
            var start = reservationByRoom.getStartAt();
            var end = reservationByRoom.getEndAt();

            if ((reservation.getStartAt().isAfter(start) && reservation.getStartAt().isBefore(end)) ||
                    (reservation.getEndAt().isAfter(start) && reservation.getEndAt().isBefore(end)) ||
                    (reservation.getStartAt().equals(start) || reservation.getEndAt().equals(end))) {

                return false;

            }

        }return true;
    }
/*
   public ReservationModel alternativeMethod(ReservationModel reservation, Exception e){

       logger.error("Error:", e);

       ReservationModel reservationModel = new ReservationModel(null,null,null,null,null);
       return reservationModel;

    }
*/
    public List<ReservationModel> findByIdRoom(UUID idRoom){

        return this.reservationRepository.findByIdRoom(idRoom);
    }

    public void deleteById(UUID id){

        reservationRepository
                .findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        this.reservationRepository.deleteById(id);
    }

}
