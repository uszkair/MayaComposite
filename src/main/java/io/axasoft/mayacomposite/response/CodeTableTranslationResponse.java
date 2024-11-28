package io.axasoft.mayacomposite.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CodeTableTranslationResponse {

    @Schema(description = "Egyedi azonosító", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Nyelv kód", example = "hu")
    private String language;

    @Schema(description = "Név", example = "Lakóingatlan")
    private String name;

    @Schema(description = "Leírás", example = "Lakóingatlan típusú albetét")
    private String description;

    @Schema(description = "Létrehozó felhasználó", example = "admin")
    private String createdBy;

    @Schema(description = "Létrehozás dátuma", example = "2024-11-25T12:34:56")
    private String createdDate;

    @Schema(description = "Utolsó módosító felhasználó", example = "editor")
    private String lastModifiedBy;

    @Schema(description = "Utolsó módosítás dátuma", example = "2024-11-25T14:34:56")
    private String lastModifiedDate;
}