package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, String>, JpaSpecificationExecutor<Apartment> {
    /**
     * Aktív társasházak lekérdezése.
     *
     * @param pageable Lapozási paraméterek
     * @return Aktív társasházak listája
     */
    Page<Apartment> findByIsActiveTrue(Pageable pageable);

    /**
     * Ellenőrzi, hogy létezik-e már az adott azonosítóval társasház.
     *
     * @param apartmentIdentifier A társasház azonosítója
     * @return true ha létezik, false ha nem
     */
    boolean existsByApartmentIdentifier(String apartmentIdentifier);
}