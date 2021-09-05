package com.example.restcompleteexample.controller;

import com.example.restcompleteexample.model.Cat;
import com.example.restcompleteexample.model.Dog;
import com.example.restcompleteexample.service.CatService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@AllArgsConstructor
@RequestMapping("/cat")
public class CatController {
    CatService catService;

    @GetMapping
    public CompletableFuture<List<Cat>> getCat() {
        return catService.getCat();
    }
    @RequestMapping(method = RequestMethod.POST)
    public Cat post(@RequestBody Cat cat){

        return catService.post(cat);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Cat put(@PathVariable int id,@RequestBody Cat cat){

        return catService.put(id,cat);

    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String removeCatId(@PathVariable int id){

        return catService.removeCatId(id);
    }
//    @GetMapping
//    public List<Cat> getCats(){
//        return catService.getCat();
//    }
//    @GetMapping("/{id}")
//    public Cat getId(@PathVariable int id){
//        return catService.getId(id);
//    }
//    @DeleteMapping("/{id}")
//    public String removeCat(@PathVariable int id){
//        catService.removeCat(id);
//        return "Remove Cat-id " + id;
//    }
//    @DeleteMapping
//    public String removeAll(){
//        catService.removeAll();
//        return "Remove All";
//    }
}
