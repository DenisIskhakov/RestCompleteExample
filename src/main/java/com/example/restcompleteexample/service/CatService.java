package com.example.restcompleteexample.service;

import com.example.restcompleteexample.model.Cat;
import com.example.restcompleteexample.model.Dog;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@CacheConfig(cacheNames={"Cat-Cache"})
public class CatService {
    RestTemplate restTemplate;

    //    @Scheduled(fixedDelay = 3000)
//@Scheduled(cron="*/5 * * * * ?")
    @Cacheable // Кэш
    @Async
    public CompletableFuture<List<Cat>> getCat() {
        List<Cat> list = restTemplate.exchange("http://localhost:8081/cat", HttpMethod.GET, null, new ParameterizedTypeReference<List<Cat>>(){}).getBody();
        System.out.println(list.size());
        return CompletableFuture.completedFuture(list); // обернули в CF
    }
    public Cat post(Cat cat){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cat> entity = new HttpEntity<Cat>(cat,headers);
        return restTemplate.exchange("http://localhost:8081/cat", HttpMethod.POST, entity, Cat.class).getBody();
    }
    public Cat put( int id,  Cat cat){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cat> entity = new HttpEntity<Cat>(cat,headers);
        return restTemplate.exchange("http://localhost:8081/cat/" + id, HttpMethod.PUT, entity,Cat.class).getBody();

    }
    public String removeCatId(int id){

        return restTemplate.exchange("http://localhost:8081/cat/" + id, HttpMethod.DELETE, null, String.class).getBody();
    }
}
