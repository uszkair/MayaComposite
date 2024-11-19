package io.axasoft.mayacomposite.response;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

/**
 * Response model for ExpenseBearer phone number details.
 */
@Data
public class ExpenseBearerPhoneNumberResponse {

    @Schema(description = "Egyedi azonosító", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Telefonszám", example = "+36123456789")
    private String phoneNumber;
}
