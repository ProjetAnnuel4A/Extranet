package com.esgi.extranet.QCM ;

import lombok.* ;

import javax.annotation.PostConstruct ;
import javax.validation.constraints.DecimalMin ;
import javax.validation.constraints.Min ;
import javax.validation.constraints.NotNull ;
import java.sql.Date ;
import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Survey
{

    @NotNull
    private String name ;
    private ArrayList<Question> questions ;
    @DecimalMin(value = "0.0")
    private float mark = 0 ;
    @Min(value = -1)
    private int chances = 0 ; // Détermine le nombre d'essais (-1 = infini)
    private Date deadLine ;
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
