package com.javamaster.springjpapostgres.business;

import com.javamaster.springjpapostgres.business.service.GoodCashService;
import com.javamaster.springjpapostgres.business.service.GoodService;
import com.javamaster.springjpapostgres.persistence.entity.Good;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class GoodServiceFacadeImpl implements GoodServiceFacade {

    private final GoodService goodService;
    private final GoodCashService goodCashService;

    public GoodServiceFacadeImpl(GoodService goodService, GoodCashService goodCashService) {
        this.goodService = goodService;
        this.goodCashService = goodCashService;
    }

    @Override
    public List<Good> search(String query) {
        return goodService.getSearch(query);
    }

    @Override
    public void create(Good good) {
        goodService.save(good);
    }

    @Override
    public List<Good> read() {
        return goodService.findAll();
    }

    @Override
    public void update(Good good) {
        goodService.update(good);
    }

    @Override
    public void delete(Long id) {
        goodService.delete(id);
    }

    @Override
    public List<Good> fullSearch(String query) {
        List<Good> provider1 = goodService.getProvider1All(query);
        List<Good> provider2 = goodService.getProvider2Search(query);
        List<Good> main = goodService.getSearch(query);
        List<Good> result  = Stream.concat(main.stream(), provider1.stream())
                .collect(Collectors.toList());
        result = Stream.concat(result.stream(), provider2.stream())
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Good> getFullAll() {
        List<Good> provider1 = goodService.getProvider1All("");
        List<Good> provider2 = goodService.getProvider2All();
        List<Good> mainData = goodService.findAll();
        List<Good> result  = Stream.concat(mainData.stream(), provider1.stream())
                .collect(Collectors.toList());
        result = Stream.concat(result.stream(), provider2.stream())
                .collect(Collectors.toList());
        return result;
    }
}
