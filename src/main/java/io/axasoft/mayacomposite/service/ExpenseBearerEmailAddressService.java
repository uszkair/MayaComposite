package io.axasoft.mayacomposite.service;

import io.axasoft.mayacomposite.model.ExpenseBearerEmailAddress;
import io.axasoft.mayacomposite.repository.ExpenseBearerEmailAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing ExpenseBearerEmailAddress entities.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExpenseBearerEmailAddressService {

    private final ExpenseBearerEmailAddressRepository emailAddressRepository;

    /**
     * Retrieves all email addresses for a given ExpenseBearer.
     *
     * @param expenseBearerId The UUID of the ExpenseBearer
     * @return A list of ExpenseBearerEmailAddress entities
     */
    public List<ExpenseBearerEmailAddress> getEmailAddressesByExpenseBearerId(String expenseBearerId) {
        return emailAddressRepository.findByExpenseBearerId(expenseBearerId);
    }
}
