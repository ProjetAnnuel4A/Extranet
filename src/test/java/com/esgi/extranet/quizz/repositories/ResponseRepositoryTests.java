package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.ResponseEntity ;
import org.junit.Assert ;
import org.junit.Before ;
import org.junit.Test ;
import org.springframework.beans.factory.annotation.Autowired ;

/**
 * Created by Samuel Bijou on 01/07/2017.
 */
public class ResponseRepositoryTests
{

    @Autowired
    private ResponseRepository responseRepository ;

    private ResponseEntity responseEntity ;


    @Before
    public void initialize_datas()
    {
        responseEntity = new ResponseEntity(new Long(1), "ResponseTest", "") ;

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
