package com.esgi.extranet.quizz.entities ;

import org.junit.Assert ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ResponseEntityTests
{

    @Test
    public void should_create_response()
    {
        ResponseEntity response = ResponseEntity.builder()
                .id(new Long(1))
                .description("ResponseTest")
                .imageId(new Long(1))
                .build() ;


        Assert.assertNotNull(response) ;

        Assert.assertEquals("ResponseTest", response.getDescription()) ;
        Assert.assertEquals(new Long(1), response.getImageId()) ;
    }

}
