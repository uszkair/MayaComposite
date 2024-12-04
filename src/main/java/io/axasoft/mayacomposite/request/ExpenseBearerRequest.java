package io.axasoft.mayacomposite.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Request model for creating or updating an ExpenseBearer.
 */
@Data
public class ExpenseBearerRequest {

    // Basic Information
    @Schema(description = "A költségviselő típusa", example = "természetes személy")
    @NotBlank(message = "{expenseBearer.error.bearerType.notBlank}")
    private String bearerType;

    @Schema(description = "A költségviselő neve", example = "Kiss János")
    @NotBlank(message = "{expenseBearer.error.name.notBlank}")
    private String name;

    // Natural Person Fields
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

    // Organization Fields
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

    // Notification Settings
    @Schema(description = "Értesítések nyelve", example = "hu")
    private String notificationLanguage;

    @Schema(description = "IBAN használata", example = "false")
    private Boolean useIban = false;

    @Schema(description = "E-mail értesítések engedélyezése", example = "true")
    private Boolean emailNotifications = true;

    @Schema(description = "Nyomtatott értesítések engedélyezése", example = "true")
    private Boolean printNotifications = true;

    // Bank Information
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
    private BigDecimal directDebitLimit = BigDecimal.ZERO;

    // System Information
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

    // New fields for related entities
    @Schema(description = "Telefonszámok listája")
    private List<ExpenseBearerPhoneNumberRequest> phoneNumbers;

    @Schema(description = "E-mail címek listája")
    private List<ExpenseBearerEmailAddressRequest> emailAddresses;

    @Schema(description = "Címek listája")
    private List<ExpenseBearerAddressRequest> addresses;
}