package io.axasoft.mayacomposite.response;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

/**
 * Response model for ExpenseBearer address details.
 */
@Data
public class ExpenseBearerAddressResponse {

    @Schema(description = "Egyedi azonosító", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Cím", example = "1234 Budapest, Fő utca 1.")
    private String addressLine;

    @Schema(description = "Város", example = "Budapest")
    private String city;

    @Schema(description = "Irányítószám", example = "1234")
    private String postalCode;

    @Schema(description = "Ország", example = "Magyarország")
    private String country;

    @Schema(description = "Alapértelmezett cím", example = "false")
    private Boolean defaultAddress;
}
