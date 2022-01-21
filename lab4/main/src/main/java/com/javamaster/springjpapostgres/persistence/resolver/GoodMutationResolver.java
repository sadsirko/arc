package com.javamaster.springjpapostgres.persistence.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.javamaster.springjpapostgres.persistence.entity.Good;
import org.springframework.stereotype.Component;

@Component
public class GoodMutationResolver implements GraphQLMutationResolver {
    public Good createGood(String name, Float price, Integer amount, String brand, String manufacturer, Integer discount){
        Good cl = new Good();
        cl.setId(1L);
        return cl;
    }
}
