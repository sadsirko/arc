package com.javamaster.springjpapostgres.business.specification;

import com.javamaster.springjpapostgres.persistence.entity. GoodCash;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GoodCashSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public GoodCashSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public GoodCashSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification< GoodCash> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map( GoodCashSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);
        for (int i = 1; i < params.size(); i++) {
            System.out.println(params.get(i).getOperation());
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
