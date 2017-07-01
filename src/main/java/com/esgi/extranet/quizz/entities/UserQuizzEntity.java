package com.esgi.extranet.quizz.entities ;

import lombok.* ;
import org.codehaus.jackson.annotate.JsonIgnoreProperties ;

import javax.persistence.* ;
import javax.validation.constraints.Min ;
import javax.validation.constraints.NotNull ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name="user_responses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserQuizzEntity
{

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotNull
    @JoinColumn(table = "student", name = "id")
    private Long userId ;

    @NotNull
    @JoinColumn(table = "survey", name = "id")
    private Long surveyId ;

    @NotNull
    @JoinColumn(table = "question", name = "id")
    private Long questionId ;

    @NotNull
    @OneToMany
    @JoinColumn(table = "response", name = "id")
    private List<Long> responses ;

    @NotNull
    @Min(value = 1)
    private int count = 1 ; // Nombre d'essais réalisés

}
