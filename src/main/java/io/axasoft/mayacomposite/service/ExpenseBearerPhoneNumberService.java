package io.axasoft.mayacomposite.service;

import io.axasoft.mayacomposite.model.ExpenseBearerPhoneNumber;
import io.axasoft.mayacomposite.repository.ExpenseBearerPhoneNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing ExpenseBearerPhoneNumber entities.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExpenseBearerPhoneNumberService {

    private final ExpenseBearerPhoneNumberRepository phoneNumberRepository;

    /**
     * Retrieves all phone numbers for a given ExpenseBearer.
     *
     * @param expenseBearerId The UUID of the ExpenseBearer
     * @return A list of ExpenseBearerPhoneNumber entities
     */
    public List<ExpenseBearerPhoneNumber> getPhoneNumbersByExpenseBearerId(String expenseBearerId) {
        return phoneNumberRepository.findByExpenseBearerId(expenseBearerId);
    }
}
