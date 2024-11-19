package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.ExpenseBearerEmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseBearerEmailAddressRepository extends JpaRepository<ExpenseBearerEmailAddress, String>, JpaSpecificationExecutor<ExpenseBearerEmailAddress> {
    /**
     * Lekérdezi az összes e-mail címet egy adott ExpenseBearer entitáshoz.
     *
     * @param expenseBearerId Az ExpenseBearer entitás azonosítója
     * @return E-mail címek listája
     */
    List<ExpenseBearerEmailAddress> findByExpenseBearerId(String expenseBearerId);
}
