package com.esgi.extranet.quizz.entities ;

import lombok.* ;
import org.codehaus.jackson.annotate.JsonIgnoreProperties ;

import javax.persistence.* ;
import javax.validation.constraints.Min ;
import javax.validation.constraints.NotNull ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "user_quizz")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserQuizzEntity
{

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotNull
    @JoinColumn(table = "users", referencedColumnName = "id")
    private Long userId ;

    @NotNull
    @JoinColumn(table = "survey", referencedColumnName = "id")
    private Long surveyId ;

    @NotNull
    @Column(nullable = false)
    @Min(value = 1)
    @Builder.Default
    private int count = 1 ; // Nombre d'essais réalisés

}
