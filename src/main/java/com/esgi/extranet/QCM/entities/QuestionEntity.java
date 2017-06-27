package com.esgi.extranet.QCM.entities ;

import lombok.* ;
import org.codehaus.jackson.annotate.JsonIgnoreProperties ;

import javax.persistence.* ;
import javax.validation.constraints.DecimalMin ;
import javax.validation.constraints.NotNull ;
import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name="question")
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

    @OneToMany
    @JoinColumn
    private ArrayList<ResponseEntity> responses ;

    @Column
    private int[] indexCorrectResponses ;

    @DecimalMin(value = "0.0")
    @Column(nullable = false)
    private float points = 0 ;

    @Column(nullable = false)
    private boolean allOrNot = true ;

    @Column
    private String imagePath ;

}
