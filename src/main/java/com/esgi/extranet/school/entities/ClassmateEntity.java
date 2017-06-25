package com.esgi.extranet.school.entities;

import com.esgi.extranet.administration.entities.TeacherEntity;
import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author timotheearnauld
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="classmate")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClassmateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String classmateName;

    @OneToMany
    private List<TeacherEntity> teacherEntities;
}
