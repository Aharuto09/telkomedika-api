package com.telkomedika.telkomedikaapi.controller;

import com.telkomedika.telkomedikaapi.entity.accountEntity;
import com.telkomedika.telkomedikaapi.model.dto.accountDTO;
import com.telkomedika.telkomedikaapi.service.accountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Acc")
public class AccntController {
    @Autowired
    accountService accountservice;
    @GetMapping("/testing")
    String testing(){
        return "Hello Horld, Your Token is Valid";
    }
    @GetMapping("/all")
    public List<accountEntity> findAllAccount(){
        return accountservice.getAllAccount();
    }
    @GetMapping("{id}")
    public accountEntity findAccountById(@PathVariable("id") int id){

        return accountservice.findById(id).orElse(null);
    }
    @PutMapping("update/{id}")
    public accountEntity updateAccount(@PathVariable("id") int id, @RequestBody accountDTO dto){
        return accountservice.updateAccount(id, dto);
    }
    @PutMapping("upgrade/{id}")
    public accountEntity upgradeAccount(@PathVariable("id") int id){
        return accountservice.upgradeAccount(id);
    }
    @DeleteMapping("{id}")
    public void deleteAccount(@PathVariable("id") int id){
        accountservice.deleteAccount(id);
    }
}
