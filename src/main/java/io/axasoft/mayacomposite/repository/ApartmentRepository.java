package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, String>, JpaSpecificationExecutor<Apartment> {

    /**
     * Retrieves a paginated list of active apartments.
     *
     * @param pageable Pagination parameters
     * @return A page of active apartments
     */
    Page<Apartment> findByIsActiveTrue(Pageable pageable);

    /**
     * Checks if an apartment with the given identifier already exists.
     *
     * @param apartmentIdentifier The identifier of the apartment
     * @return true if it exists, false otherwise
     */
    boolean existsByApartmentIdentifier(String apartmentIdentifier);
}
