package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.repositories.QuestionRepository ;
import com.esgi.extranet.quizz.services.interfaces.QuestionService ;
import com.jayway.restassured.RestAssured ;
import org.junit.Before ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.context.embedded.LocalServerPort ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class QuestionControllerTests
{

    @LocalServerPort
    private int localServerPort ;

    @Autowired
    QuestionService questionService ;

    @Autowired
    QuestionRepository questionRepository ;


    @Before
    public void init()
    {
        RestAssured.port = localServerPort ;
    }


    @Test
    public void should_add_question() throws Exception
    {
        /*given()
                .log().all()
                .when()
                .post("")
                .then()
                .log().all()
                .statusCode(200).body("surveys", equalTo("messageTest"));*/
    }

    @Test
    public void should_update_question() throws Exception
    {

    }

    @Test
    public void should_remove_question() throws Exception
    {
        /*given()
                .log().all()
                .when()
                .delete("")
                .then()
                .log().all()
                .statusCode(200);*/
    }

    @Test
    public void should_get_question() throws Exception
    {

    }


    @Test
    public void should_get_response_from_a_question() throws Exception
    {

    }

    @Test
    public void should_add_response_for_a_question() throws Exception
    {

    }

    @Test
    public void should_remove_response_from_a_question() throws Exception
    {

    }


    @Test
    public void should_get_correct_responses_from_a_question() throws Exception
    {

    }

    @Test
    public void should_add_correct_response_for_a_question() throws Exception
    {

    }

    @Test
    public void should_remove_correct_response_from_a_question() throws Exception
    {

    }

}
