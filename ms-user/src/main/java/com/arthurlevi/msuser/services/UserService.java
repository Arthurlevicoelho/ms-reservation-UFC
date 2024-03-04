package com.arthurlevi.msuser.services;

import com.arthurlevi.msuser.exceptions.CreateUserException;
import com.arthurlevi.msuser.exceptions.UserPrincipalNotFoundException;
import com.arthurlevi.msuser.models.UserModel;
import com.arthurlevi.msuser.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel findById(UUID id){
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() ->  new UserPrincipalNotFoundException("User not found"));

        return userModel;
    }

    public List<UserModel> findAll(){

        return this.userRepository.findAll();
    }
    public UserModel created(UserModel user){
        var validateEmail = this.userRepository.findByEmail(user.getEmail());
        var validateRegistration = this.userRepository.findByRegistration(user.getRegistration());

        if(validateEmail.isPresent() || validateRegistration.isPresent()){
            throw new CreateUserException("E-mail or Registration already registered ");
        }
        return this.userRepository.save(user);
    }

    public UserModel findByRegistration(Integer registration) throws UserPrincipalNotFoundException {
        UserModel userModel = userRepository.findByRegistration(registration)
                .orElseThrow(() -> new UserPrincipalNotFoundException("User not found"));

        return userModel;
    }

    public void deleteUser(UUID id){
        userRepository.findById(id)
                .orElseThrow(() -> new UserPrincipalNotFoundException("User not found"));

        userRepository.deleteById(id);
    }

    public UserModel update(UserModel user){
        var userUpdate = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserPrincipalNotFoundException("User not found"));

        updateUser(userUpdate, user);
        return userRepository.save(userUpdate);
    }

    private void updateUser(UserModel userUpdate, UserModel user){
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(user.getPassword());
    }
}
