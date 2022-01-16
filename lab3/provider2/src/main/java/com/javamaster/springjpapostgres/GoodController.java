package com.javamaster.springjpapostgres;

import com.javamaster.springjpapostgres.entity.Good;
import com.javamaster.springjpapostgres.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class GoodController {

    @Autowired
    private GoodService goodService;


    @GetMapping("/details/{id}")
    public Good getDetails(@PathVariable("id") Long id)
    {
        return goodService.findById(id);
    }

    @GetMapping("/amount")
    public Integer getAmont()
    {
        return goodService.findAmount();
    }

   @GetMapping("/page/{id}")
    public List<Good> getByPage(@PathVariable("id") Long id)
    {
        return goodService.generalPage(id);
    }

}
