package io.axasoft.mayacomposite.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Társasház létrehozásához használt request modell.
 */
@Data
public class ApartmentRequest {

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

    @Schema(description = "Közterület neve és házszám", example = "Fő utca 10.")
    @NotBlank(message = "{apartment.error.streetandnumber.notblank}")
    private String streetAndNumber;

    @Schema(description = "Bázis cím, albetétek címéhez", example = "Fő utca 10. albetétek")
    private String baseAddress;

    @Schema(description = "Társasház dedikált e-mail címe", example = "napfeny@tarsashaz.hu")
    @Email(message = "{apartment.error.email.invalid}")
    private String dedicatedEmail;

    @Schema(description = "Helyrajzi szám", example = "12345/678")
    private String cadastralNumber;

    @Schema(description = "Adószám", example = "12345678-1-23")
    @Pattern(regexp = "\\d{8}-\\d-\\d{2}", message = "{apartment.error.taxnumber.pattern}")
    private String taxNumber;

    @Schema(description = "Lakásszövetkezet", example = "false")
    private Boolean isHousingCooperative = false;

    @Schema(description = "Adatrögzítés indítása (hónap)", example = "2024-01")
    @Pattern(regexp = "\\d{4}-\\d{2}", message = "{apartment.error.dataentry.date.format}")
    private String dataEntryStartMonth;

    @Schema(description = "Lakói hozzáférés indítása (hónap)", example = "2024-02")
    @Pattern(regexp = "\\d{4}-\\d{2}", message = "{apartment.error.residentaccess.date.format}")
    private String residentAccessStartMonth;
}
