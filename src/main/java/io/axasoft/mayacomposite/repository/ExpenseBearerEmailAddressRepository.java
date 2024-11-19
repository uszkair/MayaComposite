package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.ExpenseBearerEmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseBearerEmailAddressRepository extends JpaRepository<ExpenseBearerEmailAddress, String>, JpaSpecificationExecutor<ExpenseBearerEmailAddress> {
    /**
     * Lekérdezi az összes e-mail címet egy adott ExpenseBearer entitáshoz.
     *
     * @param expenseBearerId Az ExpenseBearer entitás azonosítója
     * @return E-mail címek listája
     */
    List<ExpenseBearerEmailAddress> findByExpenseBearerId(UUID expenseBearerId);
}
