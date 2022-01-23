package com.example.google_ads_api.service.common;

import com.example.google_ads_api.repository.common.LoginSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginSessionService {

    @Autowired
    private LoginSessionRepository loginSessionRepository;

    public boolean isLoggedIn(String token) {
        if (loginSessionRepository.findByToken(token) == null) {
            return false;
        }
        return true;
    }
}
