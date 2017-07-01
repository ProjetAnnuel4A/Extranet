package com.esgi.extranet.quizz.services ;

import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.repositories.ResponseRepository ;
import com.esgi.extranet.quizz.services.implementations.ResponseServiceImpl ;
import org.junit.Assert ;
import org.junit.Before ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {ResponseServiceImpl.class})
public class ResponseServiceTests
{

    @Autowired
    ResponseServiceImpl responseService ;

    @Autowired
    ResponseRepository responseRepository ;


    private ResponseEntity response ;


    @Before
    public void initialize_datas()
    {
        responseService = new ResponseServiceImpl(responseRepository) ;

        ResponseEntity response = new ResponseEntity(new Long(1), "ResponseTest", "") ;

        responseRepository.save(response) ;
    }


    @Test
    public void should_add_response() throws Exception
    {
        ResponseEntity result = responseService.getResponse(response.getId()) ;


        Assert.assertNotNull(response) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals("ResponseTest", response.getDescription()) ;
        Assert.assertEquals("", response.getImagePath()) ;

        Assert.assertEquals(result.getDescription(), response.getDescription()) ;
        Assert.assertEquals(result.getImagePath(), response.getImagePath()) ;
    }

    @Test
    public void should_update_response() throws Exception
    {
        ResponseEntity result = responseService.updateResponse(response.getId(), "ResponseTest 2", "ImagePathTest") ;


        Assert.assertNotNull(response) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals("ResponseTest 2", result.getDescription()) ;
        Assert.assertEquals("ImagePathTest", result.getImagePath()) ;
    }

    @Test
    public void should_remove_response() throws Exception
    {
        Assert.assertNotNull(response) ;


        boolean result = responseService.removeResponse(response.getId()) ;


        Assert.assertTrue(result) ;
    }

    @Test
    public void should_get_response() throws Exception
    {
        ResponseEntity result = responseRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotNull(responseService.getResponse(result.getId())) ;
    }

}
