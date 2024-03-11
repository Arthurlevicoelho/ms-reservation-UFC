package com.arthurlevi.msroom.repositories;

import com.arthurlevi.msroom.models.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<RoomModel, UUID> {

    RoomModel findByName(String name);
}
