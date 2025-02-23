package net.engineeringdigest.journalApp.service.impl;

import net.engineeringdigest.journalApp.constants.JournalApplicationConstants;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntityRespository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import net.engineeringdigest.journalApp.service.JournalService;
import net.engineeringdigest.journalApp.utils.CommonUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Component
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalEntityRespository journalEntityRespository;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public JournalApplicationApiResponse saveEntity(JournalEntry journalEntry){

        User user = userRepository.findByUserName(CommonUtils.getUserName());

        journalEntry.setDate(LocalDate.now());
        JournalEntry saved = journalEntityRespository.save(journalEntry);

        user.getJournalEntries().add(saved);
        userRepository.save(user);

        return JournalApplicationApiResponse.builder()
                .code(JournalApplicationConstants.SUCCESS)
                .msg(JournalApplicationConstants.SUCCESS_MSG)
                .data(journalEntry)
                .build();
    }

    @Override
    public JournalApplicationApiResponse getAllEntries() {

        User user = userRepository.findByUserName(CommonUtils.getUserName());

        return JournalApplicationApiResponse.builder()
                .code(JournalApplicationConstants.SUCCESS)
                .msg(JournalApplicationConstants.SUCCESS_MSG)
                .data(user.getJournalEntries())
                .build();
    }

    @Override
    @Transactional
    public JournalApplicationApiResponse deleteEntryById(ObjectId id){
        boolean removed=false;
        try{
            User user = userRepository.findByUserName(CommonUtils.getUserName());
            removed = user.getJournalEntries().removeIf(
                    x-> x.getId().equals(id));

            if(removed){
                userRepository.save(user);
                journalEntityRespository.deleteById(id);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        return JournalApplicationApiResponse.builder()
                .code(JournalApplicationConstants.SUCCESS)
                .msg(JournalApplicationConstants.SUCCESS_MSG)
                .data(removed)
                .build();
    }

    @Override
    public JournalApplicationApiResponse updateJournalById(ObjectId id, JournalEntry newJournalEntry) {

        try{
            JournalEntry journalEntry = null;
            User user = userRepository.findByUserName(CommonUtils.getUserName());

            List<JournalEntry> collect = user.getJournalEntries().stream().filter(
                            x->x.getId().equals(id))
                    .collect(Collectors.toList());

            if(!collect.isEmpty()){
                JournalEntry oldJournalEntry = collect.get(0);
                oldJournalEntry.setMsg(newJournalEntry.getMsg() != null && !newJournalEntry.getMsg().isEmpty() ? newJournalEntry.getMsg() : oldJournalEntry.getMsg());
                oldJournalEntry.setTitle(newJournalEntry.getTitle() != null && !newJournalEntry.getTitle().isEmpty() ? newJournalEntry.getTitle() : oldJournalEntry.getTitle());
                oldJournalEntry.setDate(LocalDate.now());
                userRepository.save(user);
                journalEntityRespository.save(oldJournalEntry);
                return JournalApplicationApiResponse.builder()
                        .code(JournalApplicationConstants.SUCCESS)
                        .msg(JournalApplicationConstants.SUCCESS_MSG)
                        .data(oldJournalEntry)
                        .build();
            }
            else{
                throw new NoSuchElementException();
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
            JournalEntry journalEntry = null;
            User user = userRepository.findByUserName(CommonUtils.getUserName());
            List<JournalEntry> collect = user.getJournalEntries().stream().filter(
                    x->x.getId().equals(id))
                            .collect(Collectors.toList());
            if(!collect.isEmpty()){
                journalEntry = journalEntityRespository.findById(id).get();
            }
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
