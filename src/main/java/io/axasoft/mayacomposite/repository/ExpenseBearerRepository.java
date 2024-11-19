package io.axasoft.mayacomposite.repository;

import io.axasoft.mayacomposite.model.ExpenseBearer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ExpenseBearerRepository extends JpaRepository<ExpenseBearer, String>, JpaSpecificationExecutor<ExpenseBearer> {
    /**
     * Lekérdezi az összes ExpenseBearer entitást lapozással.
     *
     * @param pageable Lapozási paraméterek
     * @return ExpenseBearer entitások listája lapozva
     */
    Page<ExpenseBearer> findAll(Pageable pageable);
}
