package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.JournalAppUrl;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JournalAppUrlRepository extends MongoRepository<JournalAppUrl, ObjectId> {

    List<JournalAppUrl> findAll();
}
