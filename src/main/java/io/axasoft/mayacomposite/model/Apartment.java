package io.axasoft.mayacomposite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "apartment")
@Getter
@Setter
public class Apartment extends Auditable{
    /** Egyedi azonosító UUID formátumban */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "apartment_identifier")
    private String apartmentIdentifier;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "postal_code", nullable = false, length = 4)
    private String postalCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "base_address")
    private String baseAddress;

    @Column(name = "dedicated_email")
    private String dedicatedEmail;

    @Column(name = "cadastral_number")
    private String cadastralNumber;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "is_housing_cooperative")
    private Boolean isHousingCooperative = false;

    @Column(name = "data_entry_start_date", nullable = false)
    private LocalDate dataEntryStartDate;

    @Column(name = "resident_access_start_date", nullable = false)
    private LocalDate residentAccessStartDate;

    @Column(name = "is_active")
    private Boolean isActive = true;
}