package com.telkomedika.telkomedikaapi.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telkomedika.telkomedikaapi.entity.accountEntity;
import com.telkomedika.telkomedikaapi.entity.roleEntity;
import com.telkomedika.telkomedikaapi.model.dto.accountDTO;
import com.telkomedika.telkomedikaapi.model.request.authRequest;
import com.telkomedika.telkomedikaapi.model.request.registRequest;
import com.telkomedika.telkomedikaapi.model.response.authResponse;
import com.telkomedika.telkomedikaapi.repository.accountRepository;
import com.telkomedika.telkomedikaapi.security.jwt.jwtUtility;
import com.telkomedika.telkomedikaapi.service.accountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class accountServiceImpl implements accountService {
    @Autowired
    accountRepository accountRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    jwtUtility jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public List<accountEntity> getAllAccount() {
        return accountRepo.findAll();
    }

    @Override
    public Optional<accountEntity> findById(int id) {
        return accountRepo.findById(id);
    }

    @Override
    public accountEntity updateAccount(int id, accountDTO dto) {
        accountEntity entity = accountRepo.findById(id).orElse(null);
        if(dto.name() != null) {
            entity.setName(dto.name());
        }
        if(dto.username() != null){
            entity.setUsername(dto.username());
        }
        if(dto.email() != null){
            entity.setEmail(dto.email());
        }
        return accountRepo.save(entity);
    }
    @Override
    public accountEntity upgradeAccount(int id) {
        accountEntity entity = accountRepo.findById(id).orElse(null);
        entity.setRole(roleEntity.ADMIN);
        return accountRepo.save(entity);
    }

    @Override
    public void deleteAccount(int id) {

    }

    @Override
    public void register(registRequest request) {
        var account = accountEntity.builder()
                .username(request.getUsername())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleEntity.USER)
                .build();
        accountRepo.save(account);
    }

    @Override
    public authResponse authenticate(authRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );
//        logger.error(request.getUsername());
        var account = accountRepo.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtUtil.generateToken(account);
        return authResponse.builder()
                .accessToken(jwtToken)
                .build();
    }





    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userName;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userName = jwtUtil.extractUsername(refreshToken);
        if (userName != null) {
            var user = this.accountRepo.findByUsername(userName)
                    .orElseThrow();
            if (jwtUtil.isTokenValid(refreshToken, user)) {
                var accessToken = jwtUtil.generateToken(user);
                var authresponse = authResponse.builder()
                        .accessToken(accessToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authresponse);
            }
        }
    }

}
