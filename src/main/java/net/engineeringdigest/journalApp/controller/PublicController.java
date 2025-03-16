package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.constants.JournalApplicationConstants;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import net.engineeringdigest.journalApp.service.PublicService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private PublicService publicService;


    @GetMapping("ping")
    public ResponseEntity<JournalApplicationApiResponse> ping(){
        return new ResponseEntity<>(JournalApplicationApiResponse.builder()
                .code(JournalApplicationConstants.SUCCESS)
                .msg(JournalApplicationConstants.SUCCESS_MSG)
                .data("OK")
                .build(), HttpStatus.OK );
    }

    @PostMapping("add")
    public ResponseEntity<JournalApplicationApiResponse> addUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addNewUser(user), HttpStatus.OK);
    }

    @GetMapping("get/weather-report")
    public ResponseEntity<JournalApplicationApiResponse> getWeatherReport(@RequestParam("city") String city){
        return new ResponseEntity<>(publicService.getWeatherReport(city), HttpStatus.OK);
    }
}
