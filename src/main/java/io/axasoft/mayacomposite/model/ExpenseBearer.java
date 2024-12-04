package io.axasoft.mayacomposite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A költségviselő entitás reprezentálja a társasházi költségek fizetéséért felelős személyt vagy szervezetet.
 * Lehet tulajdonos, bérlő vagy egyéb költségviselő szervezet.
 */
@Entity
@Table(name = "expense_bearer")
@Getter
@Setter
public class ExpenseBearer extends Auditable {

    /** Egyedi azonosító UUID formátumban */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "bearer_type", nullable = false)
    private String bearerType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_name")
    private String birthName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "tax_id")
    private String taxId;

    @Column(name = "personal_id")
    private String personalId;

    @Column(name = "id_card_number")
    private String idCardNumber;

    @Column(name = "company_registration_number")
    private String companyRegistrationNumber;

    @Column(name = "headquarters_address", length = 500)
    private String headquartersAddress;

    @Column(name = "representative_name")
    private String representativeName;

    @Column(name = "representative_birth_name")
    private String representativeBirthName;

    @Column(name = "representative_mother_name")
    private String representativeMotherName;

    @Column(name = "representative_tax_number")
    private String representativeTaxNumber;

    @Column(name = "representative_birth_date")
    private LocalDate representativeBirthDate;

    @Column(name = "representative_address", length = 500)
    private String representativeAddress;

    @Column(name = "notification_language")
    private String notificationLanguage;

    @Column(name = "use_iban", nullable = false)
    private Boolean useIban = false;

    @Column(name = "email_notifications", nullable = false)
    private Boolean emailNotifications = true;

    @Column(name = "print_notifications", nullable = false)
    private Boolean printNotifications = true;

    @Column(name = "direct_debit_id")
    private String directDebitId;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "default_payment_method")
    private String defaultPaymentMethod;

    @Column(name = "direct_debit_limit", nullable = false)
    private BigDecimal directDebitLimit = BigDecimal.ZERO;

    @Column(name = "access_email")
    private String accessEmail;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;
}
