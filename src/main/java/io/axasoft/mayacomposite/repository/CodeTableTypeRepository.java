package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.CodeTableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeTableTypeRepository extends JpaRepository<CodeTableType, String>, JpaSpecificationExecutor<CodeTableType> {
    Optional<CodeTableType> findByCode(String code);
    boolean existsByCode(String code);
}