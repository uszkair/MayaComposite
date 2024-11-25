package io.axasoft.mayacomposite.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Response model for Subdeposit.
 *
 * This DTO is used to return Subdeposit information in API responses.
 *
 * Author: RobertUszkai
 */
@Data
public class SubdepositResponse {

    @Schema(description = "Egyedi azonosító az albetéthez", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Befizető azonosító az albetéthez", example = "PAY123456")
    private String paymentIdentifier;

    @Schema(description = "Albetét típusa", example = "Residential")
    private String type;

    @Schema(description = "Épület", example = "A")
    private String building;

    @Schema(description = "Lépcsőház", example = "1")
    private String staircase;

    @Schema(description = "Szint", example = "2")
    private String floor;

    @Schema(description = "Azonosító", example = "SD12345")
    private String identifier;

    @Schema(description = "Helyrajzi szám", example = "HR123456")
    private String cadastralNumber;

    @Schema(description = "GDPR hozzájárulás", example = "true")
    private Boolean gdprConsent;

    @Schema(description = "Albetét területe négyzetméterben", example = "75.50")
    private BigDecimal subdepositArea;

    @Schema(description = "Erkély területe négyzetméterben", example = "10.00")
    private BigDecimal balconyArea;

    @Schema(description = "Kert területe négyzetméterben", example = "25.00")
    private BigDecimal gardenArea;

    @Schema(description = "Fűtött terület négyzetméterben", example = "60.00")
    private BigDecimal heatedArea;

    @Schema(description = "Tulajdoni hányad", example = "0.125")
    private BigDecimal ownershipRatio;

    @Schema(description = "Albetét térfogata köbméterben", example = "300.00")
    private BigDecimal volume;

    @Schema(description = "Lakók száma az albetétben", example = "4")
    private Integer residentsCount;

    @Schema(description = "Önkormányzati előírás", example = "true")
    private Boolean municipalRegulation;

    @Schema(description = "Vízóra felszerelve", example = "false")
    private Boolean waterMeterInstalled;

    @Schema(description = "Hitelt fizet-e az albetét", example = "true")
    private Boolean depositPaysLoan;

    @Schema(description = "Jelzáloggal terhelhető", example = "false")
    private Boolean mortgageable;

    @Schema(description = "Postai csekket igényel", example = "true")
    private Boolean postalCheckRequired;

    @Schema(description = "Közös költség (általános) szorzó", example = "1.00")
    private BigDecimal generalCommonCostRatio;

    @Schema(description = "Célbefizetések szorzója", example = "1.00")
    private BigDecimal targetedPaymentRatio;

    @Schema(description = "Szemétszállítási díj szorzója", example = "1.00")
    private BigDecimal wasteManagementRatio;

    @Schema(description = "Közös költség (csoportos) szorzó", example = "1.00")
    private BigDecimal groupCommonCostRatio;

    @Schema(description = "Felújítási díj szorzója", example = "1.00")
    private BigDecimal renovationFeeRatio;

    @Schema(description = "Villamosenergia díj szorzója", example = "1.00")
    private BigDecimal electricityFeeRatio;

    @Schema(description = "Kezelési díj szorzója", example = "1.00")
    private BigDecimal handlingFeeRatio;

    @Schema(description = "Takarítási díj szorzója", example = "1.00")
    private BigDecimal cleaningFeeRatio;

    @Schema(description = "Hitel törlesztési szorzó", example = "1.00")
    private BigDecimal loanRepaymentRatio;

    @Schema(description = "Albetét aktív státusza", example = "true")
    private Boolean isActive;

    @Schema(description = "Létrehozó felhasználó", example = "admin")
    private String createdBy;

    @Schema(description = "Létrehozás dátuma", example = "2024-11-25T12:34:56")
    private String createdDate;

    @Schema(description = "Utolsó módosító felhasználó", example = "editor")
    private String lastModifiedBy;

    @Schema(description = "Utolsó módosítás dátuma", example = "2024-11-25T14:34:56")
    private String lastModifiedDate;
}
