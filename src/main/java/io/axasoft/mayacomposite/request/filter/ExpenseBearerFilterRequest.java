package io.axasoft.mayacomposite.request.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request model for filtering ExpenseBearers.
 */
@Data
public class ExpenseBearerFilterRequest {

    @Schema(description = "Költségviselő típusa", example = "Individual")
    private String bearerType;

    @Schema(description = "Név", example = "John Doe")
    private String name;

    @Schema(description = "Születési hely", example = "Budapest")
    private String birthPlace;

    @Schema(description = "Adóazonosító jel", example = "123456789")
    private String taxId;

    @Schema(description = "Státusz", example = "Active")
    private String status;

    @Schema(description = "Típus", example = "Residential")
    private String type;

    @Schema(description = "Kezdő dátum", example = "2023-01-01")
    private LocalDate startDate;

    @Schema(description = "Vége dátum", example = "2023-12-31")
    private LocalDate endDate;

    @Schema(description = "Csoportos beszedési limit minimum", example = "500.00")
    private BigDecimal minDirectDebitLimit;

    @Schema(description = "Csoportos beszedési limit maximum", example = "1500.00")
    private BigDecimal maxDirectDebitLimit;
}
