package io.axasoft.mayacomposite.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CodeTablePatchRequest {

    @Schema(description = "Kódtábla kódja", example = "RESIDENTIAL")
    @Pattern(regexp = "^[A-Z][A-Z0-9_]*$", message = "{codeTable.error.code.pattern}")
    @Size(max = 50, message = "{codeTable.error.code.size}")
    private String code;

    @Schema(description = "Rendezési sorrend", example = "1")
    private Integer sortOrder;

    @Schema(description = "Aktív státusz", example = "true")
    private Boolean isActive;
}