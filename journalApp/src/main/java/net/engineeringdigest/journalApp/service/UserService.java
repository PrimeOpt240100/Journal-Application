package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;

public interface UserService {

    JournalApplicationApiResponse addNewUser(User user);

    JournalApplicationApiResponse getAllUsers();

    JournalApplicationApiResponse updateUserNameAndPassword(User newUser);

}
