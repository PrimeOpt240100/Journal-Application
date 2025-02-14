package net.engineeringdigest.journalApp.service.impl;

import net.engineeringdigest.journalApp.constants.JournalApplicationConstants;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntityRespository;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import net.engineeringdigest.journalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public JournalApplicationApiResponse updateJournalById(ObjectId id, JournalEntry newJournalEntry) {

        try{
            JournalEntry oldJournalEntry = journalEntityRespository.findById(id).get();

            if (oldJournalEntry != null) {
                oldJournalEntry.setMsg(newJournalEntry.getMsg() != null && !newJournalEntry.getMsg().isEmpty() ? newJournalEntry.getMsg() : oldJournalEntry.getMsg());
                oldJournalEntry.setTitle(newJournalEntry.getTitle() != null && !newJournalEntry.getTitle().isEmpty() ? newJournalEntry.getTitle() : oldJournalEntry.getTitle());
                oldJournalEntry.setDate(LocalDateTime.now());
                journalEntityRespository.save(oldJournalEntry);

                return JournalApplicationApiResponse.builder()
                        .code(JournalApplicationConstants.SUCCESS)
                        .msg(JournalApplicationConstants.SUCCESS_MSG)
                        .data(oldJournalEntry)
                        .build();
            }
            else{
                throw new NullPointerException();
            }
        } catch (NoSuchElementException | NullPointerException e){

            return JournalApplicationApiResponse.builder()
                    .code(JournalApplicationConstants.FAILURE)
                    .msg(e.getMessage())
                    .data(null)
                    .build();
        }

    }

    @Override
    public JournalApplicationApiResponse findJournalById(ObjectId id) {

        try {
            JournalEntry journalEntry = journalEntityRespository.findById(id).get();
            return JournalApplicationApiResponse.builder()
                    .code(JournalApplicationConstants.SUCCESS)
                    .msg(JournalApplicationConstants.SUCCESS_MSG)
                    .data(journalEntry)
                    .build();
        }catch (NoSuchElementException  e){
            return JournalApplicationApiResponse.builder()
                    .code(JournalApplicationConstants.FAILURE)
                    .msg(e.getMessage())
                    .data(null)
                    .build();
        }

    }

}
