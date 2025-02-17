package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("update/user/{name}")
    public ResponseEntity<JournalApplicationApiResponse> updateUserNameAndPassword(
            @PathVariable String name, @RequestBody User newUser){
        return new ResponseEntity<>(userService.updateUserNameAndPassword(name,newUser),HttpStatus.OK);
    }
}
