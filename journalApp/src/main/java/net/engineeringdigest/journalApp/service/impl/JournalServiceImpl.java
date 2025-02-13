package net.engineeringdigest.journalApp.service.impl;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntityRespository;
import net.engineeringdigest.journalApp.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalEntityRespository journalEntityRespository;


    @Override
    public void saveEntity(JournalEntry journalEntry){
        journalEntityRespository.save(journalEntry);
    }

    @Override
    public List<JournalEntry> getAllEntries() {
        return journalEntityRespository.findAll();
    }

    @Override
    public void deleteAllEntries() {
        journalEntityRespository.deleteAll();
    }


}
