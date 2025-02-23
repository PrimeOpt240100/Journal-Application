package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import net.engineeringdigest.journalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController // it is special class/component, which handle our http request
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @PostMapping("add/entry")
    public ResponseEntity<JournalApplicationApiResponse> addEntity(@RequestBody JournalEntry journalEntry){
        return new ResponseEntity<>(journalService.saveEntity(journalEntry),
                HttpStatus.OK);
    }

    @GetMapping("get/all/journal-entries")
    public ResponseEntity<JournalApplicationApiResponse> getAllJournalEntriesOfUser(){

        return new ResponseEntity<>(journalService.getAllEntries(),
                HttpStatus.OK);
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<JournalApplicationApiResponse> findById(@PathVariable ObjectId id){

        return new ResponseEntity<>(journalService.findJournalById(id),HttpStatus.OK);
    }

    @DeleteMapping("del/id/{id}")
    public ResponseEntity<JournalApplicationApiResponse> deleteAllEntries(@PathVariable ObjectId id){

        return new ResponseEntity<>(journalService.deleteEntryById(id),HttpStatus.OK);
    }

    @PutMapping("update/id/{id}")
    public ResponseEntity<JournalApplicationApiResponse> updateJournalById(@PathVariable ObjectId id,
                                                                           @RequestBody JournalEntry newJournalEntry){

        return new ResponseEntity<>(journalService.updateJournalById(id,newJournalEntry),HttpStatus.OK);
    }
}
