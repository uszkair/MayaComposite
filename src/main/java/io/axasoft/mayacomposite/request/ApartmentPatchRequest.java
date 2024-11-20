package io.axasoft.mayacomposite.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApartmentPatchRequest {
    private String name;
    private String city;
    private String postalCode;
    private String address;
    private String baseAddress;
    private String dedicatedEmail;
    private String cadastralNumber;
    private String taxNumber;
    private Boolean isHousingCooperative;
    private LocalDate dataEntryStartDate;
    private LocalDate residentAccessStartDate;
    private Boolean isActive;
    private Double ownershipRatio;
}
