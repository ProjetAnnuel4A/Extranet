package com.esgi.extranet.quizz.entities ;

import lombok.* ;
import org.codehaus.jackson.annotate.JsonIgnoreProperties ;

import javax.persistence.* ;
import javax.validation.constraints.NotNull ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 16/07/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_quizz_responses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserQuizzResponsesEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotNull
    @JoinColumn(table = "user_quizz", referencedColumnName = "id")
    private Long userQuizzId ;

    @NotNull
    @JoinColumn(table = "question", referencedColumnName = "id")
    private Long questionId ;

    @ManyToMany
    @JoinColumn(table = "response")
    private List<ResponseEntity> responses ;

}
