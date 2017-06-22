package com.esgi.extranet.school.entities;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author timotheearnauld
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mark")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long idStudent;

    @Column(nullable = false)
    private Long idTeacher;

    @Column(nullable = false)
    private Long idCourse;

    @Column(nullable = false)
    private Long mark;
}
