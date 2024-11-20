package io.axasoft.mayacomposite.controller;

import io.axasoft.mayacomposite.request.ExpenseBearerPatchRequest;
import io.axasoft.mayacomposite.request.ExpenseBearerRequest;
import io.axasoft.mayacomposite.request.filter.ExpenseBearerFilterRequest;
import io.axasoft.mayacomposite.response.ExpenseBearerResponse;
import io.axasoft.mayacomposite.service.ExpenseBearerService;
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
 * Költségviselők kezelésére szolgáló REST végpontok.
 */
@RestController
@RequestMapping(value = "/expense-bearer", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Költségviselők", description = "Költségviselők kezelésére szolgáló végpontok")
public class ExpenseBearerController {

    private final ExpenseBearerService expenseBearerService;

    @Operation(summary = "Költségviselők listázása szűréssel és lapozással")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ExpenseBearerResponse>> getAllExpenseBearers(
            ExpenseBearerFilterRequest filterRequest,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        return ResponseEntity.ok(expenseBearerService.getAllExpenseBearers(filterRequest, pageable));
    }

    @Operation(summary = "Költségviselő lekérése azonosító alapján")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpenseBearerResponse> getExpenseBearerById(@PathVariable String id) {
        return ResponseEntity.ok(expenseBearerService.getExpenseBearerById(id));
    }

    @Operation(summary = "Új költségviselő létrehozása adott társasházhoz")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpenseBearerResponse> createExpenseBearerForApartment(
            @RequestParam("apartmentId") String apartmentId,
            @Valid @RequestBody ExpenseBearerRequest request) {

        ExpenseBearerResponse response = expenseBearerService.createExpenseBearerForApartment(apartmentId, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/expense-bearers/{id}")
    public ResponseEntity<ExpenseBearerResponse> updateExpenseBearer(
            @PathVariable("id") String id,
            @RequestBody @Valid ExpenseBearerPatchRequest request) {
        ExpenseBearerResponse response = expenseBearerService.updateExpenseBearer(id, request);
        return ResponseEntity.ok(response);
    }

}
