package io.axasoft.mayacomposite.controller;

import io.axasoft.mayacomposite.request.CodeTableTypeRequest;
import io.axasoft.mayacomposite.request.CodeTableValueRequest;
import io.axasoft.mayacomposite.response.CodeTableResponse;
import io.axasoft.mayacomposite.response.CodeTableTypeResponse;
import io.axasoft.mayacomposite.service.CodeTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/code-table", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Kódtáblák", description = "Kódtáblák és típusok kezelésére szolgáló végpontok")
public class CodeTableController {

    private final CodeTableService codeTableService;

    @Operation(summary = "Új kódtábla típus létrehozása értékekkel és fordításokkal")
    @PostMapping(value = "/type/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CodeTableTypeResponse> createCodeTableType(
            @Valid @RequestBody CodeTableTypeRequest request) {
        return ResponseEntity.ok(codeTableService.createCodeTableType(request));
    }

    @Operation(summary = "Új kódtábla érték hozzáadása típushoz")
    @PostMapping(value = "/type/{typeCode}/value", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CodeTableResponse> addCodeTableToType(
            @Parameter(description = "Kódtábla típus kódja", example = "APARTMENT_TYPE")
            @PathVariable String typeCode,
            @Valid @RequestBody CodeTableValueRequest request) {
        return ResponseEntity.ok(codeTableService.addCodeTableToType(typeCode, request));
    }

    @Operation(summary = "Kódtábla típusok listázása lapozással")
    @GetMapping("/type/list")
    public ResponseEntity<Page<CodeTableTypeResponse>> getAllCodeTableTypes(
            @PageableDefault(size = 20, sort = "code") Pageable pageable) {
        return ResponseEntity.ok(codeTableService.getAllCodeTableTypes(pageable));
    }

    @Operation(summary = "Kódtábla típus lekérése azonosító alapján")
    @GetMapping("/type/{id}")
    public ResponseEntity<CodeTableTypeResponse> getCodeTableTypeById(
            @Parameter(description = "Kódtábla típus azonosítója")
            @PathVariable String id) {
        return ResponseEntity.ok(codeTableService.getCodeTableTypeById(id));
    }

    @Operation(summary = "Kódtábla típus lekérése kód alapján")
    @GetMapping("/type/by-code/{code}")
    public ResponseEntity<CodeTableTypeResponse> getCodeTableTypeByCode(
            @Parameter(description = "Kódtábla típus kódja", example = "APARTMENT_TYPE")
            @PathVariable String code) {
        return ResponseEntity.ok(codeTableService.findTypeByCode(code));
    }

    @Operation(summary = "Kódtábla értékek lekérése típus alapján")
    @GetMapping("/values/{typeCode}")
    public ResponseEntity<List<CodeTableResponse>> getCodeTablesByType(
            @Parameter(description = "Kódtábla típus kódja", example = "APARTMENT_TYPE")
            @PathVariable String typeCode) {
        return ResponseEntity.ok(codeTableService.getCodeTablesByType(typeCode));
    }

    @Operation(summary = "Kódtábla érték lekérése azonosító alapján")
    @GetMapping("/value/{id}")
    public ResponseEntity<CodeTableResponse> getCodeTableById(
            @Parameter(description = "Kódtábla érték azonosítója")
            @PathVariable String id) {
        return ResponseEntity.ok(codeTableService.getCodeTableById(id));
    }

    @Operation(summary = "Kódtábla típus törlése")
    @DeleteMapping("/type/{id}")
    public ResponseEntity<Void> deleteCodeTableType(
            @Parameter(description = "Kódtábla típus azonosítója")
            @PathVariable String id) {
        codeTableService.deleteCodeTableType(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Kódtábla érték törlése")
    @DeleteMapping("/value/{id}")
    public ResponseEntity<Void> deleteCodeTable(
            @Parameter(description = "Kódtábla érték azonosítója")
            @PathVariable String id) {
        codeTableService.deleteCodeTable(id);
        return ResponseEntity.ok().build();
    }
}