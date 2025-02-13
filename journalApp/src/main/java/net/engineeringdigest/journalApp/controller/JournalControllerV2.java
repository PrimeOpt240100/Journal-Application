package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.database.DataBase;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.response.BaseResponse;
import net.engineeringdigest.journalApp.service.JournalService;
import net.engineeringdigest.journalApp.service.impl.JournalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // it is special class/component, which handle our http request
@RequestMapping("v2/journal")
public class JournalControllerV2 {

    @Autowired
    private JournalService journalService;

    @PostMapping("add/entry")
    public ResponseEntity<BaseResponse> addEntity(@RequestBody JournalEntry journalEntry){

        journalEntry.setDate(LocalDateTime.now());

        journalService.saveEntity(journalEntry);

        return new ResponseEntity<>(new BaseResponse<>("200","Okay",journalEntry), HttpStatus.OK);
    }

    @GetMapping("get/all")
    public ResponseEntity<BaseResponse> getAllEntries(){
        return new ResponseEntity<>(new BaseResponse<>("200","Okay",journalService.getAllEntries()), HttpStatus.OK);
    }

    @DeleteMapping("del/all")
    public ResponseEntity<BaseResponse> deleteAllEntries(){
        journalService.deleteAllEntries();
        BaseResponse baseResponse = BaseResponse.builder()
                .code("302")
                .msg("all collections has been removed")
                .data(null)
                .build();
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
