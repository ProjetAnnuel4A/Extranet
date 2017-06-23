package com.esgi.extranet.administration.entities;

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
@Table(name="teacher")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date birtday;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private String address;
}
