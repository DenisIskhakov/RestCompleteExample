package com.example.restcompleteexample.controller;


import com.example.restcompleteexample.model.Dog;
import com.example.restcompleteexample.service.DogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/dog")
public class DogController {
    DogService dogService;

    @GetMapping
    public CompletableFuture<List<Dog>> getDog() {
//        System.out.println("________________" + Thread.currentThread().getName());
        return dogService.getDog();
    }
    @RequestMapping(method = RequestMethod.POST)
    public Dog post(@RequestBody Dog dog){

        return dogService.post(dog);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Dog put(@PathVariable int id,@RequestBody Dog dog){

        return dogService.put(id,dog);

    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String removeDogId(@PathVariable int id){

        return dogService.removeDogId(id);
    }


//    POST localhost:8080/dog  // метод POST - URL
//    Content-Type: application/json // header
//
//    {"name":"Tom", "age": 20}   // body
//    @PostMapping
//    public Dog post(@RequestBody Dog dog){
//        return dogService.addDog(dog);
//    }
//
//    @GetMapping("/{id}")
//    public Dog getId(@PathVariable int id) {
//        return dogService.getId(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public String removeDogId(@PathVariable int id) {
//        dogService.removeDogId(id);
//        return "remove Dog-id " + id;
//    }
//
//    @DeleteMapping
//    public String removeAll() {
//        dogService.removeAll();
//        return "remove all-Dog";
//    }
//
//    @RequestMapping
//            (value = "/foo", method = RequestMethod.OPTIONS)
//    public ResponseEntity options(HttpServletResponse response) {
//        response.setHeader("Allow", "HEAD,GET,PUT,OPTIONS");
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
