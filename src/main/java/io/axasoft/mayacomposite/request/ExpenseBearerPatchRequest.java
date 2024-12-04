package io.axasoft.mayacomposite.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A költségviselő részleges módosítására szolgáló request osztály.
 * Minden mező opcionális, csak a módosítandó mezőket kell megadni.
 */
@Data
public class ExpenseBearerPatchRequest {

    // Alap adatok
    @Schema(description = "A költségviselő típusa", example = "természetes személy")
    private String bearerType;

    @Schema(description = "A költségviselő neve", example = "Kiss János")
    private String name;

    // Természetes személy adatok
    @Schema(description = "Születési név", example = "Kiss János Péter")
    private String birthName;

    @Schema(description = "Anyja neve", example = "Nagy Mária")
    private String motherName;

    @Schema(description = "Születési év", example = "1980")
    private Integer birthYear;

    @Schema(description = "Születési idő", example = "1980-01-01")
    private LocalDate birthDate;

    @Schema(description = "Születési hely", example = "Budapest")
    private String birthPlace;

    @Schema(description = "Adóazonosító jel", example = "8123456789")
    @Pattern(regexp = "^[0-9]{10}$", message = "{expenseBearer.error.taxId.pattern}")
    private String taxId;

    @Schema(description = "Személyi szám", example = "123456AB")
    private String personalId;

    @Schema(description = "Személyi igazolvány szám", example = "123456AB")
    private String idCardNumber;

    // Gazdálkodó szervezet adatok
    @Schema(description = "Cégjegyzékszám", example = "01-09-123456")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{6}$", message = "{expenseBearer.error.companyRegistrationNumber.pattern}")
    private String companyRegistrationNumber;

    @Schema(description = "Székhely címe", example = "1234 Budapest, Példa utca 1.")
    private String headquartersAddress;

    @Schema(description = "Képviselő neve", example = "Nagy János")
    private String representativeName;

    @Schema(description = "Képviselő születési neve", example = "Nagy János Péter")
    private String representativeBirthName;

    @Schema(description = "Képviselő anyja neve", example = "Kiss Mária")
    private String representativeMotherName;

    @Schema(description = "Képviselő adószáma", example = "8123456789")
    private String representativeTaxNumber;

    @Schema(description = "Képviselő születési ideje", example = "1980-01-01")
    private LocalDate representativeBirthDate;

    @Schema(description = "Képviselő lakcíme", example = "1234 Budapest, Példa utca 2.")
    private String representativeAddress;

    // Értesítési beállítások
    @Schema(description = "Értesítések nyelve", example = "hu")
    private String notificationLanguage;

    @Schema(description = "IBAN használata", example = "false")
    private Boolean useIban;

    @Schema(description = "E-mail értesítések engedélyezése", example = "true")
    private Boolean emailNotifications;

    @Schema(description = "Nyomtatott értesítések engedélyezése", example = "true")
    private Boolean printNotifications;

    // Banki adatok
    @Schema(description = "Csoportos beszedési megbízás azonosító", example = "A12345")
    private String directDebitId;

    @Schema(description = "Bank neve", example = "OTP Bank")
    private String bankName;

    @Schema(description = "Bankszámlaszám", example = "11111111-22222222-33333333")
    @Pattern(regexp = "^\\d{8}-\\d{8}-\\d{8}$", message = "{expenseBearer.error.bankAccountNumber.pattern}")
    private String bankAccountNumber;

    @Schema(description = "Számlatulajdonos neve", example = "Kiss János")
    private String accountHolderName;

    @Schema(description = "Alapértelmezett fizetési mód", example = "bank_transfer")
    private String defaultPaymentMethod;

    @Schema(description = "Csoportos beszedés limit", example = "100000.00")
    @DecimalMin(value = "0.0", message = "{expenseBearer.error.directDebitLimit.min}")
    private BigDecimal directDebitLimit;

    // Rendszer adatok
    @Schema(description = "Hozzáférési e-mail cím", example = "pelda@email.hu")
    @Email(message = "{expenseBearer.error.accessEmail.email}")
    private String accessEmail;

    @Schema(description = "Státusz", example = "active")
    private String status;

    @Schema(description = "Típus", example = "owner")
    private String type;

    @Schema(description = "Azonosító", example = "KV-123")
    private String identifier;

    @Schema(description = "Érvényesség kezdete", example = "2024-01-01")
    private LocalDate startDate;

    @Schema(description = "Érvényesség vége", example = "2024-12-31")
    private LocalDate endDate;

}
