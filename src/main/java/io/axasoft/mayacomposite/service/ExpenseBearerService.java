package io.axasoft.mayacomposite.service;

import io.axasoft.mayacomposite.exception.ServiceException;
import io.axasoft.mayacomposite.model.*;
import io.axasoft.mayacomposite.repository.*;
import io.axasoft.mayacomposite.request.*;
import io.axasoft.mayacomposite.response.ExpenseBearerResponse;
import io.axasoft.mayacomposite.mapper.ExpenseBearerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;
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

    /**
     * Creates a new ExpenseBearer with related phone numbers, email addresses, and addresses.
     *
     * @param request The details of the new ExpenseBearer
     * @return The created ExpenseBearer response
     */
    public ExpenseBearerResponse createExpenseBearer(ExpenseBearerRequest request) {
        // Create and save the ExpenseBearer entity
        ExpenseBearer expenseBearer = expenseBearerMapper.toEntity(request);
        ExpenseBearer savedExpenseBearer = expenseBearerRepository.save(expenseBearer);

        // Save related phone numbers
        if (request.getPhoneNumbers() != null) {
            List<ExpenseBearerPhoneNumber> phoneNumbers = request.getPhoneNumbers().stream()
                .map(phoneRequest -> {
                    ExpenseBearerPhoneNumber phoneNumber = new ExpenseBearerPhoneNumber();
                    phoneNumber.setExpenseBearer(savedExpenseBearer);
                    phoneNumber.setPhoneNumber(phoneRequest.getPhoneNumber());
                    return phoneNumber;
                }).collect(Collectors.toList());
            phoneNumberRepository.saveAll(phoneNumbers);
        }

        // Save related email addresses
        if (request.getEmailAddresses() != null) {
            List<ExpenseBearerEmailAddress> emailAddresses = request.getEmailAddresses().stream()
                .map(emailRequest -> {
                    ExpenseBearerEmailAddress emailAddress = new ExpenseBearerEmailAddress();
                    emailAddress.setExpenseBearer(savedExpenseBearer);
                    emailAddress.setEmailAddress(emailRequest.getEmailAddress());
                    return emailAddress;
                }).collect(Collectors.toList());
            emailAddressRepository.saveAll(emailAddresses);
        }

        // Save related addresses
        if (request.getAddresses() != null) {
            List<ExpenseBearerAddress> addresses = request.getAddresses().stream()
                .map(addressRequest -> {
                    ExpenseBearerAddress address = new ExpenseBearerAddress();
                    address.setExpenseBearer(savedExpenseBearer);
                    address.setAddressLine(addressRequest.getAddressLine());
                    address.setCity(addressRequest.getCity());
                    address.setPostalCode(addressRequest.getPostalCode());
                    address.setCountry(addressRequest.getCountry());
                    address.setDefaultAddress(addressRequest.getDefaultAddress());
                    return address;
                }).collect(Collectors.toList());
            addressRepository.saveAll(addresses);
        }

        // Return the response
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

    /**
     * Retrieves a paginated list of ExpenseBearers.
     *
     * @param pageable Pagination information
     * @return A page of ExpenseBearer responses
     */
    public Page<ExpenseBearerResponse> getAllExpenseBearers(Pageable pageable) {
        return expenseBearerRepository.findAll(pageable)
                .map(expenseBearerMapper::toResponse);
    }
}
