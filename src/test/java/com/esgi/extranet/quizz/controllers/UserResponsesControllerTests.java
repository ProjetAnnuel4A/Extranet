package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.repositories.UserResponsesRepository ;
import com.esgi.extranet.quizz.services.interfaces.UserResponsesService ;
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
public class UserResponsesControllerTests
{

    @LocalServerPort
    private int localServerPort ;

    @Autowired
    UserResponsesService userResponsesService ;

    @Autowired
    UserResponsesRepository userResponsesRepository ;


    @Before
    public void init()
    {
        RestAssured.port = localServerPort ;
    }


    @Test
    public void should_add_user_responses() throws Exception
    {
        /*given()
                .log().all()
                .when()
                .post("")
                .then()
                .log().all()
                .statusCode(200).body("user_responses", equalTo("messageTest"));*/
    }

    @Test
    public void should_update_user_responses() throws Exception
    {

    }

    @Test
    public void should_remove_user_responses() throws Exception
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
    public void should_get_user_responses() throws Exception
    {

    }


    @Test
    public void should_get_responses_from_a_user_responses() throws Exception
    {

    }

    @Test
    public void should_add_response_for_a_user_responses() throws Exception
    {

    }

    @Test
    public void should_remove_response_from_a_user_responses() throws Exception
    {

    }

}
