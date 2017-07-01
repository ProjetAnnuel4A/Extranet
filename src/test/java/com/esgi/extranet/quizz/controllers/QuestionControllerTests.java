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
public class QuestionControllerTests
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
    public void should_get_all_questions()
    {
        given()
            .log().all()
        .when()
            .get("/questions")
        .then()
            .log().all()
        .statusCode(200).body("question", hasSize(1)) ;
    }

    @Test
    public void should_add_question() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/questions/addQuestion")
        .then()
            .log().all()
        .statusCode(200).body("question", equalTo("QuestionTest")) ;
    }

    @Test
    public void should_update_question() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/questions/updateQuestion")
        .then()
            .log().all()
        .statusCode(200).body("question", equalTo("QuestionTest 2")) ;
    }

    @Test
    public void should_remove_question() throws Exception
    {
        given()
            .log().all()
        .when()
            .delete("/questions/removeQuestion")
        .then()
            .log().all()
        .statusCode(200) ;
    }

    @Test
    public void should_get_question() throws Exception
    {
        given()
            .log().all()
        .when()
            .get("/questions/getQuestion")
        .then()
            .log().all()
        .statusCode(200).body("question", equalTo("QuestionTest")) ;
    }


    @Test
    public void should_get_responses_from_a_question() throws Exception
    {
        given()
            .log().all()
        .when()
            .get("/questions/getResponsesFromAQuestion")
        .then()
            .log().all()
        .statusCode(200).body("responses", hasSize(1)) ;
    }

    @Test
    public void should_add_response_for_a_question() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/questions/addResponseForAQuestion")
        .then()
            .log().all()
        .statusCode(200).body("responses", equalTo("ResponseTest")) ;
    }

    @Test
    public void should_remove_response_from_a_question() throws Exception
    {
        given()
            .log().all()
        .when()
            .delete("/questions/removeResponseFromAQuestion")
        .then()
            .log().all()
        .statusCode(200) ;
    }


    @Test
    public void should_get_correct_responses_from_a_question() throws Exception
    {
        given()
            .log().all()
        .when()
            .get("/questions/getCorrectResponsesFromAQuestion")
        .then()
            .log().all()
        .statusCode(200).body("correctResponses", hasSize(1)) ;
    }

    @Test
    public void should_add_correct_response_for_a_question() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/questions/addCorrectResponseForAQuestion")
        .then()
            .log().all()
        .statusCode(200).body("correctResponses", equalTo("CorrectResponseTest")) ;
    }

    @Test
    public void should_remove_correct_response_from_a_question() throws Exception
    {
        given()
            .log().all()
        .when()
            .delete("/questions/removeCorrectResponseFromAQuestion")
        .then()
            .log().all()
        .statusCode(200) ;
    }

}
