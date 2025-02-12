package net.engineeringdigest.journalApp.database;

import net.engineeringdigest.journalApp.entity.JournalEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataBase {

    public final Map<Integer, JournalEntity> list = new HashMap<>();

}
