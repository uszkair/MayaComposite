package io.axasoft.mayacomposite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "expense_bearer_address")
@Getter
@Setter
public class ExpenseBearerAddress extends Auditable {

    /** Egyedi azonosító UUID formátumban */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @ManyToOne
    @JoinColumn(name = "expense_bearer_id", nullable = false)
    private ExpenseBearer expenseBearer;

    @Column(name = "address_line", nullable = false)
    private String addressLine;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "default_address", nullable = false)
    private Boolean defaultAddress = false;
}
