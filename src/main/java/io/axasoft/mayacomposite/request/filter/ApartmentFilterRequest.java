package io.axasoft.mayacomposite.request.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Társasház létrehozásához használt request modell.
 */
@Data
public class ApartmentFilterRequest {

    @Schema(description = "Társasház azonosító (opcionális, belső használatra)", example = "APT-12345")
    private String apartmentIdentifier;

    @Schema(description = "Társasház neve", example = "Napfény Társasház")
    @NotBlank(message = "{apartment.error.name.notblank}")
    private String name;

    @Schema(description = "Irányítószám", example = "1234")
    @Pattern(regexp = "\\d{4}", message = "{apartment.error.postalcode.pattern}")
    private String postalCode;

    @Schema(description = "Település", example = "Budapest")
    @NotBlank(message = "{apartment.error.city.notblank}")
    private String city;
}
