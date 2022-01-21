package com.javamaster.springjpapostgres.persistence.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.javamaster.springjpapostgres.persistence.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientResolver implements GraphQLQueryResolver {
    public Client client(Integer id){
        System.out.println(id);
        Client cl = new Client();
        cl.setId(1);
        cl.setNameSurname("asd");
        cl.setPhone("asd");
        return cl;
    }
}
