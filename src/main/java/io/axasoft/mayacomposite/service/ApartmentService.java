package io.axasoft.mayacomposite.service;

import io.axasoft.mayacomposite.constants.ApplicationConstants;
import io.axasoft.mayacomposite.exception.ServiceException;
import io.axasoft.mayacomposite.mapper.ApartmentMapper;
import io.axasoft.mayacomposite.model.Apartment;
import io.axasoft.mayacomposite.repository.ApartmentRepository;
import io.axasoft.mayacomposite.request.ApartmentPatchRequest;
import io.axasoft.mayacomposite.request.ApartmentRequest;
import io.axasoft.mayacomposite.request.filter.ApartmentFilterRequest;
import io.axasoft.mayacomposite.response.ApartmentListResponse;
import io.axasoft.mayacomposite.response.ApartmentResponse;
import io.axasoft.mayacomposite.specification.ApartmentSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
     * Retrieves all apartments with filtering and pagination.
     *
     * @param filterRequest The filtering criteria
     * @param pageable      The pagination information
     * @return A paginated and filtered list of apartments
     */
    public Page<ApartmentListResponse> getAllApartments(ApartmentFilterRequest filterRequest, Pageable pageable) {
        ApartmentSpecifications specifications = new ApartmentSpecifications(pageable);
        Specification<Apartment> specification = specifications.buildSpecification(filterRequest);

        return apartmentRepository.findAll(specification, specifications.pageable)
                .map(apartmentMapper::toListResponse);
    }

    /**
     * Retrieves detailed information about an apartment by its ID.
     *
     * @param id The UUID of the apartment
     * @return The detailed information of the apartment
     */
    public ApartmentResponse getApartmentById(String id) {
        return apartmentRepository.findById(id)
                .map(apartmentMapper::toResponse)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, id.toString()));
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
            throw new ServiceException(ApplicationConstants.APARTMENT_IDENTIFIER_EXISTS);
        }

        Apartment apartment = apartmentMapper.toEntity(apartmentRequest);
        apartment = apartmentRepository.save(apartment);
        return apartmentMapper.toResponse(apartment);
    }

    /**
     * Updates an existing Apartment entity with the provided partial data.
     *
     * @param id The unique identifier of the Apartment to update.
     * @param request The ApartmentPatchRequest containing the fields to update.
     *                Only non-null fields in the request will be updated.
     * @return ApartmentResponse containing the updated Apartment data.
     * @throws ServiceException if the Apartment with the given ID is not found.
     */
    @Transactional
    public ApartmentResponse updateApartment(String id, ApartmentPatchRequest request) {
        // Fetch the Apartment entity or throw an exception if not found
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, id));

        // Update Apartment entity using MapStruct mapper
        apartmentMapper.updateApartmentFromDto(request, apartment);

        // Save updated entity to the database
        apartment = apartmentRepository.save(apartment);

        // Convert the updated entity to a response DTO and return
        return apartmentMapper.toResponse(apartment);
    }

}
