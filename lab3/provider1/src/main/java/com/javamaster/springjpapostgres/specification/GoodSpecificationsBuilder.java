package com.javamaster.springjpapostgres.specification;

import antlr.StringUtils;
import com.javamaster.springjpapostgres.entity.Good;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GoodSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public GoodSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public GoodSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Good> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(GoodSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);
        for (int i = 1; i < params.size(); i++) {
            System.out.println(params.get(i).getOperation());
            result = Specification.where(result)
                    .and(specs.get(i));
//                    params.get(i)
//                    .isOrPredicate()
//                    ? Specification.where(result)
//                    .or(specs.get(i))
//                    :

        }
        return result;
    }
}
