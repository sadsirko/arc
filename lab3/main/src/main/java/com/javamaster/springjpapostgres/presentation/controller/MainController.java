package com.javamaster.springjpapostgres.presentation.controller;

import com.javamaster.springjpapostgres.business.GoodServiceFacade;
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

    @Autowired
    private GoodServiceFacade goodServiceFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public List<Good> search(@RequestParam(value = "query") String search) {
        return goodService.getSearch(search);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/full/search")
    @ResponseBody
    public List<Good> searchFull(@RequestParam(value = "query") String search) {
        return goodServiceFacade.getFullSearch(search);
    }

    @GetMapping("/full/good")
    List<Good> getFullGood() {
        return goodServiceFacade.getFullAll();
    }

    @GetMapping("/good")
    List<Good> getAll() {
        return goodServiceFacade.read();
    }

    @PostMapping("/create/")
    public void save(@RequestBody Good good)
    {
        goodServiceFacade.create(good);
    }

    @PutMapping("/update/")
    public void update(@RequestBody Good good)
    {
        goodServiceFacade.update(good);
    }

    @DeleteMapping("/delete/")
     public void delete(@RequestBody Long id) {
        goodServiceFacade.delete(id);
    }


}
