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
 * Created by Samuel Bijou on 28/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {UserQuizzServiceImpl.class})
public class UserQuizzServiceTests
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
                .count(1)
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
    public void should_add_user_quizz() throws Exception
    {
        UserQuizzEntity result = userQuizzService.getUserQuizz(userQuizz.getId()) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals(new Long(1), userQuizz.getUserId()) ;
        Assert.assertEquals(new Long(2), userQuizz.getSurveyId()) ;
        Assert.assertEquals(1, userQuizz.getCount()) ;

        Assert.assertEquals(result.getUserId(), userQuizz.getUserId()) ;
        Assert.assertEquals(result.getSurveyId(), userQuizz.getSurveyId()) ;
        Assert.assertEquals(result.getCount(), userQuizz.getCount()) ;
    }

    @Test
    public void should_update_user_quizz() throws Exception
    {
        UserQuizzEntity result = userQuizzService.updateUserQuizz(new Long(1), new Long(2), new Long(3), 2) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals(new Long(2), userQuizz.getUserId()) ;
        Assert.assertEquals(new Long(3), userQuizz.getSurveyId()) ;
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
    public void should_get_all_users_quizz_by_user_id() throws Exception
    {
        List<UserQuizzEntity> result = userQuizzRepository.findAllByUserId(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotNull(userQuizzService.getUserQuizz(result.get(0).getId())) ;
    }

    @Test
    public void should_get_user_quizz() throws Exception
    {
        UserQuizzEntity result = userQuizzRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotNull(userQuizzService.getUserQuizz(result.getId())) ;
    }

    @Test
    public void should_get_user_quizz_by_user_id_and_survey_id() throws Exception
    {
        UserQuizzEntity result = userQuizzRepository.findByUserIdAndSurveyId(new Long(1), new Long(2)) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotNull(userQuizzService.getUsersQuizzByUserIdAndSurveyId(result.getUserId(), result.getSurveyId())) ;
    }


    @Test
    public void should_get_all_user_quizz_responses_from_an_user_quizz() throws Exception
    {
        Long response = new Long(1) ;

        boolean result = userQuizzService.addResponseForAnUserQuizz(userQuizz.getId(), new Long(1), response) ;

        List<UserQuizzResponsesEntity> responses = userQuizzService.getAllUserQuizzResponsesFromAnUserQuizz(userQuizz.getId()) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(response) ;
        Assert.assertNotNull(responses) ;
        Assert.assertNotNull(responses.get(0)) ;
        Assert.assertNotNull(responses.get(0).getResponses()) ;

        Assert.assertTrue(result) ;

        Assert.assertEquals(1, responses.size()) ;
        Assert.assertEquals(response, responses.get(0).getResponses().get(0)) ;
    }

    @Test
    public void should_remove_all_user_quizz_responses_from_an_user_quizz() throws Exception
    {
        Long response = new Long(1) ;

        boolean resultAdd = userQuizzService.addResponseForAnUserQuizz(userQuizz.getId(), new Long(1), response) ;
        boolean resultRemove = userQuizzService.removeAllUserQuizzResponsesFromAnUserQuizz(userQuizz.getId()) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(response) ;

        Assert.assertTrue(resultAdd) ;
        Assert.assertTrue(resultRemove) ;
    }

    @Test
    public void should_get_user_quizz_responses_from_an_user_quizz() throws Exception
    {
        Long response = new Long(1) ;

        boolean result = userQuizzService.addResponseForAnUserQuizz(userQuizz.getId(), new Long(1), response) ;

        UserQuizzResponsesEntity userQuizzResponses = userQuizzService.getUserQuizzResponsesFromAnUserQuizz(userQuizz.getId(), new Long(1)) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(response) ;
        Assert.assertNotNull(userQuizzResponses) ;

        Assert.assertTrue(result) ;

        Assert.assertEquals(1, userQuizzResponses.getResponses().size()) ;
        Assert.assertEquals(response, userQuizzResponses.getResponses().get(0)) ;
    }

    @Test
    public void should_get_response_from_an_user_quizz() throws Exception
    {
        Long response = new Long(1) ;

        boolean result = userQuizzService.addResponseForAnUserQuizz(userQuizz.getId(), new Long(1), response) ;

        List<Long> responses = userQuizzService.getResponsesFromAnUserQuizz(userQuizz.getId(), new Long(1)) ;


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

        boolean result = userQuizzService.addResponseForAnUserQuizz(userQuizz.getId(), new Long(1), response) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(response) ;

        Assert.assertTrue(result) ;
    }

    @Test
    public void should_remove_response_from_an_user_quizz() throws Exception
    {
        Long response = new Long(1) ;

        boolean resultAdd = userQuizzService.addResponseForAnUserQuizz(userQuizz.getId(), new Long(1), response) ;
        boolean resultRemove = userQuizzService.removeResponseFromAnUserQuizz(userQuizz.getId(), new Long(1), response) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(response) ;

        Assert.assertTrue(resultAdd) ;
        Assert.assertTrue(resultRemove) ;
    }

}
