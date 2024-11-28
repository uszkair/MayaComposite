package io.axasoft.mayacomposite.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CodeTableRequest {

    @Schema(description = "Kódtábla típus kódja", example = "APARTMENT_TYPE")
    @NotBlank(message = "{codeTable.error.typeCode.notblank}")
    @Size(max = 50, message = "{codeTable.error.typeCode.size}")
    private String typeCode;

    @Schema(description = "Kódtábla kódja", example = "RESIDENTIAL")
    @NotBlank(message = "{codeTable.error.code.notblank}")
    @Pattern(regexp = "^[A-Z][A-Z0-9_]*$", message = "{codeTable.error.code.pattern}")
    @Size(max = 50, message = "{codeTable.error.code.size}")
    private String code;

    @Schema(description = "Alapértelmezett név", example = "Lakóingatlan")
    @NotBlank(message = "{codeTable.error.defaultName.notblank}")
    @Size(max = 255, message = "{codeTable.error.defaultName.size}")
    private String defaultName;

    @Schema(description = "Alapértelmezett nyelv", example = "hu")
    @Pattern(regexp = "^[a-z]{2}$", message = "{codeTable.error.defaultLanguage.pattern}")
    private String defaultLanguage = "hu";

    @Schema(description = "Leírás", example = "Lakóingatlan típus")
    private String description;

    @Schema(description = "Rendezési sorrend", example = "1")
    private Integer sortOrder;

    @Schema(description = "Aktív státusz", example = "true")
    private Boolean isActive = true;
}