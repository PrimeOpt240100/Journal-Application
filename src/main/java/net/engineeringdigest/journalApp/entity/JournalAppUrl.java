package net.engineeringdigest.journalApp.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app_url")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalAppUrl {
    private String key;
    private String value;
}
