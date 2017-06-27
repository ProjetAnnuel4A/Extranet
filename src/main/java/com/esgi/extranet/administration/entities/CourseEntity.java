package com.esgi.extranet.administration.entities;

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
@Table(name="course")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String coursename;

    @ManyToMany
    @JoinColumn
    private List<TeacherEntity> teacherEntities;
}
