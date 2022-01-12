package com.javamaster.springjpapostgres;

import com.javamaster.springjpapostgres.entity.Good;
import com.javamaster.springjpapostgres.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class GoodController {

    @Autowired
    private GoodService goodService;


    @GetMapping("/good")
    List<Good> getAll() {
        return goodService.findAll();
    }

    @GetMapping("/details/{id}")
    public Good getDetails(@PathVariable("id") Long id)
    {
        return goodService.findById(id);
    }

    @GetMapping("/price-list")
    List<Object> getNamePriceAll() {
        return goodService.findNamePriceAll();
    }

}
