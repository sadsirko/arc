package com.javamaster.springjpapostgres.service;

import com.javamaster.springjpapostgres.entity.Good;
import com.javamaster.springjpapostgres.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Good> getByPage(int page,int limit){
        int top = goodRepository.firstTop((page - 1) * limit );
        List<Good> pagin = goodRepository.getByPage(top,limit);
        return pagin;
    };

    public Integer findAmount(){return  goodRepository.findAmount();}
    public List<Good> firstPage(int limit){
        return  goodRepository.firstPage(limit);}

    public List<Good> generalPage(Long id){
        List<Good> res = new ArrayList<>();
        if (id.intValue() == 1) {
            res = firstPage(5000 );
        }
        else {
            res = getByPage(id.intValue(),5000);
        }
        return res;}

    public int firstTop(int limit){
        System.out.println(limit);
        return  goodRepository.firstTop(limit);}


}
