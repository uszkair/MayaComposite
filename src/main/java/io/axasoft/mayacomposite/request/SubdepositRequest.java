package io.axasoft.mayacomposite.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Request model egy albetét létrehozásához vagy frissítéséhez.
 * Tartalmazza az összes szükséges mezőt, validációs szabályokat, és leírásokat.
 */
@Data
public class SubdepositRequest {

    // Albetét befizető azonosítója
    @Schema(description = "Az albetét befizető azonosítója", example = "PAY12345")
    @Size(max = 50, message = "{subdeposit.error.paymentIdentifier.size}")
    private String paymentIdentifier;

    // Albetét típusa
    @Schema(description = "Az albetét típusa", example = "Residential")
    @NotBlank(message = "{subdeposit.error.type.notblank}")
    private String type;

    // Épület neve vagy azonosítója
    @Schema(description = "Az épület adatai", example = "Building A")
    private String building;

    // Lépcsőház neve vagy azonosítója
    @Schema(description = "A lépcsőház adatai", example = "Staircase 1")
    private String staircase;

    // Albetét szintje
    @Schema(description = "Az albetét szintje", example = "2")
    private String floor;

    // Egyedi azonosító
    @Schema(description = "Az albetét egyedi azonosítója", example = "SUB12345")
    @NotBlank(message = "{subdeposit.error.identifier.notblank}")
    @Size(max = 50, message = "{subdeposit.error.identifier.size}")
    private String identifier;

    // Helyrajzi szám
    @Schema(description = "Az albetét helyrajzi száma", example = "12345/67")
    private String cadastralNumber;

    // GDPR hozzájárulás
    @Schema(description = "GDPR hozzájárulás", example = "true")
    @NotNull(message = "{subdeposit.error.gdprConsent.notnull}")
    private Boolean gdprConsent = false;

    // Albetét területe négyzetméterben
    @Schema(description = "Az albetét területe négyzetméterben", example = "85.5")
    @DecimalMin(value = "0.0", inclusive = true, message = "{subdeposit.error.subdepositArea.min}")
    private BigDecimal subdepositArea;

    // Erkély területe négyzetméterben
    @Schema(description = "Az erkély területe négyzetméterben", example = "10.5")
    private BigDecimal balconyArea;

    // Kert területe négyzetméterben
    @Schema(description = "A kert területe négyzetméterben", example = "15.0")
    private BigDecimal gardenArea;

    // Fűtött terület négyzetméterben
    @Schema(description = "A fűtött terület négyzetméterben", example = "80.0")
    private BigDecimal heatedArea;

    // Tulajdoni hányad
    @Schema(description = "Tulajdoni hányad az albetétben", example = "0.100")
    private BigDecimal ownershipRatio;

    // Albetét térfogata köbméterben
    @Schema(description = "Az albetét térfogata köbméterben", example = "200.5")
    private BigDecimal volume;

    // Lakók száma
    @Schema(description = "Lakók száma az albetétben", example = "4")
    private Integer residentsCount;

    // Önkormányzati előírás
    @Schema(description = "Önkormányzati előírás", example = "false")
    @NotNull(message = "{subdeposit.error.municipalRegulation.notnull}")
    private Boolean municipalRegulation = false;

    // Vízóra felszerelve
    @Schema(description = "Vízóra felszerelve", example = "true")
    @NotNull(message = "{subdeposit.error.waterMeterInstalled.notnull}")
    private Boolean waterMeterInstalled = false;

    // Albetét hitelt fizet
    @Schema(description = "Az albetét hitelt fizet", example = "false")
    @NotNull(message = "{subdeposit.error.depositPaysLoan.notnull}")
    private Boolean depositPaysLoan = false;

    // Jelzáloggal terhelhető
    @Schema(description = "Jelzáloggal terhelhető", example = "true")
    @NotNull(message = "{subdeposit.error.mortgageable.notnull}")
    private Boolean mortgageable = false;

    // Postai csekket igényel
    @Schema(description = "Postai csekket igényel", example = "false")
    @NotNull(message = "{subdeposit.error.postalCheckRequired.notnull}")
    private Boolean postalCheckRequired = false;

    // Közös költség (általános) szorzó
    @Schema(description = "Közös költség (általános) szorzó", example = "1.00")
    @DecimalMin(value = "0.0", inclusive = true, message = "{subdeposit.error.generalCommonCostRatio.min}")
    private BigDecimal generalCommonCostRatio = BigDecimal.valueOf(1.00);

    // Célbefizetések szorzója
    @Schema(description = "Célbefizetések szorzója", example = "1.00")
    private BigDecimal targetedPaymentRatio = BigDecimal.valueOf(1.00);

    // Szemétszállítási díj szorzója
    @Schema(description = "Szemétszállítási díj szorzója", example = "1.00")
    private BigDecimal wasteManagementRatio = BigDecimal.valueOf(1.00);

    // Közös költség (csoportos) szorzó
    @Schema(description = "Közös költség (csoportos) szorzó", example = "1.00")
    private BigDecimal groupCommonCostRatio = BigDecimal.valueOf(1.00);

    // Felújítási díj szorzója
    @Schema(description = "Felújítási díj szorzója", example = "1.00")
    private BigDecimal renovationFeeRatio = BigDecimal.valueOf(1.00);

    // Villamosenergia díj szorzója
    @Schema(description = "Villamosenergia díj szorzója", example = "1.00")
    private BigDecimal electricityFeeRatio = BigDecimal.valueOf(1.00);

    // Kezelési díj szorzója
    @Schema(description = "Kezelési díj szorzója", example = "1.00")
    private BigDecimal handlingFeeRatio = BigDecimal.valueOf(1.00);

    // Takarítási díj szorzója
    @Schema(description = "Takarítási díj szorzója", example = "1.00")
    private BigDecimal cleaningFeeRatio = BigDecimal.valueOf(1.00);

    // Hitel törlesztési díj szorzója
    @Schema(description = "Hitel törlesztési díj szorzója", example = "1.00")
    private BigDecimal loanRepaymentRatio = BigDecimal.valueOf(1.00);

    @Schema(description = "Aktív állapot", example = "true")
    private Boolean isActive;
}
