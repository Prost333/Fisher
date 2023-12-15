package by.RIP.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Entity
@Setter
@ToString
@Table(schema = "fishman", name = "fish")
@Inheritance(strategy = InheritanceType.JOINED)
public class Fish {
    @Id
    @SequenceGenerator(name = "seq_fish", sequenceName = "fish_seq", allocationSize = 1, schema = "fishman")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fish")
    private Long id;
    private String name;
    private Float weight;
    private Long power;
    private Long speed;
    private  Long health;


}
