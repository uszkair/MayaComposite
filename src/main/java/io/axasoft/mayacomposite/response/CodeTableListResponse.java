package io.axasoft.mayacomposite.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CodeTableListResponse {

    @Schema(description = "Egyedi azonosító", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Kódtábla típus kódja", example = "APARTMENT_TYPE")
    private String typeCode;

    @Schema(description = "Kódtábla kódja", example = "RESIDENTIAL")
    private String code;

    @Schema(description = "Aktuális név az alapértelmezett nyelven", example = "Lakóingatlan")
    private String defaultName;

    @Schema(description = "Aktív státusz", example = "true")
    private Boolean isActive;
}