package io.axasoft.mayacomposite.specification;

import io.axasoft.mayacomposite.model.Subdeposit;
import io.axasoft.mayacomposite.request.filter.SubdepositFilterRequest;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;

/**
 * Specification class for building dynamic filters for Subdeposit entities.
 */
public class SubdepositSpecifications {

    public static Specification<Subdeposit> build(SubdepositFilterRequest filterRequest) {
        return (root, query, criteriaBuilder) -> {
            Specification<Subdeposit> specification = Specification.where(null);

            if (filterRequest.getIdentifier() != null && !filterRequest.getIdentifier().isEmpty()) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.like(root1.get("identifier"), "%" + filterRequest.getIdentifier() + "%")
                );
            }

            if (filterRequest.getType() != null && !filterRequest.getType().isEmpty()) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.equal(root1.get("type"), filterRequest.getType())
                );
            }

            if (filterRequest.getCadastralNumber() != null && !filterRequest.getCadastralNumber().isEmpty()) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.equal(root1.get("cadastralNumber"), filterRequest.getCadastralNumber())
                );
            }

            if (filterRequest.getBuilding() != null && !filterRequest.getBuilding().isEmpty()) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.equal(root1.get("building"), filterRequest.getBuilding())
                );
            }

            if (filterRequest.getStaircase() != null && !filterRequest.getStaircase().isEmpty()) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.equal(root1.get("staircase"), filterRequest.getStaircase())
                );
            }

            if (filterRequest.getFloor() != null && !filterRequest.getFloor().isEmpty()) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.equal(root1.get("floor"), filterRequest.getFloor())
                );
            }

            if (filterRequest.getMinSubdepositArea() != null) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.greaterThanOrEqualTo(root1.get("subdepositArea"), filterRequest.getMinSubdepositArea())
                );
            }

            if (filterRequest.getMaxSubdepositArea() != null) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.lessThanOrEqualTo(root1.get("subdepositArea"), filterRequest.getMaxSubdepositArea())
                );
            }

            if (filterRequest.getIsActive() != null) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.equal(root1.get("isActive"), filterRequest.getIsActive())
                );
            }

            if (filterRequest.getGdprConsent() != null) {
                specification = specification.and(
                        (root1, query1, criteriaBuilder1) ->
                                criteriaBuilder1.equal(root1.get("gdprConsent"), filterRequest.getGdprConsent())
                );
            }

            // Default ordering
            if (query.getOrderList().isEmpty()) {
                Order defaultOrder = criteriaBuilder.asc(root.get("identifier"));
                query.orderBy(Collections.singletonList(defaultOrder));
            }

            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}
