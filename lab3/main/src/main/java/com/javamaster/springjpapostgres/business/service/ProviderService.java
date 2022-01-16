package com.javamaster.springjpapostgres.business.service;

import com.javamaster.springjpapostgres.persistence.entity.Good;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProviderService {

    @Async
    public CompletableFuture<List<Good>> getProvider1All() {
        final String url = "http://localhost:8061/good";
        RestTemplate restTemplate = new RestTemplate();
        Good[] good = restTemplate.getForObject(url,Good[].class);
        List<Good> provider1 = Arrays.asList(good);
        return CompletableFuture.completedFuture(provider1);
    }

    @Async
    public CompletableFuture<List<Good>> getProvider2All() {
        int perPage = 5000;
        int rowsAmount = getProvider2Amount();
//        System.out.println(rowsAmount);
        int pages = rowsAmount /perPage;
        if (rowsAmount % perPage != 0) pages++;
        List<Good> result = new ArrayList<>();
        for (Integer i = 1;i <= pages;i++) {
//            System.out.println(i);
            final String url = "http://localhost:8062/page/" + i.toString();
            RestTemplate restTemplate = new RestTemplate();
            Good[] good = restTemplate.getForObject(url, Good[].class);
            List<Good> provider2 = Arrays.asList(good);
            result = Stream.concat(result.stream(), provider2.stream())
                    .collect(Collectors.toList());
        }
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<List<Good>> getProviderPage(Integer page) {
        final String url = "http://localhost:8062/page/" + page.toString();
        RestTemplate restTemplate = new RestTemplate();
        Good[] good = restTemplate.getForObject(url,Good[].class);
        List<Good> provider1 = Arrays.asList(good);
        return CompletableFuture.completedFuture(provider1);
    }

    public Integer getProvider2Amount() {
        final String url = "http://localhost:8062/amount";
        RestTemplate restTemplate = new RestTemplate();
        Integer amount = restTemplate.getForObject(url,int.class);
        return amount;
    }

}
