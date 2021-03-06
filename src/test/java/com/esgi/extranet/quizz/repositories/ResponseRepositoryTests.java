package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.ResponseEntity ;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.jupiter.api.BeforeAll ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 01/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ResponseRepositoryTests
{

    @Autowired
    public static ResponseRepository responseRepository ;

    private static ResponseEntity responseEntity ;


    @BeforeAll
    public static void initialize_datas()
    {
        responseEntity = ResponseEntity.builder()
                .id(new Long(1))
                .description("ResponseTest")
                .imageId(new Long(1))
                .build() ;

        responseRepository.save(responseEntity) ;
    }


    @Test
    public void should_find_response_by_id()
    {
        ResponseEntity result = responseRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(result, responseEntity) ;
    }

}
