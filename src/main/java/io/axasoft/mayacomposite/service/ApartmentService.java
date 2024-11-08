package io.axasoft.mayacomposite.service;

import io.axasoft.mayacomposite.constants.ApplicationConstants;
import io.axasoft.mayacomposite.exception.ServiceException;
import io.axasoft.mayacomposite.mapper.ApartmentMapper;
import io.axasoft.mayacomposite.model.Apartment;
import io.axasoft.mayacomposite.repository.ApartmentRepository;
import io.axasoft.mayacomposite.request.ApartmentRequest;
import io.axasoft.mayacomposite.response.ApartmentListResponse;
import io.axasoft.mayacomposite.response.ApartmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing apartments.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;

    /**
     * Retrieves all apartments in a paginated format.
     */
    public Page<ApartmentListResponse> getAllApartments(Pageable pageable) {
        return apartmentRepository.findAll(pageable)
                .map(apartmentMapper::toListResponse);
    }

    /**
     * Retrieves detailed information about an apartment by its ID.
     *
     * @param id The ID of the apartment
     * @return The detailed information of the apartment
     */
    public ApartmentResponse getApartmentById(String id) {
        return apartmentRepository.findById(id)
                .map(apartmentMapper::toResponse)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, id));
    }

    /**
     * Creates a new apartment.
     *
     * @param apartmentRequest The details of the new apartment
     * @return The created apartment details
     */
    @Transactional
    public ApartmentResponse createApartment(ApartmentRequest apartmentRequest) {
        if (apartmentRequest.getApartmentIdentifier() != null &&
                apartmentRepository.existsByApartmentIdentifier(apartmentRequest.getApartmentIdentifier())) {
            // Throws a ServiceException with the message key for "apartment.identifier.exists"
            throw new ServiceException(ApplicationConstants.APARTMENT_IDENTIFIER_EXISTS);
        }

        Apartment apartment = apartmentMapper.toEntity(apartmentRequest);
        apartment = apartmentRepository.save(apartment);
        return apartmentMapper.toResponse(apartment);
    }
}
