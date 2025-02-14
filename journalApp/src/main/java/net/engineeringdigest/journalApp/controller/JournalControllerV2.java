package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.constants.JournalApplicationConstants;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import net.engineeringdigest.journalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController // it is special class/component, which handle our http request
@RequestMapping("v2/journal")
public class JournalControllerV2 {

    @Autowired
    private JournalService journalService;

    @PostMapping("add/entry")
    public ResponseEntity<JournalApplicationApiResponse> addEntity(@RequestBody JournalEntry journalEntry){

        journalEntry.setDate(LocalDateTime.now());
        journalService.saveEntity(journalEntry);
        return new ResponseEntity<>(new JournalApplicationApiResponse(JournalApplicationConstants.SUCCESS,
                JournalApplicationConstants.SUCCESS_MSG, journalEntry),
                HttpStatus.OK);
    }

    @GetMapping("get/all")
    public ResponseEntity<JournalApplicationApiResponse> getAllEntries(){

        return new ResponseEntity<>(new JournalApplicationApiResponse(JournalApplicationConstants.SUCCESS,
                JournalApplicationConstants.SUCCESS_MSG, journalService.getAllEntries()),
                HttpStatus.OK);
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<JournalApplicationApiResponse> findById(@PathVariable ObjectId id){

        return new ResponseEntity<>(journalService.findJournalById(id),HttpStatus.OK);
    }

    @DeleteMapping("del/all")
    public ResponseEntity<JournalApplicationApiResponse> deleteAllEntries(){

        journalService.deleteAllEntries();
        JournalApplicationApiResponse journalApplicationApiResponse = JournalApplicationApiResponse.builder()
                .code(JournalApplicationConstants.SUCCESS)
                .msg(JournalApplicationConstants.SUCCESS_MSG)
                .data(null)
                .build();
        return new ResponseEntity<>(journalApplicationApiResponse,HttpStatus.OK);
    }

    @PutMapping("update/id/{id}")
    public ResponseEntity<JournalApplicationApiResponse> updateJournalbyId(@PathVariable ObjectId id,
                                                                           @RequestBody JournalEntry newJournalEntry){

        return new ResponseEntity<>(journalService.updateJournalById(id,newJournalEntry),HttpStatus.OK);
    }
}
