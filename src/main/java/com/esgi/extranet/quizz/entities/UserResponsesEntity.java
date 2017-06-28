package com.esgi.extranet.quizz.entities ;

import lombok.* ;
import org.codehaus.jackson.annotate.JsonIgnoreProperties ;

import javax.persistence.* ;
import javax.validation.constraints.NotNull ;
import java.util.ArrayList ;

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
public class UserResponsesEntity
{

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotNull
    @JoinColumn
    private Long userId ;

    @NotNull
    @JoinColumn
    private Long surveyId ;

    @NotNull
    @JoinColumn
    private Long questionId ;

    @NotNull
    @OneToMany
    @JoinColumn
    private ArrayList<Long> responses ;

}
