package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import org.bson.types.ObjectId;

import java.util.List;

public interface JournalService {

    JournalApplicationApiResponse saveEntity(JournalEntry journalEntry, String userName);

    JournalApplicationApiResponse getAllEntries(String userName);

    void deleteAllEntries();

    JournalApplicationApiResponse updateJournalById(ObjectId id, JournalEntry newJournalEntry);

    JournalApplicationApiResponse findJournalById(ObjectId id);
}
