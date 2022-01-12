package com.javamaster.springjpapostgres.controller;

import com.javamaster.springjpapostgres.entity.Good;
import com.javamaster.springjpapostgres.repository.GoodRepository;
import com.javamaster.springjpapostgres.service.GoodService;
import com.javamaster.springjpapostgres.specification.GoodSpecification;
import com.javamaster.springjpapostgres.specification.GoodSpecificationsBuilder;
import com.javamaster.springjpapostgres.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/")
public class GoodController {

    @Autowired
    private GoodService goodService;
    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private GoodRepository repo;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public List<Good> search(@RequestParam(value = "query") String search) {
        GoodSpecificationsBuilder builder = new GoodSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Good> spec = builder.build();
        return repo.findAll(spec);
    }
//    @GetMapping("/search")
//    List<Good> search() {
//        GoodSpecification spec1 = new GoodSpecification(new SearchCriteria("id", ":", 1002));
//        List<Good> res = goodRepository.findAll(spec1);
//        return res;
//    }
//
//    @GetMapping("/good")
//    List<Good> getAll() {
//        return goodService.findAll();
//    }
//    @GetMapping("/details/{id}")
//    public Good getDetails(@PathVariable("id") Long id)
//    {
//        return goodService.findById(id);
//    }
////
//    @GetMapping("/price-list")
//    List<Object> getNamePriceAll() {
//        return goodService.findNamePriceAll();
//    }

}
