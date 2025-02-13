package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;

import java.util.List;

public interface JournalService {

    void saveEntity(JournalEntry journalEntry);

    List<JournalEntry> getAllEntries();

    void deleteAllEntries();
}
