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
public class UserResponsesControllerTests
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
    public void should_get_all_user_responses()
    {
        given()
            .log().all()
        .when()
            .get("/userResponses")
        .then()
            .log().all()
        .statusCode(200).body("userResponses", hasSize(1)) ;
    }

    @Test
    public void should_add_user_responses() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/userResponses/addUserResponses")
        .then()
            .log().all()
        .statusCode(200).body("userResponses", equalTo("test")) ;
    }

    @Test
    public void should_update_user_responses() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/userResponses/updateUserResponses")
        .then()
            .log().all()
        .statusCode(200).body("userResponses", equalTo("test2")) ;
    }

    @Test
    public void should_remove_user_responses() throws Exception
    {
        given()
            .log().all()
        .when()
            .delete("/userResponses/removeUserResponses")
        .then()
            .log().all()
        .statusCode(200) ;
    }

    @Test
    public void should_get_user_responses() throws Exception
    {
        given()
            .log().all()
        .when()
            .get("/userResponses/getUserResponses")
        .then()
            .log().all()
        .statusCode(200).body("userResponses", equalTo("test")) ;
    }


    @Test
    public void should_get_responses_from_a_user_responses() throws Exception
    {
        given()
            .log().all()
        .when()
            .get("/userResponses/getResponsesFromAnUserResponses")
        .then()
            .log().all()
        .statusCode(200).body("responses", hasSize(1)) ;
    }

    @Test
    public void should_add_response_for_a_user_responses() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/userResponses/addResponseForAnUserResponses")
        .then()
            .log().all()
        .statusCode(200).body("responses", equalTo("test")) ;
    }

    @Test
    public void should_remove_response_from_a_user_responses() throws Exception
    {
        given()
            .log().all()
        .when()
            .delete("/userResponses/removeResponseFromAnUserResponses")
        .then()
            .log().all()
        .statusCode(200) ;
    }

}
