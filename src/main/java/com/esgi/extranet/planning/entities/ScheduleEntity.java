package com.esgi.extranet.planning.entities;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * @author timotheearnauld
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="attribution")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long idPlanning;

    @Column
    private Date date;

    @Column
    private Long idCourseEntity;

    @Column
    private Long idTeacherEntity;

    @Column
    private Long idClassmateEntity;

    @Column
    private Long begin;

    @Column
    private Long end;
}
