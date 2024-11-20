package io.axasoft.mayacomposite.specification;

import io.axasoft.mayacomposite.model.ExpenseBearer;
import io.axasoft.mayacomposite.request.filter.ExpenseBearerFilterRequest;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;

public class ExpenseBearerSpecifications {

    public static Specification<ExpenseBearer> build(ExpenseBearerFilterRequest filterRequest) {
        return (root, query, criteriaBuilder) -> {
            Specification<ExpenseBearer> specification = Specification.where(null);

            if (filterRequest.getName() != null && !filterRequest.getName().isEmpty()) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.like(root1.get("name"), "%" + filterRequest.getName() + "%")
                );
            }

            if (filterRequest.getBearerType() != null && !filterRequest.getBearerType().isEmpty()) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.equal(root1.get("bearerType"), filterRequest.getBearerType())
                );
            }

            if (filterRequest.getStatus() != null && !filterRequest.getStatus().isEmpty()) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.equal(root1.get("status"), filterRequest.getStatus())
                );
            }

            if (filterRequest.getStartDate() != null) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.greaterThanOrEqualTo(root1.get("startDate"), filterRequest.getStartDate())
                );
            }

            if (filterRequest.getEndDate() != null) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.lessThanOrEqualTo(root1.get("endDate"), filterRequest.getEndDate())
                );
            }

            if (filterRequest.getMinDirectDebitLimit() != null) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.greaterThanOrEqualTo(root1.get("directDebitLimit"), filterRequest.getMinDirectDebitLimit())
                );
            }

            if (filterRequest.getMaxDirectDebitLimit() != null) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.lessThanOrEqualTo(root1.get("directDebitLimit"), filterRequest.getMaxDirectDebitLimit())
                );
            }

            // Add default sort
            if (query.getOrderList().isEmpty()) {
                Order defaultOrder = criteriaBuilder.asc(root.get("name")); // Replace "name" with your default sorting field
                query.orderBy(Collections.singletonList(defaultOrder));
            }

            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}
