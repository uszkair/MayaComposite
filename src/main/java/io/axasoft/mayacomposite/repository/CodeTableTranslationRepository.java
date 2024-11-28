package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.CodeTableTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeTableTranslationRepository extends JpaRepository<CodeTableTranslation, String>, JpaSpecificationExecutor<CodeTableTranslation> {
    List<CodeTableTranslation> findByCodeTableIdAndLanguage(String codeTableId, String language);
    Optional<CodeTableTranslation> findByCodeTableIdAndLanguageAndName(String codeTableId, String language, String name);
}