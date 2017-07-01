package com.esgi.extranet.quizz.entities ;

import lombok.* ;
import org.codehaus.jackson.annotate.JsonIgnoreProperties ;

import javax.persistence.* ;
import javax.annotation.PostConstruct ;
import javax.validation.constraints.DecimalMin ;
import javax.validation.constraints.Min ;
import javax.validation.constraints.NotNull ;
import java.sql.Date ;
import java.time.LocalDate ;
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
@Table(name = "survey")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SurveyEntity
{

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name ;

    @ManyToMany
    @JoinColumn(table = "question")
    private List<QuestionEntity> questions ;

    @NotNull
    @Column(nullable = false)
    @DecimalMin(value = "0.0")
    private float mark = 0 ;

    @NotNull
    @Column(nullable = false)
    @Min(value = 0)
    private int chances = 1 ; // Détermine le nombre d'essais (0 = infini)

    @Column
    private Date deadLine ;

    @Column
    private Long imageId ;


    @PostConstruct // Ne fonctionne pas
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

    public boolean isInfinite()
    {
        return this.chances == 0 ;
    }

    public boolean isOpen()
    {
        if(this.deadLine == null)
        {
            return true ;
        }

        LocalDate today = LocalDate.now() ;
        Date todayConverted = Date.valueOf(today) ;

        return this.deadLine.after(todayConverted) ;
    }

}
