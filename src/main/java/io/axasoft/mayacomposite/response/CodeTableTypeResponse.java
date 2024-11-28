package io.axasoft.mayacomposite.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CodeTableTypeResponse {

    @Schema(description = "Egyedi azonosító", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Kódtábla típus kódja", example = "APARTMENT_TYPE")
    private String code;

    @Schema(description = "Alapértelmezett nyelv", example = "hu")
    private String defaultLanguage;

    @Schema(description = "Aktív státusz", example = "true")
    private Boolean isActive;

    @Schema(description = "Kódtábla típus fordítások")
    private List<CodeTableTypeTranslationResponse> translations = new ArrayList<>();

    @Schema(description = "Kódtábla értékek")
    private List<CodeTableResponse> values = new ArrayList<>();

    @Schema(description = "Létrehozó felhasználó", example = "admin")
    private String createdBy;

    @Schema(description = "Létrehozás dátuma", example = "2024-11-25T12:34:56")
    private String createdDate;

    @Schema(description = "Utolsó módosító felhasználó", example = "editor")
    private String lastModifiedBy;

    @Schema(description = "Utolsó módosítás dátuma", example = "2024-11-25T14:34:56")
    private String lastModifiedDate;
}