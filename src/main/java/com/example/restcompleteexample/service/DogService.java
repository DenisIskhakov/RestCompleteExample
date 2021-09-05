package com.example.restcompleteexample.service;

import com.example.restcompleteexample.model.Dog;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@CacheConfig(cacheNames={"DogCache"})
public class DogService {
 RestTemplate restTemplate;

//    @Scheduled(fixedDelay = 3000)
//@Scheduled(cron="*/5 * * * * ?")
    @Cacheable
    @Async
    public CompletableFuture<List<Dog>> getDog() {
        List<Dog> list = restTemplate.exchange("http://localhost:8081/dog", HttpMethod.GET, null, new ParameterizedTypeReference<List<Dog>>(){}).getBody();
        System.out.println(list.size());
        return CompletableFuture.completedFuture(list);
    }
       public Dog post( Dog dog){
//        System.out.println("________________" + Thread.currentThread().getName());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Dog> entity = new HttpEntity<Dog>(dog,headers);
        return restTemplate.exchange("http://localhost:8081/dog", HttpMethod.POST, entity, Dog.class).getBody();
    }
       public Dog put( int id,  Dog dog){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Dog> entity = new HttpEntity<Dog>(dog,headers);
        return restTemplate.exchange("http://localhost:8081/dog/" + id, HttpMethod.PUT, entity,Dog.class).getBody();

    }
        public String removeDogId(int id){

        return restTemplate.exchange("http://localhost:8081/dog/" + id, HttpMethod.DELETE, null, String.class).getBody();
    }
}
