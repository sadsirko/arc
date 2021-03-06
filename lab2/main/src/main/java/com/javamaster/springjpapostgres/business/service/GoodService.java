package com.javamaster.springjpapostgres.business.service;

import com.javamaster.springjpapostgres.persistence.entity.Good;
import com.javamaster.springjpapostgres.persistence.entity.GoodCash;
import com.javamaster.springjpapostgres.persistence.repository.GoodRepository;
import com.javamaster.springjpapostgres.persistence.repository.SubcategoryRepository;
import com.javamaster.springjpapostgres.business.service.specification.GoodCashSpecificationsBuilder;
import com.javamaster.springjpapostgres.business.service.specification.GoodSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GoodService {
    @Autowired
    private final GoodRepository goodRepository;
    @Autowired
    private final GoodCashService goodCashService;
    @Autowired
    private final SubcategoryRepository subcategoryRepository;

    public GoodService(GoodRepository goodRepository,
                       GoodCashService goodCashService, SubcategoryRepository subcategoryRepository) {
        this.goodRepository = goodRepository;
        this.goodCashService = goodCashService;
        this.subcategoryRepository = subcategoryRepository;
    }



    public Good findById(Long id){return  goodRepository.findById(id);}

    public List<Good> findAll(){return goodRepository.findAll();};

    public List<Good> findAll(Specification<Good> criteria){return goodRepository.findAll(criteria);};

    public void saveList( List<Good> data){
        for(int i = 0; i < data.size();i++){
            goodRepository.save(data.get(i));
        }
    }

    public void save( Good data){
            Good newGood = new Good();
            newGood.setAmount(data.getAmount());
            newGood.setBrand(data.getBrand());
            newGood.setDiscount(data.getDiscount());
            newGood.setManufacturer(data.getManufacturer());
            newGood.setName(data.getName());
            newGood.setPrice(data.getPrice());
            Long subId =  new Long(data.getSubcategory().getId());
            newGood.setSubcategory(  subcategoryRepository.findById(subId.intValue()).get());
            goodRepository.save(newGood);
    }

    public void update( Good data){
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
            goodRepository.save(newGood);
    }

    @Transactional
    public void delete( Long id){
        goodRepository.deleteById( id);
    }

    public List<Good> getProvider2All() {
        final String url = "http://localhost:8082/price-list";
        RestTemplate restTemplate = new RestTemplate();
        Object[] good = restTemplate.getForObject(url, Object[].class);
        List<Good> detailedGoods = new ArrayList<>();
        for (int i = 0; i < good.length ;i++){
            detailedGoods.add(getDetailedProvider2(Integer.parseInt(good[i]
                    .toString()
                    .substring(1,good[i].toString().indexOf(",")))));
        }

        return detailedGoods;
    }

    public List<Good> getProvider2Search(String search){
        saveToCash(getProvider2All());
        GoodCashSpecificationsBuilder builder = new GoodCashSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<GoodCash> spec = builder.build();
        List<Good> result = new ArrayList<>();
        List<GoodCash> secondProviderFull = goodCashService.findAll(spec);
        for(int i = 0; i < secondProviderFull.size();i++){
            result.add(goodCashService.toGood(secondProviderFull.get(i)));
        }
        return result;

    }
    public List<Good> getProvider1All(String query) {
        final String url = "http://localhost:8081/search?query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        Good[] good = restTemplate.getForObject(url,Good[].class);
        List<Good> provider1 = Arrays.asList(good);
        return provider1;
    }

    public Good getDetailedProvider2(int id){
        final String url = "http://localhost:8082/details/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Good good = restTemplate.getForObject(url, Good.class);
        return good;
    }

    public List<Good> getFullGood(){
        List<Good> provider1 = getProvider1All("");
        List<Good> provider2 = getProvider2All();
        List<Good> mainData = goodRepository.findAll();
        List<Good> result  = Stream.concat(mainData.stream(), provider1.stream())
                .collect(Collectors.toList());
        result = Stream.concat(result.stream(), provider2.stream())
                .collect(Collectors.toList());
        return result;
    }

    public List<Good> getFullSearch(String parameters){
        System.out.println("http://localhost:8081/search?query=" + parameters);
        List<Good> provider1 = getProvider1All(parameters);
        List<Good> provider2 = getProvider2Search(parameters);
        List<Good> main = getSearch(parameters);
        List<Good> result  = Stream.concat(main.stream(), provider1.stream())
                .collect(Collectors.toList());
        result = Stream.concat(result.stream(), provider2.stream())
                .collect(Collectors.toList());
        return result;
    }

    public List<Good> getSearch(String parameters){
        GoodSpecificationsBuilder builder = new GoodSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(parameters + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<Good> spec = builder.build();
        return findAll(spec);
    }

    public void saveToCash(List<Good> goodToCash){
        goodCashService.deleteAll();
        goodCashService.saveAll(goodToCash);
    }
}
