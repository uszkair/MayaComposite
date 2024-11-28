package io.axasoft.mayacomposite.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CodeTableTypeTranslationRequest {

    @Schema(description = "Nyelv kód", example = "hu")
    @NotBlank(message = "{codeTableType.error.language.notblank}")
    @Pattern(regexp = "^[a-z]{2}$", message = "{codeTableType.error.language.pattern}")
    private String language;

    @Schema(description = "Név", example = "Lakás típusok")
    @NotBlank(message = "{codeTableType.error.name.notblank}")
    @Size(max = 255, message = "{codeTableType.error.name.size}")
    private String name;

    @Schema(description = "Leírás", example = "Lakások típusainak kategorizálása")
    private String description;
}