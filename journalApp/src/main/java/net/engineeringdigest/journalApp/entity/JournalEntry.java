package net.engineeringdigest.journalApp.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntry {

    @Id
    private ObjectId id;
    @NotNull
    private String title;

    private String msg;

    private LocalDateTime date;
}
