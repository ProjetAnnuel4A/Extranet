package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.QuizzDatas ;
import com.jayway.restassured.RestAssured ;
import org.junit.BeforeClass ;
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
public class UserEntityQuizzControllerTests
{

    @LocalServerPort
    private static int localServerPort ;


    @BeforeClass
    public static void initialize_datas()
    {
        RestAssured.port = localServerPort ;

        QuizzDatas.initialize_datas() ;
    }


    @Test
    public void should_get_all_user_quizz()
    {
        given()
            .log().all()
        .when()
            .get("/usersQuizz")
        .then()
            .log().all()
        .statusCode(200).body("userQuizz", hasSize(1)) ;
    }

    @Test
    public void should_add_user_quizz() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/usersQuizz/addUserQuizz")
        .then()
            .log().all()
        .statusCode(200).body("userQuizz", equalTo("UserQuizzTest")) ;
    }

    @Test
    public void should_update_user_quizz() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/usersQuizz/updateUserQuizz")
        .then()
            .log().all()
        .statusCode(200).body("userQuizz", equalTo("UserQuizzTest 2")) ;
    }

    @Test
    public void should_remove_user_quizz() throws Exception
    {
        given()
            .log().all()
        .when()
            .delete("/usersQuizz/removeUserQuizz")
        .then()
            .log().all()
        .statusCode(200) ;
    }

    @Test
    public void should_get_user_quizz() throws Exception
    {
        given()
            .log().all()
        .when()
            .get("/usersQuizz/getUserQuizz")
        .then()
            .log().all()
        .statusCode(200).body("userQuizz", equalTo("UserQuizzTest")) ;
    }


    @Test
    public void should_get_responses_from_an_user_quizz() throws Exception
    {
        given()
            .log().all()
        .when()
            .get("/usersQuizz/getResponsesFromAnUserQuizz")
        .then()
            .log().all()
        .statusCode(200).body("responses", hasSize(1)) ;
    }

    @Test
    public void should_add_response_for_an_user_quizz() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/usersQuizz/addResponseForAnUserQuizz")
        .then()
            .log().all()
        .statusCode(200).body("responses", equalTo("ResponseTest")) ;
    }

    @Test
    public void should_remove_response_from_an_user_quizz() throws Exception
    {
        given()
            .log().all()
        .when()
            .delete("/usersQuizz/removeResponseFromAnUserQuizz")
        .then()
            .log().all()
        .statusCode(200) ;
    }

}
