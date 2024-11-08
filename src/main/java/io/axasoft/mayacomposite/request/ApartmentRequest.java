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
    @NotBlank(message = "A társasház neve nem lehet üres")
    private String name;

    @Schema(description = "Irányítószám", example = "1234")
    @Pattern(regexp = "\\d{4}", message = "Az irányítószám 4 számjegyből kell álljon")
    private String postalCode;

    @Schema(description = "Település", example = "Budapest")
    @NotBlank(message = "A település neve nem lehet üres")
    private String city;

    @Schema(description = "Közterület neve és házszám", example = "Fő utca 10.")
    @NotBlank(message = "A közterület neve és házszám nem lehet üres")
    private String streetAndNumber;

    @Schema(description = "Bázis cím, albetétek címéhez", example = "Fő utca 10. albetétek")
    private String baseAddress;

    @Schema(description = "Társasház dedikált e-mail címe", example = "napfeny@tarsashaz.hu")
    @Email(message = "Érvénytelen email cím formátum")
    private String dedicatedEmail;

    @Schema(description = "Helyrajzi szám", example = "12345/678")
    private String cadastralNumber;

    @Schema(description = "Adószám", example = "12345678-1-23")
    @Pattern(regexp = "\\d{8}-\\d-\\d{2}", message = "Az adószám formátuma nem megfelelő (########-#-##)")
    private String taxNumber;

    @Schema(description = "Lakásszövetkezet", example = "false")
    private Boolean isHousingCooperative = false;

    @Schema(description = "Adatrögzítés indítása (hónap)", example = "2024-01")
    @Pattern(regexp = "\\d{4}-\\d{2}", message = "A dátum formátuma nem megfelelő (YYYY-MM)")
    private String dataEntryStartMonth;

    @Schema(description = "Lakói hozzáférés indítása (hónap)", example = "2024-02")
    @Pattern(regexp = "\\d{4}-\\d{2}", message = "A dátum formátuma nem megfelelő (YYYY-MM)")
    private String residentAccessStartMonth;
}
