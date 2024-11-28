package io.axasoft.mayacomposite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "code_table_translation")
@Getter
@Setter
public class CodeTableTranslation extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_table_id", nullable = false)
    private CodeTable codeTable;

    @Column(name = "language", nullable = false, length = 2)
    private String language;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}