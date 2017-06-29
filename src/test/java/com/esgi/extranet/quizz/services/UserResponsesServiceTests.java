package com.esgi.extranet.quizz.services ;

import com.esgi.extranet.quizz.entities.UserResponsesEntity ;
import com.esgi.extranet.quizz.repositories.UserResponsesRepository ;
import com.esgi.extranet.quizz.services.implementations.UserResponsesServiceImpl ;
import org.junit.Assert ;
import org.junit.Before ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.util.ArrayList ;
import java.util.List ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {UserResponsesServiceImpl.class})
public class UserResponsesServiceTests
{

    @Autowired
    UserResponsesServiceImpl userResponsesService ;

    @Autowired
    UserResponsesRepository userResponsesRepository ;


    private UserResponsesEntity userResponses ;


    @Before
    public void initialize_datas()
    {
        userResponsesService = new UserResponsesServiceImpl(userResponsesRepository) ;

        ArrayList<Long> responses = new ArrayList<Long>() ;
        UserResponsesEntity userResponses = new UserResponsesEntity(new Long(1), new Long(1), new Long(2), new Long(3), responses) ;

        userResponsesRepository.save(userResponses) ;
    }


    @Test
    public void should_add_user_responses() throws Exception
    {
        UserResponsesEntity result = userResponsesService.getUserResponses(userResponses.getId()) ;


        Assert.assertNotNull(userResponses) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals(new Long(1), userResponses.getUserId()) ;
        Assert.assertEquals(new Long(2), userResponses.getSurveyId()) ;
        Assert.assertEquals(new Long(3), userResponses.getQuestionId()) ;

        Assert.assertEquals(result.getUserId(), userResponses.getUserId()) ;
        Assert.assertEquals(result.getSurveyId(), userResponses.getSurveyId()) ;
        Assert.assertEquals(result.getQuestionId(), userResponses.getQuestionId()) ;
    }

    @Test
    public void should_update_user_responses() throws Exception
    {
        UserResponsesEntity result = userResponsesService.updateUserResponses(new Long(1), new Long(2), new Long(3), new Long(4)) ;


        Assert.assertNotNull(userResponses) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals(new Long(2), userResponses.getUserId()) ;
        Assert.assertEquals(new Long(3), userResponses.getSurveyId()) ;
        Assert.assertEquals(new Long(4), userResponses.getQuestionId()) ;
    }

    @Test
    public void should_remove_user_responses() throws Exception
    {
        Assert.assertNotNull(userResponses) ;


        boolean result = userResponsesService.removeUserResponses(userResponses.getId()) ;


        Assert.assertTrue(result) ;
    }

    @Test
    public void should_get_user_responses() throws Exception
    {
        UserResponsesEntity result = userResponsesRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotNull(userResponsesService.getUserResponses(result.getId())) ;
    }


    @Test
    public void should_get_responses_from_a_user_responses() throws Exception
    {
        Long response = new Long(1) ;

        boolean result = userResponsesService.addResponseForAUserResponses(userResponses.getId(), response) ;

        List<Long> responses = userResponsesService.getResponsesFromAUserResponses(userResponses.getId()) ;


        Assert.assertNotNull(userResponses) ;
        Assert.assertNotNull(response) ;
        Assert.assertNotNull(responses) ;

        Assert.assertTrue(result) ;

        Assert.assertEquals(1, responses.size()) ;
        Assert.assertEquals(response, responses.get(0)) ;
    }

    @Test
    public void should_add_response_for_a_user_responses() throws Exception
    {
        Long response = new Long(1) ;

        boolean result = userResponsesService.addResponseForAUserResponses(userResponses.getId(), response) ;


        Assert.assertNotNull(userResponses) ;
        Assert.assertNotNull(response) ;

        Assert.assertTrue(result) ;
    }

    @Test
    public void should_remove_response_from_a_user_responses() throws Exception
    {
        Long response = new Long(1) ;

        boolean resultAdd = userResponsesService.addResponseForAUserResponses(userResponses.getId(), response) ;
        boolean resultRemove = userResponsesService.removeResponseFromAUserResponses(userResponses.getId(), response) ;


        Assert.assertNotNull(userResponses) ;
        Assert.assertNotNull(response) ;

        Assert.assertTrue(resultAdd) ;
        Assert.assertTrue(resultRemove) ;
    }

}
