package io.axasoft.mayacomposite.service;

import io.axasoft.mayacomposite.exception.ServiceException;
import io.axasoft.mayacomposite.mapper.ExpenseBearerMapper;
import io.axasoft.mayacomposite.model.*;
import io.axasoft.mayacomposite.repository.*;
import io.axasoft.mayacomposite.request.ExpenseBearerRequest;
import io.axasoft.mayacomposite.request.filter.ExpenseBearerFilterRequest;
import io.axasoft.mayacomposite.response.ExpenseBearerResponse;
import io.axasoft.mayacomposite.specification.ExpenseBearerSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing ExpenseBearer entities and related data.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseBearerService {

    private final ExpenseBearerRepository expenseBearerRepository;
    private final ExpenseBearerPhoneNumberRepository phoneNumberRepository;
    private final ExpenseBearerEmailAddressRepository emailAddressRepository;
    private final ExpenseBearerAddressRepository addressRepository;
    private final ExpenseBearerMapper expenseBearerMapper;
    private final ApartmentRepository apartmentRepository;

    @Transactional
    public ExpenseBearerResponse createExpenseBearerForApartment(String apartmentId, ExpenseBearerRequest request) {
        // Validate and fetch the Apartment entity
        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new ServiceException("Apartment not found", apartmentId));

        // Map and save the ExpenseBearer entity
        ExpenseBearer expenseBearer = expenseBearerMapper.toEntity(request);
        expenseBearer.setApartment(apartment); // Set the apartment relationship
        ExpenseBearer savedExpenseBearer = expenseBearerRepository.save(expenseBearer);

        // Save related addresses and set the ExpenseBearer reference
        if (request.getAddresses() != null) {
            List<ExpenseBearerAddress> addresses = request.getAddresses().stream()
                    .map(addressRequest -> {
                        ExpenseBearerAddress address = new ExpenseBearerAddress();
                        address.setExpenseBearer(savedExpenseBearer); // Set the reference
                        address.setAddressLine(addressRequest.getAddressLine());
                        address.setCity(addressRequest.getCity());
                        address.setPostalCode(addressRequest.getPostalCode());
                        address.setCountry(addressRequest.getCountry());
                        address.setDefaultAddress(addressRequest.getDefaultAddress());
                        return address;
                    }).collect(Collectors.toList());
            addressRepository.saveAll(addresses);
        }

        // Save related phone numbers and set the ExpenseBearer reference
        if (request.getPhoneNumbers() != null) {
            List<ExpenseBearerPhoneNumber> phoneNumbers = request.getPhoneNumbers().stream()
                    .map(phoneRequest -> {
                        ExpenseBearerPhoneNumber phoneNumber = new ExpenseBearerPhoneNumber();
                        phoneNumber.setExpenseBearer(savedExpenseBearer); // Set the reference
                        phoneNumber.setPhoneNumber(phoneRequest.getPhoneNumber());
                        return phoneNumber;
                    }).collect(Collectors.toList());
            phoneNumberRepository.saveAll(phoneNumbers);
        }

        // Save related email addresses and set the ExpenseBearer reference
        if (request.getEmailAddresses() != null) {
            List<ExpenseBearerEmailAddress> emailAddresses = request.getEmailAddresses().stream()
                    .map(emailRequest -> {
                        ExpenseBearerEmailAddress emailAddress = new ExpenseBearerEmailAddress();
                        emailAddress.setExpenseBearer(savedExpenseBearer); // Set the reference
                        emailAddress.setEmailAddress(emailRequest.getEmailAddress());
                        return emailAddress;
                    }).collect(Collectors.toList());
            emailAddressRepository.saveAll(emailAddresses);
        }

        // Return the mapped response
        return expenseBearerMapper.toResponse(expenseBearer);
    }

    /**
     * Retrieves an ExpenseBearer by its ID.
     *
     * @param id The UUID of the ExpenseBearer
     * @return The ExpenseBearer response
     */
    public ExpenseBearerResponse getExpenseBearerById(String id) {
        return expenseBearerRepository.findById(id)
                .map(expenseBearerMapper::toResponse)
                .orElseThrow(() -> new ServiceException("ExpenseBearer not found", id.toString()));
    }



    public Page<ExpenseBearerResponse> getAllExpenseBearers(ExpenseBearerFilterRequest filterRequest, Pageable pageable) {
        // Build the specification using ExpenseBearerSpecifications
        Specification<ExpenseBearer> specification = ExpenseBearerSpecifications.build(filterRequest);

        // Fetch the filtered and paginated result
        return expenseBearerRepository.findAll(specification, pageable)
                .map(expenseBearerMapper::toResponse);
    }
}
