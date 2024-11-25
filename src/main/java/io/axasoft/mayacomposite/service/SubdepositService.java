package io.axasoft.mayacomposite.service;

import io.axasoft.mayacomposite.constants.ApplicationConstants;
import io.axasoft.mayacomposite.exception.ServiceException;
import io.axasoft.mayacomposite.mapper.SubdepositMapper;
import io.axasoft.mayacomposite.model.Apartment;
import io.axasoft.mayacomposite.model.Subdeposit;
import io.axasoft.mayacomposite.repository.ApartmentRepository;
import io.axasoft.mayacomposite.repository.SubdepositRepository;
import io.axasoft.mayacomposite.request.SubdepositRequest;
import io.axasoft.mayacomposite.request.filter.SubdepositFilterRequest;
import io.axasoft.mayacomposite.response.SubdepositResponse;
import io.axasoft.mayacomposite.specification.SubdepositSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing Subdeposit entities and related operations.
 *
 * Author: RobertUszkai
 */
@Service
@RequiredArgsConstructor
@Transactional
public class SubdepositService {

    private final SubdepositRepository subdepositRepository;
    private final ApartmentRepository apartmentRepository;
    private final SubdepositMapper subdepositMapper;

    /**
     * Creates a new Subdeposit and associates it with an existing Apartment.
     *
     * @param apartmentId The ID of the Apartment to associate the Subdeposit with.
     * @param request     The SubdepositRequest containing the details for the Subdeposit.
     * @return The created SubdepositResponse.
     */
    @Transactional
    public SubdepositResponse createSubdepositForApartment(String apartmentId, SubdepositRequest request) {
        // Validate and fetch the Apartment entity
        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, apartmentId));

        // Map the request to a Subdeposit entity
        Subdeposit subdeposit = subdepositMapper.toEntity(request);
        subdeposit.setApartment(apartment); // Associate the Subdeposit with the Apartment

        // Save the Subdeposit entity to the database
        Subdeposit savedSubdeposit = subdepositRepository.save(subdeposit);

        // Map and return the Subdeposit response
        return subdepositMapper.toResponse(savedSubdeposit);
    }

    /**
     * Retrieves a Subdeposit by its ID.
     *
     * @param id The ID of the Subdeposit.
     * @return The SubdepositResponse for the specified ID.
     */
    public SubdepositResponse getSubdepositById(String id) {
        Subdeposit subdeposit = subdepositRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, id));
        return subdepositMapper.toResponse(subdeposit);
    }

    /**
     * Retrieves all Subdeposits for a specified Apartment with filtering and pagination support.
     *
     * @param apartmentId   The ID of the Apartment to filter Subdeposits by.
     * @param filterRequest The filter criteria for Subdeposits.
     * @param pageable      Pagination and sorting details.
     * @return A paginated list of SubdepositResponse objects.
     * @throws ServiceException If the specified Apartment is not found.
     */
    public Page<SubdepositResponse> getAllSubdepositsForApartment(
            String apartmentId, SubdepositFilterRequest filterRequest, Pageable pageable) {
        // Validate and fetch the Apartment entity
        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, apartmentId));

        // Build the filter specification using SubdepositSpecifications
        Specification<Subdeposit> specification = SubdepositSpecifications.build(filterRequest)
                .and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("apartment"), apartment));

        // Retrieve filtered and paginated Subdeposits, mapping them to response objects
        return subdepositRepository.findAll(specification, pageable)
                .map(subdepositMapper::toResponse);
    }


    /**
     * Updates an existing Subdeposit.
     *
     * @param id      The ID of the Subdeposit to update.
     * @param request The SubdepositRequest containing the updated fields.
     * @return The updated SubdepositResponse.
     */
    @Transactional
    public SubdepositResponse updateSubdeposit(String id, SubdepositRequest request) {
        // Fetch the existing Subdeposit entity
        Subdeposit subdeposit = subdepositRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, id));

        // Update Subdeposit entity using the mapper
        subdepositMapper.updateSubdepositFromDto(request, subdeposit);

        // Save and return the updated entity
        Subdeposit updatedSubdeposit = subdepositRepository.save(subdeposit);
        return subdepositMapper.toResponse(updatedSubdeposit);
    }
}
