package com.arthurlevi.msreservations.repostories;

import com.arthurlevi.msreservations.models.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<ReservationModel, UUID> {

    List<ReservationModel> findByIdUser(UUID idUser);
    List<ReservationModel> findByIdRoom(UUID idRoom);
}
