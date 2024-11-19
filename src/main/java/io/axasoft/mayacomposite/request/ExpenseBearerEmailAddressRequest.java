package io.axasoft.mayacomposite.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request model for adding an email address to an ExpenseBearer.
 */
@Data
public class ExpenseBearerEmailAddressRequest {

    @Schema(description = "E-mail cím", example = "john.doe@example.com")
    @NotBlank(message = "{expenseBearerEmailAddress.error.emailAddress.notblank}")
    @Email(message = "{expenseBearerEmailAddress.error.emailAddress.email}")
    private String emailAddress;
}
