package io.axasoft.mayacomposite.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request model for adding an address to an ExpenseBearer.
 */
@Data
public class ExpenseBearerAddressRequest {

    @Schema(description = "Cím", example = "1234 Budapest, Fő utca 1.")
    @NotBlank(message = "{expenseBearerAddress.error.addressLine.notblank}")
    private String addressLine;

    @Schema(description = "Város", example = "Budapest")
    @NotBlank(message = "{expenseBearerAddress.error.city.notblank}")
    private String city;

    @Schema(description = "Irányítószám", example = "1234")
    @NotBlank(message = "{expenseBearerAddress.error.postalCode.notblank}")
    private String postalCode;

    @Schema(description = "Ország", example = "Magyarország")
    @NotBlank(message = "{expenseBearerAddress.error.country.notblank}")
    private String country;

    @Schema(description = "Alapértelmezett cím", example = "false")
    private Boolean defaultAddress = false;
}
