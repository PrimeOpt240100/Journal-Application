package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;

public interface UserService {

    JournalApplicationApiResponse addUser(User user);

    JournalApplicationApiResponse getAllUsers();

    JournalApplicationApiResponse updateUserNameAndPassword(String name, User newUser);

}
