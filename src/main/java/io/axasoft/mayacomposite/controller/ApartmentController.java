package io.axasoft.mayacomposite.controller;


import io.axasoft.mayacomposite.request.ApartmentFilterRequest;
import io.axasoft.mayacomposite.request.ApartmentRequest;
import io.axasoft.mayacomposite.response.ApartmentListResponse;
import io.axasoft.mayacomposite.response.ApartmentResponse;
import io.axasoft.mayacomposite.service.ApartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Társasházak kezelésére szolgáló REST végpontok.
 */
@RestController
@RequestMapping("/apartment")
@RequiredArgsConstructor
@Tag(name = "Társasházak", description = "Társasházak kezelésére szolgáló végpontok")
public class ApartmentController {

    private final ApartmentService apartmentService;

    @Operation(summary = "Get all apartments with filtering and pagination")
    @GetMapping("/list")
    public ResponseEntity<Page<ApartmentListResponse>> getAllApartments(
            ApartmentFilterRequest filterRequest, Pageable pageable) {
        return ResponseEntity.ok(apartmentService.getAllApartments(filterRequest, pageable));
    }

    @Operation(summary = "Társasház lekérése azonosító alapján")
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentResponse> getApartmentById(@PathVariable String id) {
        return ResponseEntity.ok(apartmentService.getApartmentById(id));
    }

    @Operation(summary = "Új társasház létrehozása")
    @PostMapping("/save")
    public ResponseEntity<ApartmentResponse> createApartment(@Valid @RequestBody ApartmentRequest request) {
        return ResponseEntity.ok(apartmentService.createApartment(request));
    }
}