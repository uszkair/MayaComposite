package io.axasoft.mayacomposite.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CodeTableTranslationRequest {

    @Schema(description = "Nyelv kód", example = "en")
    @NotBlank(message = "{codeTable.error.language.notblank}")
    @Pattern(regexp = "^[a-z]{2}$", message = "{codeTable.error.language.pattern}")
    private String language;

    @Schema(description = "Lefordított név", example = "Residential")
    @NotBlank(message = "{codeTable.error.name.notblank}")
    @Size(max = 255, message = "{codeTable.error.name.size}")
    private String name;

    @Schema(description = "Leírás", example = "Residential property type")
    private String description;
}