package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import org.bson.types.ObjectId;

import java.util.List;

public interface JournalService {

    JournalApplicationApiResponse saveEntity(JournalEntry journalEntry);

    JournalApplicationApiResponse getAllEntries();

    JournalApplicationApiResponse deleteEntryById(ObjectId id);

    JournalApplicationApiResponse updateJournalById(ObjectId id, JournalEntry newJournalEntry);

    JournalApplicationApiResponse findJournalById(ObjectId id);
}
