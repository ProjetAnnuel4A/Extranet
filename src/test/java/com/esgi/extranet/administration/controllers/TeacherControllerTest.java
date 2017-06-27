package com.esgi.extranet.administration.controllers;

import com.esgi.extranet.administration.repositories.TeacherRepository;
import com.esgi.extranet.administration.services.TeacherService;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author timotheearnauld
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TeacherControllerTest {
    @LocalServerPort
    private int localServerPort;

    @Autowired
    TeacherService teacherService;

    @Autowired
    TeacherRepository teacherRepository;

    @Before
    public void init(){
        RestAssured.port = localServerPort;
    }

    @Test
    public void addTeacher() throws Exception {
        /*given()
                .log().all()
                .when()
                .post("")
                .then()
                .log().all()
                .statusCode(200).body("teachers", equalTo("messageTest"));*/
    }

    @Test
    public void removeTeacher() throws Exception {
        /*given()
                .log().all()
                .when()
                .delete("")
                .then()
                .log().all()
                .statusCode(200);*/
    }

}