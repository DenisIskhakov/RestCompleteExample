package com.example.restcompleteexample.service;

import com.example.restcompleteexample.model.Phone;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PhoneService {
    RestTemplate restTemplate2;


    @Cacheable
    @Async
    public CompletableFuture<List<Phone>> getPhone() {
        List<Phone> phone =  restTemplate2.exchange("http://localhost:8081/phone", HttpMethod.GET, null, new ParameterizedTypeReference<List<Phone>>(){}).getBody();
        System.out.println(phone.size());
        return CompletableFuture.completedFuture(phone);
    }

    public Phone post(@RequestBody Phone phone){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Phone> entity = new HttpEntity<Phone>(phone,headers);
        return restTemplate2.exchange("http://localhost:8081/phone", HttpMethod.POST, entity, Phone.class).getBody();
    }

    public Phone put(@PathVariable int id, @RequestBody Phone phone){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Phone> entity = new HttpEntity<Phone>(phone,headers);
        return restTemplate2.exchange("http://localhost:8081/phone/" + id, HttpMethod.PUT, entity,Phone.class).getBody();

    }
}
