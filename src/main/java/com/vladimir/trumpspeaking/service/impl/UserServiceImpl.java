package com.vladimir.trumpspeaking.service.impl;


import com.vladimir.trumpspeaking.exception.UserTelegramNotFound;
import com.vladimir.trumpspeaking.models.UserTelegram;
import com.vladimir.trumpspeaking.repository.UserRepository;
import com.vladimir.trumpspeaking.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registration(Long id) {
        if (!userRepository.existsById(id))
            userRepository.save(new UserTelegram(id, ""));
    }
    @Override
    public UserTelegram findById(Long id) throws UserTelegramNotFound {
        userExist(id);

        return userRepository.findUserTelegramById(id);
    }

    @Override
    public void setFindName(Long id, String findName) throws UserTelegramNotFound {
        userExist(id);

        UserTelegram userTelegram = userRepository.findUserTelegramById(id);
        userTelegram.setFindName(findName);
        userRepository.save(userTelegram);
    }

    private void userExist(Long userId) throws UserTelegramNotFound {
        if (!userRepository.existsById(userId))
            throw new UserTelegramNotFound("User not exist");
    }
}
