package com.telkomedika.telkomedikaapi.controller;

import com.telkomedika.telkomedikaapi.model.request.agendaRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/agenda")
public class agendaController {
    URI uri = new URI("http://knybby-fx505dt:8081/agenda");

    RestTemplate restTemplate = new RestTemplate();

    public agendaController() throws URISyntaxException {
        this.restTemplate = new RestTemplate();
    }
    @GetMapping
    public agendaRequest[] getAgendaAll(){
        agendaRequest[] agendalist = restTemplate.getForObject(uri, agendaRequest[].class);
        return agendalist;
    }
    @GetMapping("{id}")
    public agendaRequest getAgendaById(@PathVariable("id") Long id) throws URISyntaxException {
        agendaRequest agenda = restTemplate.getForObject(uri+"/"+id, agendaRequest.class);
        return agenda;
    }
    @PostMapping
    public agendaRequest addAgenda(@RequestBody agendaRequest agenda) throws URISyntaxException{
        agendaRequest newAgenda =  restTemplate.postForObject(uri, agenda, agendaRequest.class);
        return newAgenda;
    }
    @DeleteMapping("{id}")
    public void deteleAgenda(@PathVariable Long id) throws URISyntaxException{
        restTemplate.delete(uri+"/"+id);
    }
    @GetMapping("testAgenda")
    public String getString(){
        return "Your Admin";
    }
}
