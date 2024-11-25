package io.axasoft.mayacomposite.request.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * A szűrési feltételek modellje az albetétekhez.
 */
@Data
public class SubdepositFilterRequest {

    @Schema(description = "Albetét azonosító", example = "SUB123")
    private String identifier;

    @Schema(description = "Albetét típusa", example = "Residential")
    private String type;

    @Schema(description = "Helyrajzi szám", example = "123456/1")
    private String cadastralNumber;

    @Schema(description = "Épület adatai", example = "Building A")
    private String building;

    @Schema(description = "Lépcsőház adatai", example = "Staircase 1")
    private String staircase;

    @Schema(description = "Szint", example = "1")
    private String floor;

    @Schema(description = "Minimális albetét terület (m2)", example = "50.00")
    private BigDecimal minSubdepositArea;

    @Schema(description = "Maximális albetét terület (m2)", example = "200.00")
    private BigDecimal maxSubdepositArea;

    @Schema(description = "Aktív állapot", example = "true")
    private Boolean isActive;

    @Schema(description = "GDPR hozzájárulás", example = "true")
    private Boolean gdprConsent;
}
