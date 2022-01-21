package com.javamaster.springjpapostgres.persistence.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.javamaster.springjpapostgres.business.service.GoodService;
import com.javamaster.springjpapostgres.persistence.entity.Client;
import com.javamaster.springjpapostgres.persistence.entity.Good;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoodResolver implements GraphQLQueryResolver {
    private final GoodService goodService;
    public Good good(Long id){
        System.out.println(id);
        return goodService.findById(id);
    }
}
