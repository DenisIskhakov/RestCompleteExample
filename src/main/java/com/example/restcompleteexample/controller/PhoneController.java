package com.example.restcompleteexample.controller;

import com.example.restcompleteexample.model.Phone;

import com.example.restcompleteexample.service.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@AllArgsConstructor
@RequestMapping("/phone")
public class PhoneController {
    PhoneService phoneService;

    @GetMapping
    public CompletableFuture<List<Phone>> getPhone() {
        return phoneService.getPhone();
    }
    @RequestMapping(method = RequestMethod.POST)
    public Phone post(@RequestBody Phone phone){

        return phoneService.post(phone);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Phone put(@PathVariable int id,@RequestBody Phone phone){

        return phoneService.put(id,phone);

    }
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public String removePhoneId(@PathVariable int id) {
//        return restTemplate1.exchange("http://localhost:8081/phone/"+id, HttpMethod.DELETE, null, String.class).getBody();
//    }



//    @GetMapping
//    public CompletableFuture<List<Phone>> getPhone(){
//        System.out.println("_______ " + Thread.currentThread().getName());
//        phoneService.getPhone();
//        phoneService.getPhone();
//        phoneService.getPhone();
//        phoneService.getPhone();
//        return phoneService.getPhone();
//    }
//    @PostMapping
//    public Phone post(@RequestBody Phone phone){
//        return phoneService.addPhone(phone);
//    }
//    @GetMapping("/{id}")
//    public Phone getId(int id){
//        return phoneService.getId(id);
//    }
//    @DeleteMapping("/{id}")
//    public String removePhoneId(int id){
//        phoneService.removePhoneId(id);
//        return "Phone remove id = " + id;
//    }
//    @DeleteMapping
//    public String removeAll(){
//        phoneService.removeAll();
//        return "Phone remove all";
//    }


}
