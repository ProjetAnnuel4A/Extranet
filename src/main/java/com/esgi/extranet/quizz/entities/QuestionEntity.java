package com.esgi.extranet.quizz.entities ;

import lombok.* ;
import org.codehaus.jackson.annotate.JsonIgnoreProperties ;

import javax.persistence.* ;
import javax.validation.constraints.DecimalMin ;
import javax.validation.constraints.NotNull ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "question")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuestionEntity
{

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotNull
    @Column(nullable = false)
    private String description ;

    @ManyToMany
    @JoinColumn(table = "response")
    private List<ResponseEntity> responses ;

    @ManyToMany(targetEntity = ResponseEntity.class)
    @JoinColumn(table = "response", referencedColumnName = "id")
    private List<Long> correctResponses ;

    @NotNull
    @Column(nullable = false)
    @DecimalMin(value = "0.0")
    private float points = 0 ;

    @NotNull
    @Column(nullable = false)
    private boolean allOrNot = true ;

    @Column
    private Long imageId ;

}
