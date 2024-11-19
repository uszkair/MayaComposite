package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.ExpenseBearerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseBearerAddressRepository extends JpaRepository<ExpenseBearerAddress, String>, JpaSpecificationExecutor<ExpenseBearerAddress> {
    /**
     * Lekérdezi az összes címet egy adott ExpenseBearer entitáshoz.
     *
     * @param expenseBearerId Az ExpenseBearer entitás azonosítója
     * @return Címek listája
     */
    List<ExpenseBearerAddress> findByExpenseBearerId(UUID expenseBearerId);
}
