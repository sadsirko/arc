package com.javamaster.springjpapostgres.persistence.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.javamaster.springjpapostgres.persistence.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMutationResolver implements GraphQLMutationResolver {
    public Client client(Integer id){
        System.out.println(id);
        return new Client();
    }
}
