package com.esgi.extranet.QCM.entities ;

import lombok.* ;
import org.codehaus.jackson.annotate.JsonIgnoreProperties ;

import javax.persistence.* ;
import javax.annotation.PostConstruct ;
import javax.validation.constraints.DecimalMin ;
import javax.validation.constraints.Min ;
import javax.validation.constraints.NotNull ;
import java.sql.Date ;
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
@Table(name="survey")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SurveyEntity
{

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotNull
    @Column(nullable = false)
    private String name ;

    @OneToMany
    @JoinColumn
    private ArrayList<QuestionEntity> questions ;

    @DecimalMin(value = "0.0")
    @Column(nullable = false)
    private float mark = 0 ;

    @Min(value = -1)
    @Column(nullable = false)
    private int chances = 0 ; // Détermine le nombre d'essais (-1 = infini)

    @Column
    private Date deadLine ;

    @Column
    private String imagePath ;


    @PostConstruct
    public void calculateMark()
    {
        this.mark = 0 ;

        int questionsNumber = this.questions.size() ;

        if(questionsNumber > 0)
        {
            for(int i = 0 ; i < questionsNumber ; i++)
            {
                this.mark += this.questions.get(i).getPoints() ;
            }
        }
    }

}
