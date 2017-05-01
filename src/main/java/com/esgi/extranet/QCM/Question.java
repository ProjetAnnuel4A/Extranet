package com.esgi.extranet.QCM ;

import lombok.* ;

import javax.validation.constraints.DecimalMin ;
import javax.validation.constraints.NotNull ;
import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Question
{

    @NotNull
    private String description ;
    private ArrayList<Response> responses ;
    private int[] indexCorrectResponses ;
    @DecimalMin(value = "0.0")
    private float points = 0 ;
    private boolean allOrNot = true ;

}
