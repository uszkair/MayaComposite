package io.axasoft.mayacomposite.mapper;

import io.axasoft.mayacomposite.model.Subdeposit;
import io.axasoft.mayacomposite.request.SubdepositRequest;
import io.axasoft.mayacomposite.response.SubdepositResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * MapStruct mapper interface for converting Subdeposit entity and DTOs.
 *
 * Author: RobertUszkai
 */
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SubdepositMapper {

    /**
     * Converts a SubdepositRequest DTO to a Subdeposit entity.
     */
    @Mapping(target = "id", ignore = true) // ID is auto-generated
    @Mapping(target = "createdBy", ignore = true) // CreatedBy is handled by auditing
    @Mapping(target = "createdDate", ignore = true) // CreatedDate is handled by auditing
    @Mapping(target = "lastModifiedBy", ignore = true) // LastModifiedBy is handled by auditing
    @Mapping(target = "lastModifiedDate", ignore = true) // LastModifiedDate is handled by auditing
    Subdeposit toEntity(SubdepositRequest request);

    /**
     * Converts a Subdeposit entity to a SubdepositResponse DTO.
     */
    SubdepositResponse toResponse(Subdeposit subdeposit);

    /**
     * Updates an existing Subdeposit entity with non-null fields from a SubdepositRequest.
     *
     * @param dto The SubdepositRequest containing the fields to update.
     * @param entity The Subdeposit entity to be updated.
     */
    void updateSubdepositFromDto(SubdepositRequest dto, @MappingTarget Subdeposit entity);
}
