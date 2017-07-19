package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.QuizzDatas ;
import com.jayway.restassured.RestAssured ;
import org.junit.Test ;
import org.junit.jupiter.api.BeforeAll ;
import org.junit.runner.RunWith ;
import org.springframework.boot.context.embedded.LocalServerPort ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import static com.jayway.restassured.RestAssured.given ;
import static org.hamcrest.Matchers.equalTo ;
import static org.hamcrest.Matchers.hasSize ;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 18/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserQuizzResponsesControllerTests
{

    @LocalServerPort
    private static int localServerPort ;


    @BeforeAll
    public static void initialize_datas()
    {
        RestAssured.port = localServerPort ;

        QuizzDatas.initialize_datas() ;
    }

}
