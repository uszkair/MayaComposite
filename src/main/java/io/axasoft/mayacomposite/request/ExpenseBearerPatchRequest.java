package io.axasoft.mayacomposite.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseBearerPatchRequest {
    @Schema(description = "Költségviselő típusa", example = "Individual")
    @NotBlank(message = "{expenseBearer.error.bearerType.notblank}")
    private String bearerType;

    @Schema(description = "Név", example = "John Doe")
    @NotBlank(message = "{expenseBearer.error.name.notblank}")
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
    @Size(max = 50, message = "{expenseBearer.error.taxId.size}")
    private String taxId;

    @Schema(description = "Személyi szám", example = "12345678")
    @Size(max = 50, message = "{expenseBearer.error.personalId.size}")
    private String personalId;

    @Schema(description = "Személyi igazolvány szám", example = "ID123456")
    @Size(max = 50, message = "{expenseBearer.error.idCardNumber.size}")
    private String idCardNumber;

    @Schema(description = "Értesítések nyelve", example = "hu")
    private String notificationLanguage;

    @Schema(description = "IBAN használata", example = "false")
    private Boolean useIban = false;

    @Schema(description = "E-mail értesítések", example = "true")
    private Boolean emailNotifications = true;

    @Schema(description = "Nyomtatott értesítések", example = "true")
    private Boolean printNotifications = true;

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
    @DecimalMin(value = "0.0", inclusive = true, message = "{expenseBearer.error.directDebitLimit.min}")
    private BigDecimal directDebitLimit = BigDecimal.ZERO;

    @Schema(description = "Hozzáférési e-mail", example = "john.doe@example.com")
    @Email(message = "{expenseBearer.error.accessEmail.email}")
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

}
