package com.esgi.extranet.QCM ;

import lombok.* ;

import javax.validation.constraints.NotNull ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Response
{

    @NotNull
    private String description ;
    private String imagePath ;

}
