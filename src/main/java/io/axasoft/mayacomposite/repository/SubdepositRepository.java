package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.Subdeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository for managing Subdeposit entities.
 */
public interface SubdepositRepository extends JpaRepository<Subdeposit, String>, JpaSpecificationExecutor<Subdeposit> {
}
