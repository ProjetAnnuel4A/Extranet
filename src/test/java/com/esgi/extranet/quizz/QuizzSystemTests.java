package com.esgi.extranet.quizz ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.school.entities.StudentEntity ;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.jupiter.api.BeforeAll ;
import org.junit.runner.RunWith ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.sql.Date ;
import java.time.LocalDate ;
import java.util.ArrayList ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 07/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class QuizzSystemTests
{

    private static ResponseEntity r1 ;
    private static ResponseEntity r2 ;
    private static ResponseEntity r3 ;
    private static ResponseEntity r4 ;

    private static ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;


    private static ArrayList<Long> correctResponses = new ArrayList<Long>() ;


    private static QuestionEntity question ;

    private static QuestionEntity q1 ;
    private static QuestionEntity q2 ;
    private static QuestionEntity q3 ;

    private static ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;


    private static SurveyEntity survey ;


    private static StudentEntity student ;


    private static UserQuizzEntity userQuizz ;


    @BeforeAll
    public static void initialize_datas()
    {
        r1 = new ResponseEntity(new Long(1), "ResponseTest 1", new Long(1)) ;
        r2 = new ResponseEntity(new Long(2), "ResponseTest 2", new Long(1)) ;
        r3 = new ResponseEntity(new Long(3), "ResponseTest 3", new Long(1)) ;
        r4 = new ResponseEntity(new Long(4), "ResponseTest 4", new Long(1)) ;

        responses.add(r1) ;
        responses.add(r2) ;
        responses.add(r3) ;
        responses.add(r4) ;


        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;


        question = new QuestionEntity(new Long(1), "QuestionTest", responses, correctResponses, 6, true, new Long(1)) ;

        q1 = new QuestionEntity(new Long(1), "QuestionTest 1", responses, correctResponses, 6, true, new Long(1)) ;
        q2 = new QuestionEntity(new Long(2), "QuestionTest 2", responses, correctResponses, 8, true, new Long(1)) ;
        q3 = new QuestionEntity(new Long(3), "QuestionTest 3", responses, correctResponses, 6, true, new Long(1)) ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;


        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, null, new Long(1)) ;


        LocalDate localDateTest = null ;
        student = new StudentEntity(new Long(2), new Long(1), "Testeur", "Test", "testeur@testmail.com", localDateTest, "", "") ;


        userQuizz = new UserQuizzEntity(new Long(1), student.getId(), survey.getId(), question.getId(), correctResponses, 1) ;
    }


    @Test
    public void should_calculate_question_score_with_user_quizz()
    {
        ArrayList<Long> userResponses = new ArrayList<Long>() ;
        userResponses.add(new Long(2)) ;
        userResponses.add(new Long(3)) ;

        userQuizz.setResponses(userResponses) ;


        Assert.assertNotNull(QuizzSystem.calculateQuestionScore(question, userQuizz)) ;
    }

    @Test
    public void should_give_all_question_points_with_user_quizz()
    {
        ArrayList<Long> userResponses = new ArrayList<Long>() ;
        userResponses.add(new Long(2)) ;
        userResponses.add(new Long(3)) ;

        userQuizz.setResponses(userResponses) ;


        Assert.assertEquals(6.0, QuizzSystem.calculateQuestionScore(question, userQuizz), 0) ;
    }

    @Test
    public void should_give_some_question_points_with_user_quizz()
    {
        question.setAllOrNot(false) ;

        ArrayList<Long> userResponses = new ArrayList<Long>() ;
        userResponses.add(new Long(2)) ;

        userQuizz.setResponses(userResponses) ;


        Assert.assertEquals(3.0, QuizzSystem.calculateQuestionScore(question, userQuizz), 0) ;
    }

    @Test
    public void should_not_give_question_points_with_user_quizz()
    {
        ArrayList<Long> userResponses = new ArrayList<Long>() ;
        userResponses.add(new Long(1)) ;
        userResponses.add(new Long(4)) ;

        userQuizz.setResponses(userResponses) ;


        Assert.assertEquals(0.0, QuizzSystem.calculateQuestionScore(question, userQuizz), 0) ;
    }

    @Test
    public void should_calculate_question_score()
    {
        int[] userResponses = new int[2] ;
        userResponses[0] = 2 ;
        userResponses[1] = 3 ;


        Assert.assertNotNull(QuizzSystem.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_give_all_question_points()
    {
        int[] userResponses = new int[2] ;
        userResponses[0] = 2 ;
        userResponses[1] = 3 ;


        Assert.assertEquals(6.0, QuizzSystem.calculateQuestionScore(question, userResponses), 0) ;
    }

    @Test
    public void should_give_some_question_points()
    {
        question.setAllOrNot(false) ;

        int[] userResponses = new int[1] ;
        userResponses[0] = 2 ;


        Assert.assertEquals(3.0, QuizzSystem.calculateQuestionScore(question, userResponses), 0) ;
    }

    @Test
    public void should_not_give_question_points()
    {
        int[] userResponses = new int[2] ;
        userResponses[0] = 1 ;
        userResponses[1] = 4 ;


        Assert.assertEquals(0.0, QuizzSystem.calculateQuestionScore(question, userResponses), 0) ;
    }

    @Test
    public void should_calculate_survey_score_with_user_quizz()
    {
        UserQuizzEntity[] usersQuizz = new UserQuizzEntity[3] ;

        ArrayList<Long> userResponses = new ArrayList<Long>() ;
        userResponses.add(new Long(2)) ;
        userResponses.add(new Long(3)) ;

        usersQuizz[0].setResponses(userResponses) ;
        usersQuizz[1].setResponses(userResponses) ;
        usersQuizz[2].setResponses(userResponses) ;


        Assert.assertNotNull(QuizzSystem.calculateSurveyScore(survey, usersQuizz)) ;
    }

    @Test
    public void should_give_all_survey_points_with_user_quizz()
    {
        UserQuizzEntity[] usersQuizz = new UserQuizzEntity[3] ;

        ArrayList<Long> userResponses = new ArrayList<Long>() ;
        userResponses.add(new Long(2)) ;
        userResponses.add(new Long(3)) ;

        usersQuizz[0].setResponses(userResponses) ;
        usersQuizz[1].setResponses(userResponses) ;
        usersQuizz[2].setResponses(userResponses) ;


        Assert.assertEquals(20.0, QuizzSystem.calculateSurveyScore(survey, usersQuizz), 0) ;
    }

    @Test
    public void should_give_some_survey_points_with_user_quizz()
    {
        UserQuizzEntity[] usersQuizz = new UserQuizzEntity[3] ;

        ArrayList<Long> userResponses1 = new ArrayList<Long>() ;
        userResponses1.add(new Long(2)) ;
        userResponses1.add(new Long(3)) ;

        ArrayList<Long> userResponses2 = new ArrayList<Long>() ;
        userResponses2.add(new Long(1)) ;
        userResponses2.add(new Long(3)) ;

        ArrayList<Long> userResponses3 = new ArrayList<Long>() ;
        userResponses3.add(new Long(2)) ;
        userResponses3.add(new Long(4)) ;

        usersQuizz[0].setResponses(userResponses1) ;
        usersQuizz[1].setResponses(userResponses2) ;
        usersQuizz[2].setResponses(userResponses3) ;


        Assert.assertEquals(6.0, QuizzSystem.calculateSurveyScore(survey, usersQuizz), 0) ;
    }

    @Test
    public void should_not_give_survey_points_with_user_quizz()
    {
        UserQuizzEntity[] usersQuizz = new UserQuizzEntity[3] ;

        ArrayList<Long> userResponses = new ArrayList<Long>() ;
        userResponses.add(new Long(4)) ;
        userResponses.add(new Long(4)) ;

        usersQuizz[0].setResponses(userResponses) ;
        usersQuizz[1].setResponses(userResponses) ;
        usersQuizz[2].setResponses(userResponses) ;


        Assert.assertEquals(0.0, QuizzSystem.calculateSurveyScore(survey, usersQuizz), 0) ;
    }

    @Test
    public void should_calculate_survey_score()
    {
        int[][] userResponses = new int[3][2] ;
        userResponses[0][0] = 2 ;
        userResponses[0][1] = 3 ;
        userResponses[1][0] = 2 ;
        userResponses[1][1] = 3 ;
        userResponses[2][0] = 2 ;
        userResponses[2][1] = 3 ;


        Assert.assertNotNull(QuizzSystem.calculateSurveyScore(survey, userResponses)) ;
    }

    @Test
    public void should_give_all_survey_points()
    {
        int[][] userResponses = new int[3][2] ;
        userResponses[0][0] = 2 ;
        userResponses[0][1] = 3 ;
        userResponses[1][0] = 2 ;
        userResponses[1][1] = 3 ;
        userResponses[2][0] = 2 ;
        userResponses[2][1] = 3 ;


        Assert.assertEquals(20.0, QuizzSystem.calculateSurveyScore(survey, userResponses), 0) ;
    }

    @Test
    public void should_give_some_survey_points()
    {
        int[][] userResponses = new int[3][2] ;
        userResponses[0][0] = 2 ;
        userResponses[0][1] = 3 ;
        userResponses[1][0] = 1 ;
        userResponses[1][1] = 3 ;
        userResponses[2][0] = 2 ;
        userResponses[2][1] = 4 ;


        Assert.assertEquals(6.0, QuizzSystem.calculateSurveyScore(survey, userResponses), 0) ;
    }

    @Test
    public void should_not_give_survey_points()
    {
        int[][] userResponses = new int[3][2] ;
        userResponses[0][0] = 4 ;
        userResponses[0][1] = 4 ;
        userResponses[1][0] = 4 ;
        userResponses[1][1] = 4 ;
        userResponses[2][0] = 4 ;
        userResponses[2][1] = 4 ;


        Assert.assertEquals(0.0, QuizzSystem.calculateSurveyScore(survey, userResponses), 0) ;
    }


    @Test
    public void should_calculate_survey_mark()
    {
        QuizzSystem.calculateSurveyMark(survey) ;


        Assert.assertEquals(20, survey.getMark(), 0) ;
    }

    @Test
    public void should_survey_is_infinite()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 0, new Date(2037, 6, 30), new Long(1)) ;


        Assert.assertTrue(QuizzSystem.surveyIsInfinite(survey)) ;
    }

    @Test
    public void should_survey_is_not_infinite()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, new Date(2037, 6, 30), new Long(1)) ;


        Assert.assertFalse(QuizzSystem.surveyIsInfinite(survey)) ;
    }

    @Test
    public void should_survey_is_open()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, new Date(2037, 6, 30), new Long(1)) ;


        Assert.assertTrue(QuizzSystem.surveyIsOpen(survey)) ;
    }

    @Test
    public void should_survey_is_open_without_deadline()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, null, new Long(1)) ;


        Assert.assertTrue(QuizzSystem.surveyIsOpen(survey)) ;
    }

    @Test
    public void should_survey_is_not_open()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, new Date(2017, 6, 29), new Long(1)) ;


        Assert.assertFalse(QuizzSystem.surveyIsOpen(survey)) ;
    }

    @Test
    public void should_user_quizz_can_answer_survey()
    {
        userQuizz = new UserQuizzEntity(new Long(1), student.getId(), survey.getId(), question.getId(), correctResponses, 1) ;

        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 2, new Date(2037, 6, 30), new Long(1)) ;


        Assert.assertTrue(QuizzSystem.userQuizzCanAnswerSurvey(userQuizz, survey)) ;
    }

    @Test
    public void should_user_quizz_can_answer_infinite_survey()
    {
        userQuizz = new UserQuizzEntity(new Long(1), student.getId(), survey.getId(), question.getId(), correctResponses, 1) ;

        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 0, new Date(2037, 6, 30), new Long(1)) ;


        Assert.assertTrue(QuizzSystem.userQuizzCanAnswerSurvey(userQuizz, survey)) ;
    }

    @Test
    public void should_user_quizz_can_not_answer_survey()
    {
        userQuizz = new UserQuizzEntity(new Long(1), student.getId(), survey.getId(), question.getId(), correctResponses, 1) ;

        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, new Date(2037, 6, 30), new Long(1)) ;


        Assert.assertTrue(QuizzSystem.userQuizzCanAnswerSurvey(userQuizz, survey)) ;
    }

}
