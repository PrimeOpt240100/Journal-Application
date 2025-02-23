package net.engineeringdigest.journalApp.service.impl;

import net.engineeringdigest.journalApp.constants.JournalApplicationConstants;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.model.response.AdminUserResponse;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import net.engineeringdigest.journalApp.service.AdminService;
import net.engineeringdigest.journalApp.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public JournalApplicationApiResponse getAllUsersList() {

        String currentAdmin = CommonUtils.getUserName();

        List<AdminUserResponse> userList = new ArrayList<>();

        List<User> allUserList = userRepository.findAll();
        
        if(!allUserList.isEmpty()){
            for(User user : allUserList){
                if(!user.getUserName().equals(currentAdmin)){
                    AdminUserResponse adminUserResponse = new AdminUserResponse();
                    adminUserResponse.setUserName(user.getUserName());
                    adminUserResponse.setRoles(user.getRoles());
                    userList.add(adminUserResponse);
                }
            }
        }
                
        return JournalApplicationApiResponse.builder()
                .code(JournalApplicationConstants.SUCCESS)
                .msg(JournalApplicationConstants.SUCCESS_MSG)
                .data(userList)
                .build();
    }
}
