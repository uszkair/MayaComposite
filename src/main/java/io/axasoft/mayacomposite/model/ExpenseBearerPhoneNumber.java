package io.axasoft.mayacomposite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "expense_bearer_phone_number")
@Getter
@Setter
public class ExpenseBearerPhoneNumber extends Auditable {

    /** Egyedi azonosító UUID formátumban */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @ManyToOne
    @JoinColumn(name = "expense_bearer_id", nullable = false)
    private ExpenseBearer expenseBearer;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}
