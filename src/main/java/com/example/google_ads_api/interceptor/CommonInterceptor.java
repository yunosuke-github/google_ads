package com.example.google_ads_api.interceptor;

import com.example.google_ads_api.consts.ErrorCode;
import com.example.google_ads_api.dto.ErrorDto;
import com.example.google_ads_api.dto.ResponseDto;
import com.example.google_ads_api.service.common.LoginSessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonInterceptor implements HandlerInterceptor {

    @Autowired
    LoginSessionService loginSessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader("X-GOOGLE-ADS-TOKEN-X");
        if (token == null) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setCode(HttpStatus.UNAUTHORIZED.value());
            ErrorDto error = new ErrorDto();
            error.setCode(ErrorCode.E002.getCode());
            error.setMessage(ErrorCode.E002.getMessage());
            responseDto.setError(error);
            writeResponse(response, responseDto);
            return false;
        }
        if (!loginSessionService.isLoggedIn(token)) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setCode(HttpStatus.UNAUTHORIZED.value());
            ErrorDto error = new ErrorDto();
            error.setCode(ErrorCode.E001.getCode());
            error.setMessage(ErrorCode.E001.getMessage());
            responseDto.setError(error);
            writeResponse(response, responseDto);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }

    /**
     *
     * @param response
     * @param responseDto
     * @return
     * @throws Exception
     */
    private HttpServletResponse writeResponse(HttpServletResponse response, ResponseDto responseDto) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(responseDto));
        return response;
    }
}
