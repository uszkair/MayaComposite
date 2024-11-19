package io.axasoft.mayacomposite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "expense_bearer_email_address")
@Getter
@Setter
public class ExpenseBearerEmailAddress extends Auditable {

    /** Egyedi azonosító UUID formátumban */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @ManyToOne
    @JoinColumn(name = "expense_bearer_id", nullable = false)
    private ExpenseBearer expenseBearer;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;
}
