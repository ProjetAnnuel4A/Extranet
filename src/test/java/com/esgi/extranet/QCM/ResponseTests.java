package com.esgi.extranet.QCM ;

import org.junit.Assert ;
import org.junit.Test ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
public class ResponseTests
{

    @Test
    public void should_create_response()
    {
        Response response = new Response("Test", "") ;

        Assert.assertNotNull(response) ;

        Assert.assertEquals("Test", response.getDescription()) ;
        Assert.assertEquals("", response.getImagePath()) ;
    }

}
