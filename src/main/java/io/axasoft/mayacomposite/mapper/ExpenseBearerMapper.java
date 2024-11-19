package io.axasoft.mayacomposite.mapper;

import io.axasoft.mayacomposite.model.ExpenseBearer;
import io.axasoft.mayacomposite.request.ExpenseBearerRequest;
import io.axasoft.mayacomposite.response.ExpenseBearerResponse;
import org.mapstruct.*;

/**
 * MapStruct mapper interface for converting ExpenseBearer entity and DTOs.
 * 
 * Author: RobertUszkai
 */
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ExpenseBearerMapper {

    /**
     * Converts a request object to an entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    ExpenseBearer toEntity(ExpenseBearerRequest request);

    /**
     * Converts an entity to a response object.
     */
    ExpenseBearerResponse toResponse(ExpenseBearer expenseBearer);
}
