package com.arthurlevi.msuser.repositories;

import com.arthurlevi.msuser.dtos.UserRecordDto;
import com.arthurlevi.msuser.models.UserModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get user Successfully from DB")
    void findByEmailCase1() {
        String email = "arthurlevi@gmailcom";

        UserRecordDto userRecordDto = new UserRecordDto(email,"234556",233455);
        createUser(userRecordDto);
        Optional<UserModel> user = this.userRepository.findByEmail(email);
        assertThat(user.isPresent()).isTrue();
    }
    @Test
    @DisplayName("Should not user Successfully from DB")
    void findByEmailCase2() {
        String email = "arthurlevi@gmailcom";

        Optional<UserModel> user = this.userRepository.findByEmail(email);
        assertThat(user.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should get user Successfully from DB")
    void findByRegistrationCase1() {
        Integer resgistration = 233455;

        UserRecordDto userRecordDto = new UserRecordDto("arthurlevi@gmailcom","234556",resgistration);
        createUser(userRecordDto);
        Optional<UserModel> user = this.userRepository.findByRegistration(resgistration);
        assertThat(user.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not user Successfully from DB")
    void findByRegistrationCase2() {
        Integer resgistration = 233455;

        Optional<UserModel> user = this.userRepository.findByRegistration(resgistration);
        assertThat(user.isEmpty()).isTrue();
    }

    private UserModel createUser(UserRecordDto userDto){
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        this.entityManager.persist(userModel);
        return userModel;

    }
}