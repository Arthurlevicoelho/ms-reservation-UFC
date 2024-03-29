package com.arthurlevi.msuser.repositories;

import com.arthurlevi.msuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByRegistration(Integer registration);

}
