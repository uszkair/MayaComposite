package io.axasoft.mayacomposite.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CodeTableTypeRequest {

    @Schema(description = "Kódtábla típus kódja", example = "APARTMENT_TYPE")
    @NotBlank(message = "{codeTableType.error.code.notblank}")
    @Pattern(regexp = "^[A-Z][A-Z0-9_]*$", message = "{codeTableType.error.code.pattern}")
    @Size(max = 50, message = "{codeTableType.error.code.size}")
    private String code;

    @Schema(description = "Alapértelmezett nyelv", example = "hu")
    @Pattern(regexp = "^[a-z]{2}$", message = "{codeTableType.error.defaultLanguage.pattern}")
    private String defaultLanguage = "hu";

    @Schema(description = "Aktív státusz", example = "true")
    private Boolean isActive = true;

    @Schema(description = "Kódtábla típus fordítások")
    @NotEmpty(message = "{codeTableType.error.translations.notEmpty}")
    private List<@Valid CodeTableTypeTranslationRequest> translations;

    @Schema(description = "Kódtábla értékek")
    @NotEmpty(message = "{codeTableType.error.values.notEmpty}")
    private List<@Valid CodeTableValueRequest> values;
}