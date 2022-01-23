package com.example.google_ads_api.repository.common;

import com.example.google_ads_api.domain.common.LoginSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginSessionRepository extends JpaRepository<LoginSession, Long> {

    public LoginSession findByToken(String token);

}
