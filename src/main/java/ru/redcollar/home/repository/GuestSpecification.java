package ru.redcollar.home.repository;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import ru.redcollar.home.entity.Guest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class GuestSpecification {

    private Boolean isStudent;
    private Long ageTo;
    private Long ageFrom;

    public Specification<Guest> buildSpec() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isStudent != null) {
                predicates.add(criteriaBuilder.equal(root.get("isStudent"), isStudent));
            }
            if (ageTo != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("age"), ageTo));
            }
            if (ageFrom != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), ageFrom));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
