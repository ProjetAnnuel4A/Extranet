package com.esgi.extranet.quizz.entities ;

import lombok.* ;
import org.codehaus.jackson.annotate.JsonIgnoreProperties ;

import javax.persistence.* ;
import javax.validation.constraints.NotNull ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "response")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ResponseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotNull
    @Column(nullable = false)
    private String description ;

    @Column
    private Long imageId ;

}
