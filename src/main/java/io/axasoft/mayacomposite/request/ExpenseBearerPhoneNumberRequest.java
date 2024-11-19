package io.axasoft.mayacomposite.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request model for adding a phone number to an ExpenseBearer.
 */
@Data
public class ExpenseBearerPhoneNumberRequest {

    @Schema(description = "Telefonszám", example = "+36123456789")
    @NotBlank(message = "{expenseBearerPhoneNumber.error.phoneNumber.notblank}")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "{expenseBearerPhoneNumber.error.phoneNumber.pattern}")
    private String phoneNumber;
}
