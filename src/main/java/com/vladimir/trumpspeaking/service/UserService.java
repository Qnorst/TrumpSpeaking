package com.vladimir.trumpspeaking.service;


import com.vladimir.trumpspeaking.exception.UserTelegramNotFound;
import com.vladimir.trumpspeaking.models.UserTelegram;

import java.util.List;

public interface UserService {
    void registration(Long id);
    UserTelegram findById(Long id) throws UserTelegramNotFound;
    void setFindName(Long id, String findName) throws UserTelegramNotFound;
}
