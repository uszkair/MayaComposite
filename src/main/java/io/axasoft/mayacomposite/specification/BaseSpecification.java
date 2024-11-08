package io.axasoft.mayacomposite.specification;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * Base class for Specifications to provide common methods for handling filtering and Pageable.
 */
public abstract class BaseSpecification<T,F> {

    public Pageable pageable;

    /**
     * Constructor to initialize Pageable and apply default sorting if necessary.
     *
     * @param pageable         The pageable object to use for pagination and sorting
     * @param defaultSortColumn The default column to sort by if no sorting is provided
     */
    protected BaseSpecification(Pageable pageable, String defaultSortColumn) {
        this.pageable = applyDefaultSort(pageable, defaultSortColumn);
    }

    /**
     * Applies default sorting to the Pageable if no sort is provided.
     *
     * @param pageable         The pageable object
     * @param defaultSortColumn The default column to sort by
     * @return A pageable object with default sorting applied
     */
    private Pageable applyDefaultSort(Pageable pageable, String defaultSortColumn) {
        if (pageable.getSort().isUnsorted()) {
            return PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(defaultSortColumn).ascending()
            );
        }
        return pageable;
    }

    /**
     * Abstract method for building the specification based on the filter request.
     *
     * @param filterRequest The filtering criteria
     * @return A Specification object
     */
    public abstract Specification<T> buildSpecification(F filterRequest);
}
