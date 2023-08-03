package com.telkomedika.telkomedikaapi.service;

import com.telkomedika.telkomedikaapi.entity.accountEntity;
import com.telkomedika.telkomedikaapi.model.dto.accountDTO;
import com.telkomedika.telkomedikaapi.model.request.authRequest;
import com.telkomedika.telkomedikaapi.model.request.registRequest;
import com.telkomedika.telkomedikaapi.model.response.authResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface accountService {
    List<accountEntity> getAllAccount();
    Optional<accountEntity> findById(int id);
    accountEntity updateAccount(int id, accountDTO dto);
    accountEntity upgradeAccount(int id);
    void deleteAccount(int id);
    public void register(registRequest request);
    public authResponse authenticate(authRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
