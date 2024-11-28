package io.axasoft.mayacomposite.mapper;

import io.axasoft.mayacomposite.model.CodeTable;
import io.axasoft.mayacomposite.model.CodeTableTranslation;
import io.axasoft.mayacomposite.model.CodeTableType;
import io.axasoft.mayacomposite.model.CodeTableTypeTranslation;
import io.axasoft.mayacomposite.request.CodeTableTranslationRequest;
import io.axasoft.mayacomposite.request.CodeTableTypeRequest;
import io.axasoft.mayacomposite.request.CodeTableTypeTranslationRequest;
import io.axasoft.mayacomposite.request.CodeTableValueRequest;
import io.axasoft.mayacomposite.response.CodeTableResponse;
import io.axasoft.mayacomposite.response.CodeTableTranslationResponse;
import io.axasoft.mayacomposite.response.CodeTableTypeResponse;
import io.axasoft.mayacomposite.response.CodeTableTypeTranslationResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CodeTableMapper {

    // CodeTableType mappings
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "translations", ignore = true)
    @Mapping(target = "codeTables", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    CodeTableType toTypeEntity(CodeTableTypeRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codeTableType", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    CodeTableTypeTranslation toTypeTranslationEntity(CodeTableTypeTranslationRequest request);

    @Mapping(target = "translations", source = "translations")
    @Mapping(target = "values", source = "codeTables")
    CodeTableTypeResponse toTypeResponse(CodeTableType type);

    CodeTableTypeTranslationResponse toTypeTranslationResponse(CodeTableTypeTranslation translation);

    // CodeTable mappings
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "translations", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    CodeTable toEntity(CodeTableValueRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codeTable", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    CodeTableTranslation toTranslationEntity(CodeTableTranslationRequest request);

    @Mapping(target = "typeCode", source = "type.code")
    CodeTableResponse toResponse(CodeTable codeTable);

    CodeTableTranslationResponse toTranslationResponse(CodeTableTranslation translation);

    @AfterMapping
    default void mapTypeTranslations(@MappingTarget CodeTableTypeResponse target, CodeTableType source) {
        if (source.getTranslations() != null) {
            target.setTranslations(source.getTranslations().stream()
                    .map(this::toTypeTranslationResponse)
                    .toList());
        }

        if (source.getCodeTables() != null) {
            target.setValues(source.getCodeTables().stream()
                    .map(this::toResponse)
                    .toList());
        }
    }

    @AfterMapping
    default void mapTranslations(@MappingTarget CodeTableResponse target, CodeTable source) {
        if (source.getTranslations() != null) {
            target.setTranslations(source.getTranslations().stream()
                    .map(this::toTranslationResponse)
                    .toList());
        }
    }
}