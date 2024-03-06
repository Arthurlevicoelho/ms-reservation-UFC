package com.arthurlevi.msuser.controllers;


import com.arthurlevi.msuser.dtos.UserRecordDto;
import com.arthurlevi.msuser.exceptions.UserPrincipalNotFoundException;
import com.arthurlevi.msuser.models.UserModel;
import com.arthurlevi.msuser.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findUserById(@PathVariable UUID id ){

        var user = userService.findById(id);

        return ResponseEntity.ok(user);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserModel>> findAll() {

        List<UserModel> usersList = this.userService.findAll();
        return ResponseEntity.ok().body(usersList);
    }

    @GetMapping("/registration/{registration}")
    public ResponseEntity<UserModel> findByUserByRegistration(@PathVariable Integer registration)throws UserPrincipalNotFoundException {

        return ResponseEntity.ok().body(userService.findByRegistration(registration));
    }

    @PostMapping("/create")
    public ResponseEntity created(@RequestBody @Valid UserRecordDto userRecordDto){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto,userModel);
        userModel = this.userService.created(userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserRecordDto userRecordDto, @PathVariable UUID id) {

        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto,userModel);
        userModel.setId(id);
        userService.update(userModel);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
