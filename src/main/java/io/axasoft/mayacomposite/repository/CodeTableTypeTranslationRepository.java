package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.CodeTableTypeTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeTableTypeTranslationRepository extends JpaRepository<CodeTableTypeTranslation, String>, JpaSpecificationExecutor<CodeTableTypeTranslation> {
    List<CodeTableTypeTranslation> findByCodeTableTypeIdAndLanguage(String typeId, String language);
    Optional<CodeTableTypeTranslation> findByCodeTableTypeIdAndLanguageAndName(String typeId, String language, String name);
}