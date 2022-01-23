package com.example.google_ads_api.service;

import com.example.google_ads_api.dto.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractService {

    abstract ResponseDto get();

    abstract ResponseDto delete();

    abstract ResponseDto add();
}
