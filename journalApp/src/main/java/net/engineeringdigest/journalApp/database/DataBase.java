package net.engineeringdigest.journalApp.database;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataBase {

    public final Map<String, JournalEntry> list = new HashMap<>();

}
