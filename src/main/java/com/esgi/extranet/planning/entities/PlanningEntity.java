package com.esgi.extranet.planning.entities;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

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

    @Column
    private String name;

    @Column
    private Long idClassmate;

    @ManyToMany
    @JoinColumn
    private List<ScheduleEntity>scheduleEntities;
}
