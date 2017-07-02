package com.esgi.extranet.quizz.services ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.repositories.UserQuizzRepository ;
import com.esgi.extranet.quizz.services.implementations.UserQuizzServiceImpl ;
import org.junit.Assert ;
import org.junit.BeforeClass ;
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
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {UserQuizzServiceImpl.class})
public class UserQuizzServiceTests
{

    @Autowired
    public static UserQuizzServiceImpl userQuizzService ;

    @Autowired
    public static UserQuizzRepository userQuizzRepository ;


    private static UserQuizzEntity userQuizz ;


    @BeforeClass
    public static void initialize_datas()
    {
        userQuizzService = new UserQuizzServiceImpl(userQuizzRepository) ;

        ArrayList<Long> userResponses = new ArrayList<Long>() ;
        userQuizz = new UserQuizzEntity(new Long(1), new Long(1), new Long(2), new Long(3), userResponses, 1) ;

        userQuizzRepository.save(userQuizz) ;
    }


    @Test
    public void should_add_user_quizz() throws Exception
    {
        UserQuizzEntity result = userQuizzService.getUserQuizz(userQuizz.getId()) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals(new Long(1), userQuizz.getUserId()) ;
        Assert.assertEquals(new Long(2), userQuizz.getSurveyId()) ;
        Assert.assertEquals(new Long(3), userQuizz.getQuestionId()) ;
        Assert.assertEquals(1, userQuizz.getCount()) ;

        Assert.assertEquals(result.getUserId(), userQuizz.getUserId()) ;
        Assert.assertEquals(result.getSurveyId(), userQuizz.getSurveyId()) ;
        Assert.assertEquals(result.getQuestionId(), userQuizz.getQuestionId()) ;
        Assert.assertEquals(result.getCount(), userQuizz.getCount()) ;
    }

    @Test
    public void should_update_user_quizz() throws Exception
    {
        UserQuizzEntity result = userQuizzService.updateUserQuizz(new Long(1), new Long(2), new Long(3), new Long(4), 2) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals(new Long(2), userQuizz.getUserId()) ;
        Assert.assertEquals(new Long(3), userQuizz.getSurveyId()) ;
        Assert.assertEquals(new Long(4), userQuizz.getQuestionId()) ;
        Assert.assertEquals(2, userQuizz.getCount()) ;
    }

    @Test
    public void should_remove_user_quizz() throws Exception
    {
        Assert.assertNotNull(userQuizz) ;


        boolean result = userQuizzService.removeUserQuizz(userQuizz.getId()) ;


        Assert.assertTrue(result) ;
    }

    @Test
    public void should_get_user_quizz() throws Exception
    {
        UserQuizzEntity result = userQuizzRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotNull(userQuizzService.getUserQuizz(result.getId())) ;
    }


    @Test
    public void should_get_responses_from_an_user_quizz() throws Exception
    {
        Long response = new Long(1) ;

        boolean result = userQuizzService.addResponseForAnUserQuizz(userQuizz.getId(), response) ;

        List<Long> responses = userQuizzService.getResponsesFromAnUserQuizz(userQuizz.getId()) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(response) ;
        Assert.assertNotNull(responses) ;

        Assert.assertTrue(result) ;

        Assert.assertEquals(1, responses.size()) ;
        Assert.assertEquals(response, responses.get(0)) ;
    }

    @Test
    public void should_add_response_for_an_user_quizz() throws Exception
    {
        Long response = new Long(1) ;

        boolean result = userQuizzService.addResponseForAnUserQuizz(userQuizz.getId(), response) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(response) ;

        Assert.assertTrue(result) ;
    }

    @Test
    public void should_remove_response_from_an_user_quizz() throws Exception
    {
        Long response = new Long(1) ;

        boolean resultAdd = userQuizzService.addResponseForAnUserQuizz(userQuizz.getId(), response) ;
        boolean resultRemove = userQuizzService.removeResponseFromAnUserQuizz(userQuizz.getId(), response) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(response) ;

        Assert.assertTrue(resultAdd) ;
        Assert.assertTrue(resultRemove) ;
    }

}
