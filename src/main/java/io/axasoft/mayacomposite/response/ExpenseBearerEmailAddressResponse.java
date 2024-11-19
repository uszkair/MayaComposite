package io.axasoft.mayacomposite.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Response model for ExpenseBearer email address details.
 */
@Data
public class ExpenseBearerEmailAddressResponse {

    @Schema(description = "Egyedi azonosító", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "E-mail cím", example = "john.doe@example.com")
    private String emailAddress;
}
