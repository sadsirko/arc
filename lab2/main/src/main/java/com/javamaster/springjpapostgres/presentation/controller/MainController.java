package com.javamaster.springjpapostgres.presentation.controller;

import com.javamaster.springjpapostgres.business.service.GoodCashService;
import com.javamaster.springjpapostgres.persistence.entity.Good;
import com.javamaster.springjpapostgres.business.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private GoodService goodService;
    @Autowired
    private GoodCashService goodCashService;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public List<Good> search(@RequestParam(value = "query") String search) {
        return goodService.getSearch(search);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/full/search")
    @ResponseBody
    public List<Good> searchFull(@RequestParam(value = "query") String search) {

        return goodService.getFullSearch(search);
    }

    @GetMapping("/truncate")
    void trunc(){
        goodService.saveToCash(goodService.getProvider2All());
    }

    @GetMapping("/full/good")
    List<Good> getFullGood() {
        return goodService.getFullGood();
    }

    @GetMapping("/good")
    List<Good> getAll() {
        return goodService.findAll();
    }

    @GetMapping("/details/{id}")
    public Good getDetails(@PathVariable("id") Long id)
    {
        return goodService.findById(id);
    }

    @PostMapping("/create/")
    public void save(@RequestBody Good good)
    {
        goodService.save(good);
    }


//    @PutMapping("/update/")
//    public void update(@RequestBody Good good)
//    {
//        goodService.update(good);
//    }

    @DeleteMapping("/delete/")
     public void delete(@RequestBody Long id) {
        goodService.delete(id);
    }

}
