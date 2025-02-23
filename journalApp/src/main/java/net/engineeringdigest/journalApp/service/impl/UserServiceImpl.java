package net.engineeringdigest.journalApp.service.impl;

import net.engineeringdigest.journalApp.constants.JournalApplicationConstants;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public JournalApplicationApiResponse addNewUser(User user) {

        User oldUser = userRepository.findByUserName(user.getUserName());

        if(oldUser==null){

            User newUser = User.builder()
                    .userName(user.getUserName())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(Arrays.asList("user"))
                    .build();

            userRepository.save(newUser);

            return JournalApplicationApiResponse.builder()
                    .code(JournalApplicationConstants.SUCCESS)
                    .msg(JournalApplicationConstants.SUCCESS_MSG)
                    .data(newUser)
                    .build();
        }

        return JournalApplicationApiResponse.builder()
                .code(JournalApplicationConstants.FAILURE)
                .msg("Sorry user name " + user.getUserName() + " is already exists in DB ")
                .data(null)
                .build();
    }

    @Override
    public JournalApplicationApiResponse getAllUsers() {

        List<User> userList = userRepository.findAll();

        return JournalApplicationApiResponse.builder()
                .code(JournalApplicationConstants.SUCCESS)
                .msg(JournalApplicationConstants.SUCCESS_MSG)
                .data(userList)
                .build();
    }

    @Override
    public JournalApplicationApiResponse updateUserNameAndPassword(User newUser) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getName();

        try{
            User oldUser = userRepository.findByUserName(userName);
            oldUser.setUserName(newUser.getUserName());
            oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userRepository.save(oldUser);
            return JournalApplicationApiResponse.builder()
                    .code(JournalApplicationConstants.SUCCESS)
                    .msg(JournalApplicationConstants.SUCCESS_MSG)
                    .data(oldUser)
                    .build();

        } catch (NoSuchElementException | NullPointerException e){
            return JournalApplicationApiResponse.builder()
                    .code(JournalApplicationConstants.FAILURE)
                    .msg(e.getMessage())
                    .data(null)
                    .build();
        }

    }
}
