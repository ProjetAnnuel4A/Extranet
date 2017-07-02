package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.ResponseEntity ;
import org.junit.Assert ;
import org.junit.BeforeClass ;
import org.junit.Test ;
import org.springframework.beans.factory.annotation.Autowired ;

/**
 * Created by Samuel Bijou on 01/07/2017.
 */
public class ResponseRepositoryTests
{

    @Autowired
    public static ResponseRepository responseRepository ;

    private static ResponseEntity responseEntity ;


    @BeforeClass
    public static void initialize_datas()
    {
        responseEntity = new ResponseEntity(new Long(1), "ResponseTest", new Long(1)) ;

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
