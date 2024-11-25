package io.axasoft.mayacomposite.controller;

import io.axasoft.mayacomposite.request.SubdepositRequest;
import io.axasoft.mayacomposite.request.filter.SubdepositFilterRequest;
import io.axasoft.mayacomposite.response.SubdepositResponse;
import io.axasoft.mayacomposite.service.SubdepositService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Albetétek kezelésére szolgáló REST végpontok.
 */
@RestController
@RequestMapping(value = "/subdeposit", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Albetétek", description = "Albetétek kezelésére szolgáló végpontok")
public class SubdepositController {

    private final SubdepositService subdepositService;

    @Operation(summary = "Listázza az albetéteket szűrési feltételekkel és lapozással")
    @GetMapping(value = "/list/{apartmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<SubdepositResponse>> listSubdeposits(
            @PathVariable("apartmentId") String apartmentId,
            @Valid SubdepositFilterRequest filterRequest,
            @PageableDefault(size = 10, sort = "identifier") Pageable pageable) {
        // Call the service to retrieve filtered and paginated subdeposits for the specified apartment
        Page<SubdepositResponse> response = subdepositService.getAllSubdepositsForApartment(apartmentId, filterRequest, pageable);
        // Return the response wrapped in ResponseEntity
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Albetét lekérése azonosító alapján")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubdepositResponse> getSubdepositById(@PathVariable String id) {
        return ResponseEntity.ok(subdepositService.getSubdepositById(id));
    }

    @Operation(summary = "Új albetét létrehozása adott társasházhoz")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubdepositResponse> createSubdepositForApartment(
            @RequestParam("apartmentId") String apartmentId,
            @Valid @RequestBody SubdepositRequest request) {

        SubdepositResponse response = subdepositService.createSubdepositForApartment(apartmentId, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubdepositResponse> updateSubdeposit(
            @PathVariable("id") String id,
            @RequestBody @Valid SubdepositRequest request) {
        SubdepositResponse response = subdepositService.updateSubdeposit(id, request);
        return ResponseEntity.ok(response);
    }
}
