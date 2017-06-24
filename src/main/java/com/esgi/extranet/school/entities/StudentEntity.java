package com.esgi.extranet.school.entities;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author timotheearnauld
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentEntity {
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
    private LocalDate birthday;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private String address;
}
