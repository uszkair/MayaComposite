package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.ExpenseBearerPhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseBearerPhoneNumberRepository extends JpaRepository<ExpenseBearerPhoneNumber, String>, JpaSpecificationExecutor<ExpenseBearerPhoneNumber> {
    /**
     * Lekérdezi az összes telefonszámot egy adott ExpenseBearer entitáshoz.
     *
     * @param expenseBearerId Az ExpenseBearer entitás azonosítója
     * @return Telefonszámok listája
     */
    List<ExpenseBearerPhoneNumber> findByExpenseBearerId(String expenseBearerId);
}
