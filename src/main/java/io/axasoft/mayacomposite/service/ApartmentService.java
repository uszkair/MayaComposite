package io.axasoft.mayacomposite.service;

import io.axasoft.mayacomposite.mapper.ApartmentMapper;
import io.axasoft.mayacomposite.model.Apartment;
import io.axasoft.mayacomposite.repository.ApartmentRepository;
import io.axasoft.mayacomposite.request.ApartmentRequest;
import io.axasoft.mayacomposite.response.ApartmentListResponse;
import io.axasoft.mayacomposite.response.ApartmentResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Társasházak kezelésére szolgáló service osztály.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;

    /**
     * Összes társasház lekérése lapozható formában.
     */
    public Page<ApartmentListResponse> getAllApartments(Pageable pageable) {
        return apartmentRepository.findAll(pageable)
                .map(apartmentMapper::toListResponse);
    }

    /**
     * Egy társasház részletes adatainak lekérése azonosító alapján.
     */
    public ApartmentResponse getApartmentById(String id) {
        return apartmentRepository.findById(id)
                .map(apartmentMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Nem található társasház ezzel az azonosítóval: %s", id)));
    }


    /**
     * Új társasház létrehozása.
     *
     * @param apartmentRequest Az új társasház adatai
     * @return A létrehozott társasház adatai
     */
    @Transactional
    public ApartmentResponse createApartment(ApartmentRequest apartmentRequest) {
        if (apartmentRequest.getApartmentIdentifier() != null &&
                apartmentRepository.existsByApartmentIdentifier(apartmentRequest.getApartmentIdentifier())) {
            throw new IllegalArgumentException("A megadott azonosítóval már létezik társasház");
        }

        Apartment apartment = apartmentMapper.toEntity(apartmentRequest);
        apartment = apartmentRepository.save(apartment);
        return apartmentMapper.toResponse(apartment);
    }
}