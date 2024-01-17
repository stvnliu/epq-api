package me.imsonmia.epqapi.view;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class MessageSpecification implements Specification {

    @Override
    @Nullable
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder builder) {
        // TODO Auto-generated method stub

        return null;
    }
}
