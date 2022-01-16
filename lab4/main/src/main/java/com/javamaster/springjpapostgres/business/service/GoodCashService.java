package com.javamaster.springjpapostgres.business.service;

import com.javamaster.springjpapostgres.business.service.specification.GoodCashSpecificationsBuilder;
import com.javamaster.springjpapostgres.business.service.specification.GoodSpecificationsBuilder;
import com.javamaster.springjpapostgres.persistence.entity.Good;
import com.javamaster.springjpapostgres.persistence.entity.GoodCash;
import com.javamaster.springjpapostgres.persistence.repository.GoodCashRepository;
import com.javamaster.springjpapostgres.persistence.repository.GoodRepository;
import com.javamaster.springjpapostgres.persistence.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.coyote.http11.Constants.a;

@Service
public class GoodCashService {
    @Autowired
    private final GoodCashRepository goodCashRepository;
    @Autowired
    private final SubcategoryRepository subcategoryRepository;

    public GoodCashService(GoodCashRepository goodCashRepository,
                           SubcategoryRepository subcategoryRepository) {
        this.goodCashRepository = goodCashRepository;
        this.subcategoryRepository = subcategoryRepository;
    }
    public List<GoodCash> findAll(){return goodCashRepository.findAll();};

    public List<GoodCash> findAll(Specification<GoodCash> spec){
        return goodCashRepository.findAll(spec);
    };

    public Integer findAmount(){return  goodCashRepository.findAmount();}

    public void save( Good data){
        GoodCash newGood = new GoodCash();
        newGood.setId(data.getId());
        newGood.setAmount(data.getAmount());
        newGood.setBrand(data.getBrand());
        newGood.setDiscount(data.getDiscount());
        newGood.setManufacturer(data.getManufacturer());
        newGood.setName(data.getName());
        newGood.setPrice(data.getPrice());
        Long subId =  new Long(data.getSubcategory().getId());
        newGood.setSubcategory(  subcategoryRepository.findById(subId.intValue()).get());
        goodCashRepository.save(newGood);
    }

    public void saveAll(List<Good>  data){
        for(int i = 0; i < data.size();i++){
            save(data.get(i));
        }
    }

    public void deleteAll(){
        goodCashRepository.deleteAll();
    }

    public Good toGood(GoodCash data){
        Good newGood = new Good();
        newGood.setId(data.getId());
        newGood.setAmount(data.getAmount());
        newGood.setBrand(data.getBrand());
        newGood.setDiscount(data.getDiscount());
        newGood.setManufacturer(data.getManufacturer());
        newGood.setName(data.getName());
        newGood.setPrice(data.getPrice());
        Long subId =  new Long(data.getSubcategory().getId());
        newGood.setSubcategory(  subcategoryRepository.findById(subId.intValue()).get());
        return newGood;
    }

    public List<Good> toGoodList(List<GoodCash> data){
        List<Good> result = new ArrayList<>();
        data.forEach(f -> result.add(toGood(f)));
        return  result;
    }

    public List<GoodCash> getSearch(String parameters){
        GoodCashSpecificationsBuilder builder = new GoodCashSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(parameters + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<GoodCash> spec = builder.build();
        return findAll(spec);
    }
}
