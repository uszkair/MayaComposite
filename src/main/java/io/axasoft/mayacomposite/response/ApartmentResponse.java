package io.axasoft.mayacomposite.response;

import lombok.Data;

import java.time.LocalDate;

/**
 * Társasház részletes adatainak visszaadására szolgáló osztály.
 */
@Data
public class ApartmentResponse {
    /** Egyedi azonosító */
    private String id;

    /** Belső azonosító */
    private String apartmentIdentifier;

    /** Társasház neve */
    private String name;

    /** Irányítószám */
    private String postalCode;

    /** Város */
    private String city;

    /** Cím */
    private String address;

    /** Bázis cím az albetétekhez */
    private String baseAddress;

    /** Dedikált email cím */
    private String dedicatedEmail;

    /** Helyrajzi szám */
    private String cadastralNumber;

    /** Adószám */
    private String taxNumber;

    /** Lakásszövetkezet-e */
    private Boolean isHousingCooperative;

    /** Adatrögzítés kezdete */
    private LocalDate dataEntryStartDate;

    /** Lakói hozzáférés kezdete */
    private LocalDate residentAccessStartDate;

    /** Aktív-e */
    private Boolean isActive;
}