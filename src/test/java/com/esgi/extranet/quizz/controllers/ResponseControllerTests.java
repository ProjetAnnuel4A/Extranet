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
public class ResponseControllerTests
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
    public void should_get_all_responses() throws Exception
    {
        given()
            .log().all()
        .when()
            .get("/responses")
        .then()
            .log().all()
        .statusCode(200).body("response", hasSize(1)) ;
    }

    @Test
    public void should_add_response() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/responses/addResponse")
        .then()
            .log().all()
        .statusCode(200).body("response", equalTo("ResponseTest")) ;
    }

    @Test
    public void should_update_response() throws Exception
    {
        given()
            .log().all()
        .when()
            .post("/responses/updateResponse")
        .then()
            .log().all()
        .statusCode(200).body("response", equalTo("ResponseTest 2")) ;
    }

    @Test
    public void should_remove_response() throws Exception
    {
        given()
            .log().all()
        .when()
            .delete("/responses/removeResponse")
        .then()
            .log().all()
        .statusCode(200) ;
    }

    @Test
    public void should_get_response() throws Exception
    {
        given()
            .log().all()
        .when()
            .get("/responses/getResponse")
        .then()
            .log().all()
        .statusCode(200).body("response", equalTo("ResponseTest")) ;
    }

}
