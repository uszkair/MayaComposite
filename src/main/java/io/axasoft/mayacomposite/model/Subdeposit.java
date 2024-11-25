package io.axasoft.mayacomposite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

/**
 * Az albetét (subdeposit) entitás a rendszerben.
 * Ez az osztály tartalmazza az albetéthez kapcsolódó összes szükséges mezőt.
 */
@Entity
@Table(name = "subdeposit")
@Getter
@Setter
public class Subdeposit extends Auditable {

    /** Egyedi azonosító UUID formátumban az albetét számára */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    /** Befizetőazonosító az albetéthez (opcionális) */
    @Column(name = "payment_identifier", length = 50)
    private String paymentIdentifier;

    /** Az albetét típusa (kötelező) */
    @Column(name = "type", nullable = false, length = 100)
    private String type;

    /** Az épület adatai (opcionális) */
    @Column(name = "building", length = 100)
    private String building;

    /** A lépcsőház adatai (opcionális) */
    @Column(name = "staircase", length = 100)
    private String staircase;

    /** Az albetét szintje (opcionális) */
    @Column(name = "floor", length = 100)
    private String floor;

    /** Az albetét egyedi azonosítója (kötelező és egyedi) */
    @Column(name = "identifier", nullable = false, unique = true, length = 50)
    private String identifier;

    /** Az albetét helyrajzi száma (opcionális) */
    @Column(name = "cadastral_number", length = 50)
    private String cadastralNumber;

    /** GDPR hozzájárulás (kötelező, alapértelmezett érték: hamis) */
    @Column(name = "gdpr_consent", nullable = false)
    private Boolean gdprConsent = false;

    /** Az albetét területe négyzetméterben (opcionális) */
    @Column(name = "subdeposit_area", precision = 10, scale = 2)
    private BigDecimal subdepositArea;

    /** Az erkély területe négyzetméterben (opcionális) */
    @Column(name = "balcony_area", precision = 10, scale = 2)
    private BigDecimal balconyArea;

    /** A kert területe négyzetméterben (opcionális) */
    @Column(name = "garden_area", precision = 10, scale = 2)
    private BigDecimal gardenArea;

    /** A fűtött terület négyzetméterben (opcionális) */
    @Column(name = "heated_area", precision = 10, scale = 2)
    private BigDecimal heatedArea;

    /** Tulajdoni hányad az albetétben (opcionális) */
    @Column(name = "ownership_ratio", precision = 10, scale = 3)
    private BigDecimal ownershipRatio;

    /** Az albetét térfogata köbméterben (opcionális) */
    @Column(name = "volume", precision = 10, scale = 2)
    private BigDecimal volume;

    /** Lakók száma az albetétben (opcionális) */
    @Column(name = "residents_count")
    private Integer residentsCount;

    /** Önkormányzati előírás (kötelező, alapértelmezett érték: hamis) */
    @Column(name = "municipal_regulation", nullable = false)
    private Boolean municipalRegulation = false;

    /** Vízóra felszerelve (kötelező, alapértelmezett érték: hamis) */
    @Column(name = "water_meter_installed", nullable = false)
    private Boolean waterMeterInstalled = false;

    /** Az albetét hitelt fizet (kötelező, alapértelmezett érték: hamis) */
    @Column(name = "deposit_pays_loan", nullable = false)
    private Boolean depositPaysLoan = false;

    /** Jelzáloggal terhelhető (kötelező, alapértelmezett érték: hamis) */
    @Column(name = "mortgageable", nullable = false)
    private Boolean mortgageable = false;

    /** Postai csekket igényel (kötelező, alapértelmezett érték: hamis) */
    @Column(name = "postal_check_required", nullable = false)
    private Boolean postalCheckRequired = false;

    /** Közös költség (általános) szorzó (kötelező, alapértelmezett érték: 1.00) */
    @Column(name = "general_common_cost_ratio", nullable = false, precision = 10, scale = 2)
    private BigDecimal generalCommonCostRatio = BigDecimal.valueOf(1.00);

    /** Célbefizetések szorzója (kötelező, alapértelmezett érték: 1.00) */
    @Column(name = "targeted_payment_ratio", nullable = false, precision = 10, scale = 2)
    private BigDecimal targetedPaymentRatio = BigDecimal.valueOf(1.00);

    /** Szemétszállítási díj szorzója (kötelező, alapértelmezett érték: 1.00) */
    @Column(name = "waste_management_ratio", nullable = false, precision = 10, scale = 2)
    private BigDecimal wasteManagementRatio = BigDecimal.valueOf(1.00);

    /** Közös költség (csoportos) szorzó (kötelező, alapértelmezett érték: 1.00) */
    @Column(name = "group_common_cost_ratio", nullable = false, precision = 10, scale = 2)
    private BigDecimal groupCommonCostRatio = BigDecimal.valueOf(1.00);

    /** Felújítási díj szorzója (kötelező, alapértelmezett érték: 1.00) */
    @Column(name = "renovation_fee_ratio", nullable = false, precision = 10, scale = 2)
    private BigDecimal renovationFeeRatio = BigDecimal.valueOf(1.00);

    /** Villamosenergia díj szorzója (kötelező, alapértelmezett érték: 1.00) */
    @Column(name = "electricity_fee_ratio", nullable = false, precision = 10, scale = 2)
    private BigDecimal electricityFeeRatio = BigDecimal.valueOf(1.00);

    /** Kezelési díj szorzója (kötelező, alapértelmezett érték: 1.00) */
    @Column(name = "handling_fee_ratio", nullable = false, precision = 10, scale = 2)
    private BigDecimal handlingFeeRatio = BigDecimal.valueOf(1.00);

    /** Takarítási díj szorzója (kötelező, alapértelmezett érték: 1.00) */
    @Column(name = "cleaning_fee_ratio", nullable = false, precision = 10, scale = 2)
    private BigDecimal cleaningFeeRatio = BigDecimal.valueOf(1.00);

    /** Hitel törlesztési díj szorzója (kötelező, alapértelmezett érték: 1.00) */
    @Column(name = "loan_repayment_ratio", nullable = false, precision = 10, scale = 2)
    private BigDecimal loanRepaymentRatio = BigDecimal.valueOf(1.00);

    @Column(name = "is_active")
    private Boolean isActive = true;

    /** Kapcsolódó társasház azonosító (kötelező) */
    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;
}
