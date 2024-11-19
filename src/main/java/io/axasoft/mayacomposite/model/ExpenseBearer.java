package io.axasoft.mayacomposite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

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
}
