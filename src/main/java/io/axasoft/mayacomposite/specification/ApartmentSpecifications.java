package io.axasoft.mayacomposite.specification;

import io.axasoft.mayacomposite.model.Apartment;
import io.axasoft.mayacomposite.request.ApartmentFilterRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.data.domain.Pageable;

/**
 * Specification class for building apartment filtering logic.
 */
public class ApartmentSpecifications extends BaseSpecification<Apartment,ApartmentFilterRequest> {

    /**
     * Constructor to initialize Pageable and apply default sorting.
     *
     * @param pageable The pageable object
     */
    public ApartmentSpecifications(Pageable pageable) {
        super(pageable, "name"); // Default sort column is "name"
    }

    @Override
    public Specification<Apartment> buildSpecification(ApartmentFilterRequest filterRequest) {
        ApartmentFilterRequest request = filterRequest;

        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();

            // Filter by apartmentIdentifier if provided
            if (StringUtils.hasText(request.getApartmentIdentifier())) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("apartmentIdentifier"), request.getApartmentIdentifier()));
            }

            if (StringUtils.hasText(request.getCity())) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("city"), request.getCity()));
            }

            if (StringUtils.hasText(request.getPostalCode())) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("postalCode"), request.getPostalCode()));
            }
            // Filter by name if provided
            if (StringUtils.hasText(request.getName())) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%"));
            }

            return predicate;
        };
    }
}
