package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.QuizzDatas ;
import com.jayway.restassured.RestAssured ;
import org.junit.Before ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.boot.context.embedded.LocalServerPort ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import static com.jayway.restassured.RestAssured.given ;
import static org.hamcrest.Matchers.equalTo ;
import static org.hamcrest.Matchers.hasSize ;
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


    @Before
    public void initialize_datas()
    {
        RestAssured.port = localServerPort ;

        QuizzDatas.initialize_datas() ;
    }


    @Test
    public void should_get_all_surveys()
    {
        given()
            .log().all()
        .when()
            .get("/surveys")
        .then()
            .log().all()
        .statusCode(200).body("surveys", hasSize(1)) ;
    }

    @Test
    public void should_add_survey() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/surveys/addSurvey")
        .then()
            .log().all()
        .statusCode(200).body("surveys", equalTo("test")) ;
    }

    @Test
    public void should_update_survey() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/surveys/updateSurvey")
        .then()
            .log().all()
        .statusCode(200).body("surveys", equalTo("test2")) ;
    }

    @Test
    public void should_remove_survey() throws Exception
    {
        given()
            .log().all()
        .when()
            .delete("/surveys/removeSurvey")
        .then()
            .log().all()
        .statusCode(200) ;
    }

    @Test
    public void should_get_survey() throws Exception
    {
        given()
            .log().all()
        .when()
            .get("/surveys/getSurvey")
        .then()
            .log().all()
        .statusCode(200).body("surveys", equalTo("test")) ;
    }


    @Test
    public void should_get_questions_from_a_survey() throws Exception
    {
        given()
            .log().all()
        .when()
            .get("/surveys/getQuestionsFromASurvey")
        .then()
            .log().all()
        .statusCode(200).body("questions", hasSize(1)) ;
    }

    @Test
    public void should_add_question_for_a_survey() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/surveys/addQuestionForASurvey")
        .then()
            .log().all()
        .statusCode(200).body("questions", equalTo("test")) ;
    }

    @Test
    public void should_remove_question_from_a_survey() throws Exception
    {
        given()
            .log().all()
        .when()
            .delete("/surveys/removeQuestionFromASurvey")
        .then()
            .log().all()
        .statusCode(200) ;
    }

}
