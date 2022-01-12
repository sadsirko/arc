package com.javamaster.springjpapostgres.service;

import com.javamaster.springjpapostgres.entity.Good;
import com.javamaster.springjpapostgres.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodService {
    @Autowired
    private final GoodRepository goodRepository;

    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }
    public Good findById(Long id){return  goodRepository.findById(id);}
    public List<Object> findNamePriceAll(){return  goodRepository.findNamePriceAll();}
    public List<Good> findAll(){return goodRepository.findAll();};
}
