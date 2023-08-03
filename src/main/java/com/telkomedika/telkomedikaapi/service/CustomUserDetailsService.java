package com.telkomedika.telkomedikaapi.service;

import com.telkomedika.telkomedikaapi.repository.accountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private accountRepository accountRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User " + username + "Not Found !"));
    }
}
