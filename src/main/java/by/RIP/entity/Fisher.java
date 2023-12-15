package by.RIP.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Entity
@Getter
@Setter
@ToString
@Table(schema = "fishman", name = "fisher")
@Inheritance(strategy = InheritanceType.JOINED)
public class Fisher {
    @Id
    @SequenceGenerator(name = "seq_fisher", sequenceName = "fisher_seq", allocationSize = 1, schema = "fishman")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fisher")
    private Long id;
    private String name;
    private Long level;
    private Long power;
    private Long intellect;


}
