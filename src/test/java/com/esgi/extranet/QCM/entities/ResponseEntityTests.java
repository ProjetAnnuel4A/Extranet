package com.esgi.extranet.QCM.entities ;

import com.esgi.extranet.QCM.entities.ResponseEntity ;
import org.junit.Assert ;
import org.junit.Test ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
public class ResponseEntityTests
{

    @Test
    public void should_create_response()
    {
        ResponseEntity response = new ResponseEntity(new Long(1), "Test", "") ;

        Assert.assertNotNull(response) ;

        Assert.assertEquals("Test", response.getDescription()) ;
        Assert.assertEquals("", response.getImagePath()) ;
    }

}
