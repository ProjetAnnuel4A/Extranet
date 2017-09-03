package com.esgi.extranet.quizz.services ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;
import com.esgi.extranet.quizz.repositories.UserQuizzRepository ;
import com.esgi.extranet.quizz.repositories.UserQuizzResponsesRepository ;
import com.esgi.extranet.quizz.services.implementations.UserQuizzResponsesServiceImpl ;
import com.esgi.extranet.quizz.services.implementations.UserQuizzServiceImpl ;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.jupiter.api.BeforeAll ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.util.ArrayList ;
import java.util.List ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 18/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {UserQuizzResponsesServiceImpl.class})
public class UserQuizzResponsesServiceTests
{

    @Autowired
    public static UserQuizzServiceImpl userQuizzService ;
    @Autowired
    public static UserQuizzResponsesServiceImpl userQuizzResponsesService ;

    @Autowired
    public static UserQuizzRepository userQuizzRepository ;
    @Autowired
    public static UserQuizzResponsesRepository userQuizzResponsesRepository ;


    private static UserQuizzEntity userQuizz ;
    private static UserQuizzResponsesEntity userQuizzResponses ;


    @BeforeAll
    public static void initialize_datas()
    {
        userQuizzService = new UserQuizzServiceImpl(userQuizzRepository) ;

        userQuizz = UserQuizzEntity.builder()
                .id(new Long(1))
                .userId(new Long(1))
                .surveyId(new Long(2))
                .score(1)
                .build() ;

        userQuizzRepository.save(userQuizz) ;


        userQuizzResponsesService = new UserQuizzResponsesServiceImpl(userQuizzResponsesRepository) ;

        ArrayList<Long> userResponses = new ArrayList<Long>() ;

        userQuizzResponses = UserQuizzResponsesEntity.builder()
                .id(new Long(1))
                .userQuizzId(userQuizz.getId())
                .questionId(new Long(1))
                .responses(userResponses)
                .build() ;

        userQuizzResponsesRepository.save(userQuizzResponses) ;
    }


    @Test
    public void should_add_user_quizz_responses() throws Exception
    {
        UserQuizzResponsesEntity result = userQuizzResponsesService.getUserQuizzResponses(userQuizzResponses.getId()) ;


        Assert.assertNotNull(userQuizzResponses) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals(new Long(1), userQuizzResponses.getUserQuizzId()) ;
        Assert.assertEquals(new Long(1), userQuizzResponses.getQuestionId()) ;
        Assert.assertEquals(new ArrayList<Long>(), userQuizzResponses.getResponses()) ;

        Assert.assertEquals(result.getUserQuizzId(), userQuizzResponses.getUserQuizzId()) ;
        Assert.assertEquals(result.getQuestionId(), userQuizzResponses.getQuestionId()) ;
        Assert.assertEquals(result.getResponses(), userQuizzResponses.getResponses()) ;
    }

    @Test
    public void should_update_user_quizz_responses() throws Exception
    {
        UserQuizzResponsesEntity result = userQuizzResponsesService.updateUserQuizzResponses(new Long(1), new Long(2), new Long(3)) ;


        Assert.assertNotNull(userQuizzResponses) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals(new Long(2), userQuizzResponses.getUserQuizzId()) ;
        Assert.assertEquals(new Long(3), userQuizzResponses.getQuestionId()) ;
    }

    @Test
    public void should_remove_user_quizz_responses() throws Exception
    {
        Assert.assertNotNull(userQuizzResponses) ;


        boolean result = userQuizzResponsesService.removeUserQuizzResponses(userQuizzResponses.getId()) ;


        Assert.assertTrue(result) ;
    }

    @Test
    public void should_get_user_quizz_responses() throws Exception
    {
        UserQuizzResponsesEntity result = userQuizzResponsesRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotNull(userQuizzResponsesService.getUserQuizzResponses(result.getId())) ;
    }


    @Test
    public void should_get_responses_from_an_user_quizz_responses() throws Exception
    {
        List<Long> result = userQuizzResponsesService.getResponsesFromAnUserQuizzResponses(userQuizzResponses.getId()) ;


        Assert.assertNotNull(result) ;
    }

    @Test
    public void should_add_response_from_an_user_quizz_responses() throws Exception
    {
        Long response = new Long(1) ;

        boolean result = userQuizzResponsesService.addResponseForAnUserQuizzResponses(userQuizzResponses.getId(), response) ;

        List<Long> responses = userQuizzResponsesService.getResponsesFromAnUserQuizzResponses(userQuizzResponses.getId()) ;


        Assert.assertNotNull(userQuizzResponses) ;
        Assert.assertNotNull(response) ;
        Assert.assertNotNull(responses) ;
        Assert.assertNotNull(responses.get(0)) ;

        Assert.assertTrue(result) ;

        Assert.assertEquals(1, responses.size()) ;
        Assert.assertEquals(response, responses.get(0)) ;
    }

    @Test
    public void should_remove_response_from_an_user_quizz_responses() throws Exception
    {
        Long response = new Long(1) ;

        boolean resultAdd = userQuizzResponsesService.addResponseForAnUserQuizzResponses(userQuizzResponses.getId(), response) ;
        boolean resultRemove = userQuizzResponsesService.removeResponseFromAnUserQuizzResponses(userQuizzResponses.getId(), response) ;


        Assert.assertNotNull(userQuizzResponses) ;
        Assert.assertNotNull(response) ;

        Assert.assertTrue(resultAdd) ;
        Assert.assertTrue(resultRemove) ;
    }


    @Test
    public void should_get_user_quizz_from_an_user_quizz_responses() throws Exception
    {
        UserQuizzEntity result = userQuizzResponsesService.getUserQuizzFromUserQuizzResponses(userQuizzResponses.getId()) ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(new Long(1), result.getId()) ;
    }

}
