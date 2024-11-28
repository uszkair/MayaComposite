package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.CodeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeTableRepository extends JpaRepository<CodeTable, String>, JpaSpecificationExecutor<CodeTable> {
    List<CodeTable> findByTypeCodeAndIsActiveTrue(String typeCode);
    Optional<CodeTable> findByTypeCodeAndCode(String typeCode, String code);
    boolean existsByTypeCodeAndCode(String typeCode, String code);
}