package com.vladimir.trumpspeaking.repository;

import com.vladimir.trumpspeaking.models.UserTelegram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTelegram, Long> {
    UserTelegram findUserTelegramById(Long id);
}
