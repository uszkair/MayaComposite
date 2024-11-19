package io.axasoft.mayacomposite.service;

import io.axasoft.mayacomposite.model.ExpenseBearerAddress;
import io.axasoft.mayacomposite.repository.ExpenseBearerAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing ExpenseBearerAddress entities.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExpenseBearerAddressService {

    private final ExpenseBearerAddressRepository addressRepository;

    /**
     * Retrieves all addresses for a given ExpenseBearer.
     *
     * @param expenseBearerId The UUID of the ExpenseBearer
     * @return A list of ExpenseBearerAddress entities
     */
    public List<ExpenseBearerAddress> getAddressesByExpenseBearerId(String expenseBearerId) {
        return addressRepository.findByExpenseBearerId(expenseBearerId);
    }
}
