package io.axasoft.mayacomposite.response;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Response model for ExpenseBearer details.
 */
@Data
public class ExpenseBearerResponse {

    @Schema(description = "Egyedi azonosító", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Költségviselő típusa", example = "Individual")
    private String bearerType;

    @Schema(description = "Név", example = "John Doe")
    private String name;

    @Schema(description = "Születési név", example = "John Smith")
    private String birthName;

    @Schema(description = "Anyja neve", example = "Jane Smith")
    private String motherName;

    @Schema(description = "Születési év", example = "1980")
    private Integer birthYear;

    @Schema(description = "Születési idő", example = "1980-01-01")
    private LocalDate birthDate;

    @Schema(description = "Születési hely", example = "Budapest")
    private String birthPlace;

    @Schema(description = "Adóazonosító jel", example = "123456789")
    private String taxId;

    @Schema(description = "Személyi szám", example = "12345678")
    private String personalId;

    @Schema(description = "Személyi igazolvány szám", example = "ID123456")
    private String idCardNumber;

    @Schema(description = "Értesítések nyelve", example = "hu")
    private String notificationLanguage;

    @Schema(description = "IBAN használata", example = "false")
    private Boolean useIban;

    @Schema(description = "E-mail értesítések", example = "true")
    private Boolean emailNotifications;

    @Schema(description = "Nyomtatott értesítések", example = "true")
    private Boolean printNotifications;

    @Schema(description = "Csoportos beszedési azonosító", example = "DD123456")
    private String directDebitId;

    @Schema(description = "Bank neve", example = "OTP Bank")
    private String bankName;

    @Schema(description = "Bankszámlaszám", example = "1234567890123456")
    private String bankAccountNumber;

    @Schema(description = "Számlatulajdonos neve", example = "John Doe")
    private String accountHolderName;

    @Schema(description = "Alapértelmezett fizetési mód", example = "Bank Transfer")
    private String defaultPaymentMethod;

    @Schema(description = "Csoportos beszedési limit", example = "1000.00")
    private BigDecimal directDebitLimit;

    @Schema(description = "Hozzáférési e-mail", example = "john.doe@example.com")
    private String accessEmail;

    @Schema(description = "Státusz", example = "Active")
    private String status;

    @Schema(description = "Típus", example = "Residential")
    private String type;

    @Schema(description = "Azonosító", example = "EB12345")
    private String identifier;

    @Schema(description = "Kezdő dátum", example = "2023-01-01")
    private LocalDate startDate;

    @Schema(description = "Vége dátum", example = "2023-12-31")
    private LocalDate endDate;

    @Schema(description = "Létrehozta", example = "admin")
    private String createdBy;

    @Schema(description = "Létrehozás dátuma", example = "2023-01-01T10:00:00")
    private LocalDate createdDate;

    @Schema(description = "Módosította", example = "admin")
    private String lastModifiedBy;

    @Schema(description = "Módosítás dátuma", example = "2023-01-02T15:30:00")
    private LocalDate lastModifiedDate;
}
