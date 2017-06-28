package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.repositories.SurveyRepository ;
import com.esgi.extranet.quizz.services.interfaces.SurveyService ;
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
public class SurveyControllerTests
{

    @LocalServerPort
    private int localServerPort ;

    @Autowired
    SurveyService surveyService ;

    @Autowired
    SurveyRepository surveyRepository ;


    @Before
    public void init()
    {
        RestAssured.port = localServerPort ;
    }


    @Test
    public void should_add_survey() throws Exception
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
    public void should_update_survey() throws Exception
    {

    }

    @Test
    public void should_remove_survey() throws Exception
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
    public void should_get_survey() throws Exception
    {

    }


    @Test
    public void should_get_questions_from_a_survey() throws Exception
    {

    }

    @Test
    public void should_add_question_for_a_survey() throws Exception
    {

    }

    @Test
    public void should_remove_question_from_a_survey() throws Exception
    {

    }

}
