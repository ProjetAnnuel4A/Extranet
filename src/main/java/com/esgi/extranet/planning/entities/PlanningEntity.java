package com.esgi.extranet.planning.entities;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author timotheearnauld
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="planning")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlanningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long id_teacher;

    @Column(nullable = false)
    private Long id_course;

    @Column(nullable = false)
    private Long id_classmate;
}
