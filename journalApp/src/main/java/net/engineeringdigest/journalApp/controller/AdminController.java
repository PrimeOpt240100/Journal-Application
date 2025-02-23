package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import net.engineeringdigest.journalApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/get/all-users")
    public ResponseEntity<JournalApplicationApiResponse> getAllUser(){
        return new ResponseEntity<>(adminService.getAllUsersList(), HttpStatus.OK);
    }
}
