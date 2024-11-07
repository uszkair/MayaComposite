package io.axasoft.mayacomposite.response;

import lombok.Data;
/**
 * Társasházak listázásához használt válasz objektum.
 */
@Data
public class ApartmentListResponse {
    /** Egyedi azonosító */
    private String id;

    /** Társasház neve */
    private String name;

    /** Teljes cím */
    private String address;

    /** PIN kód */
    private String pinCode;

    /** Utolsó könyvelés dátuma */
    private String lastBookkeepingDate;

    /** Utolsó elszámolás dátuma */
    private String lastStatementDate;

    /** NAV import állapota */
    private String navImportStatus;

    /** Bank import állapota */
    private String bankImportStatus;
}