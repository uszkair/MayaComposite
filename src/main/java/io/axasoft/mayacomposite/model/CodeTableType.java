package io.axasoft.mayacomposite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "code_table_type")
@Getter
@Setter
public class CodeTableType extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "default_language", nullable = false, length = 2)
    private String defaultLanguage = "hu";

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "codeTableType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CodeTableTypeTranslation> translations = new HashSet<>();

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<CodeTable> codeTables = new HashSet<>();
}