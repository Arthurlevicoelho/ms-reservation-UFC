package com.arthurlevi.msuser.services;


import com.arthurlevi.msuser.exceptions.CreateUserException;
import com.arthurlevi.msuser.exceptions.UserPrincipalNotFoundException;
import com.arthurlevi.msuser.models.UserModel;
import com.arthurlevi.msuser.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class UserServiceTest {

    @InjectMocks
    private UserService userService;


    @Mock
    private UserRepository repository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        userService = new UserService(repository);
    }

    @Test
    void findByIdCase1() {
        UUID userId = UUID.randomUUID();
        UserModel user = new UserModel(userId,"arthurlevicoelho@gmail.com","aqwweer",123456);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        UserModel userTest = userService.findById(userId);

        assertEquals(user,userTest);
    }

    @Test
    void findByIdCase2() {
        UUID userId = UUID.randomUUID();
        UserModel user = new UserModel(userId,"arthurlevicoelho@gmail.com","aqwweer",123456);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        //assertNotEquals(userId,UUID.randomUUID());
        assertThrows(UserPrincipalNotFoundException.class,() -> userService.findById(UUID.randomUUID()));
    }

    @Test
    void findAllCase1() {

        List<UserModel> userList = Arrays.asList(
                new UserModel(UUID.randomUUID(), "arthurlevi1@gmail.com", "12342321", 123456),
                new UserModel(UUID.randomUUID(), "arthurlevi2@gmail.com", "90848293812", 789012)
        );

        when(repository.findAll()).thenReturn(userList);

        List<UserModel> result = userService.findAll();

        assertEquals(userList, result);
    }

    @Test
    void findAllCase2() {
        List<UserModel> userList = Arrays.asList(
                new UserModel(UUID.randomUUID(), "arthurlevi1@gmail.com", "12342321", 123456),
                new UserModel(UUID.randomUUID(), "arthurlevi2@gmail.com", "90848293812", 789012)
        );

        List<UserModel> result = userService.findAll();

        assertNotEquals(userList, result);
    }

    @Test
    void createdCase1() {
        UserModel user1 = new UserModel(UUID.randomUUID(), "arthurlevi1@gmail.com", "12342321", 123456);

        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(repository.findByRegistration(anyInt())).thenReturn(Optional.empty());
        when(repository.save(user1)).thenReturn(user1);

        UserModel createdUser = userService.created(user1);

        assertNotNull(createdUser);
        assertEquals(user1.getEmail(), createdUser.getEmail());
        assertEquals(user1.getRegistration(), createdUser.getRegistration());
        verify(repository, times(1)).save(user1);
    }

    @Test
    void createdCase2() {
        UserModel user1 = new UserModel(UUID.randomUUID(), "arthurlevi1@gmail.com", "12342321", 123456);

        when(repository.findByEmail(user1.getEmail())).thenReturn(Optional.of(user1));
        when(repository.findByRegistration(user1.getRegistration())).thenReturn(Optional.of(user1));

        assertThrows(CreateUserException.class, () -> userService.created(user1));

        verify(repository, Mockito.never()).save(any(UserModel.class));
    }

    @Test
    void findByRegistrationCase1() {
        UserModel user1 = new UserModel(UUID.randomUUID(), "arthurlevi1@gmail.com", "12342321", 123456);

        when(repository.findByRegistration(user1.getRegistration())).thenReturn(Optional.of(user1));

        UserModel repost = userService.findByRegistration(user1.getRegistration());
        assertEquals(user1,repost);
    }

    @Test
    void findByRegistrationCase2() {
        when(repository.findByRegistration(anyInt())).thenReturn(Optional.empty());
        assertThrows(UserPrincipalNotFoundException.class, () -> userService.findByRegistration(anyInt()));
    }

    @Test
    void deleteUserCase1() {
        UserModel user1 = new UserModel(UUID.randomUUID(), "arthurlevi1@gmail.com", "12342321", 123456);

        when(repository.findById(user1.getId())).thenReturn(Optional.of(user1));

        userService.deleteUser(user1.getId());

        verify(repository, times(1)).findById(user1.getId());
        verify(repository,times(1)).deleteById(user1.getId());
    }

    @Test
    void deleteUserCase2() {
        UserModel user1 = new UserModel(UUID.randomUUID(), "arthurlevi1@gmail.com", "12342321", 123456);

        when(repository.findById(user1.getId())).thenReturn(Optional.of(user1));

        assertThrows(UserPrincipalNotFoundException.class, () -> userService.deleteUser(UUID.randomUUID()));

        verify(repository,never()).deleteById(user1.getId());
    }

    @Test
    void updateCase1() {
        UserModel existingUser = new UserModel(UUID.randomUUID(), "john.doe@example.com", "123456", 789012);

        when(repository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(repository.save(existingUser)).thenReturn(existingUser);


        UserModel updatedUser = new UserModel(existingUser.getId(), "john.doe@example.com", "updatedPassword", 789012);

        when(userService.update(updatedUser)).thenReturn(updatedUser);
        UserModel result = userService.update(updatedUser);


        verify(repository, times(2)).findById(existingUser.getId());
        verify(repository, times(1)).save(existingUser);

        assertNotNull(result);
        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(updatedUser.getPassword(), result.getPassword());
        assertEquals(existingUser.getRegistration(), result.getRegistration());
    }
    @Test
    void updateCase2() {

        when(repository.findById(any())).thenReturn(Optional.empty());
        UserModel userToUpdate = new UserModel(UUID.randomUUID(), "john.doe@example.com", "updatedPassword", 789012);

        assertThrows(UserPrincipalNotFoundException.class, () -> userService.update(userToUpdate));

        verify(repository,never()).save(any());


    }
}