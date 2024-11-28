package io.axasoft.mayacomposite.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CodeTableResponse {

    @Schema(description = "Egyedi azonosító", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Kódtábla kódja", example = "RESIDENTIAL")
    private String code;

    @Schema(description = "Kódtábla típus kódja", example = "APARTMENT_TYPE")
    private String typeCode;

    @Schema(description = "Rendezési sorrend", example = "1")
    private Integer sortOrder;

    @Schema(description = "Aktív státusz", example = "true")
    private Boolean isActive;

    @Schema(description = "Kódtábla fordítások")
    private List<CodeTableTranslationResponse> translations = new ArrayList<>();

    @Schema(description = "Létrehozó felhasználó", example = "admin")
    private String createdBy;

    @Schema(description = "Létrehozás dátuma", example = "2024-11-25T12:34:56")
    private String createdDate;

    @Schema(description = "Utolsó módosító felhasználó", example = "editor")
    private String lastModifiedBy;

    @Schema(description = "Utolsó módosítás dátuma", example = "2024-11-25T14:34:56")
    private String lastModifiedDate;
}