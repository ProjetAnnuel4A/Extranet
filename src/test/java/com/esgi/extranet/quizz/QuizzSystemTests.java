package com.esgi.extranet.quizz ;

import com.esgi.extranet.login.UserEntity ;
import com.esgi.extranet.quizz.entities.* ;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.jupiter.api.BeforeAll ;
import org.junit.runner.RunWith ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.sql.Date ;
import java.text.ParseException ;
import java.text.SimpleDateFormat ;
import java.time.LocalDate ;
import java.util.ArrayList ;

import static com.esgi.extranet.login.Role.STUDENT ;
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


    private static ArrayList<ResponseEntity> correctResponses = new ArrayList<ResponseEntity>() ;
    private static ArrayList<Long> correctResponsesId = new ArrayList<Long>() ;


    private static QuestionEntity question ;

    private static QuestionEntity q1 ;
    private static QuestionEntity q2 ;
    private static QuestionEntity q3 ;

    private static ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;


    private static SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd") ;
    private static Date deadLine = null ;

    private static SurveyEntity survey ;


    private static UserEntity student ;


    private static UserQuizzEntity userQuizz ;

    private static UserQuizzResponsesEntity userQuizzResponses ;


    @BeforeAll
    public static void initialize_datas()
    {
        r1 = ResponseEntity.builder()
                .id(new Long(1))
                .description("ResponseTest 1")
                .imageId(new Long(1))
                .build() ;
        r2 = ResponseEntity.builder()
                .id(new Long(2))
                .description("ResponseTest 2")
                .imageId(new Long(1))
                .build() ;
        r3 = ResponseEntity.builder()
                .id(new Long(3))
                .description("ResponseTest 3")
                .imageId(new Long(1))
                .build() ;
        r4 = ResponseEntity.builder()
                .id(new Long(4))
                .description("ResponseTest 4")
                .imageId(new Long(1))
                .build() ;

        responses.add(r1) ;
        responses.add(r2) ;
        responses.add(r3) ;
        responses.add(r4) ;


        correctResponses.add(r2) ;
        correctResponses.add(r3) ;

        correctResponsesId.add(r2.getId()) ;
        correctResponsesId.add(r3.getId()) ;


        question = QuestionEntity.builder()
                .id(new Long(1))
                .description("QuestionTest")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(6)
                .allOrNone(true)
                .imageId(new Long(1))
                .build() ;

        q1 = QuestionEntity.builder()
                .id(new Long(1))
                .description("QuestionTest 1")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(6)
                .allOrNone(true)
                .imageId(new Long(1))
                .build() ;
        q2 = QuestionEntity.builder()
                .id(new Long(2))
                .description("QuestionTest 2")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(8)
                .allOrNone(true)
                .imageId(new Long(1))
                .build() ;
        q3 = QuestionEntity.builder()
                .id(new Long(3))
                .description("QuestionTest 3")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(6)
                .allOrNone(true)
                .imageId(new Long(1))
                .build() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;


        try
        {
            deadLine = (Date) simpleDate.parse("2037-06-30") ;
        }

        catch (ParseException e)
        {
            e.printStackTrace() ;
        }

        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        LocalDate localDateTest = null ;
        student = UserEntity.builder()
                .id(new Long(1))
                .idClassmate(new Long(1))
                .pseudo("Testeur1")
                .firstname("Testeur")
                .lastname("Test")
                .email("testeur@testmail.com")
                .birthday(localDateTest)
                .photo(null)
                .address("1 rue du test à Testville")
                .password("testeur")
                .role(STUDENT)
                .token(null)
                .build() ;


        userQuizz = UserQuizzEntity.builder()
                .id(new Long(1))
                .userId(student.getId())
                .surveyId(survey.getId())
                .score(1)
                .build() ;

        userQuizzResponses = UserQuizzResponsesEntity.builder()
                .id(new Long(1))
                .userQuizzId(userQuizz.getId())
                .questionId(question.getId())
                .responses(correctResponses)
                .build() ;
    }


    /* AVEC L'OBJET USERQUIZZRESPONSES */
    @Test
    public void should_calculate_question_score_with_user_quizz_responses()
    {
        ArrayList<ResponseEntity> userResponses = new ArrayList<ResponseEntity>() ;
        userResponses.add(r2) ;
        userResponses.add(r3) ;

        userQuizzResponses.setResponses(userResponses) ;

        float result = QuizzSystem.calculateQuestionScore(question, userQuizzResponses) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotEquals(0.0, result, 0) ;
    }

    @Test
    public void should_give_all_question_points_with_user_quizz_responses()
    {
        ArrayList<ResponseEntity> userResponses = new ArrayList<ResponseEntity>() ;
        userResponses.add(r2) ;
        userResponses.add(r3) ;

        userQuizzResponses.setResponses(userResponses) ;


        Assert.assertEquals(6.0, QuizzSystem.calculateQuestionScore(question, userQuizzResponses), 0) ;
    }

    @Test
    public void should_give_some_question_points_with_user_quizz_responses()
    {
        question.setAllOrNone(false) ;

        ArrayList<ResponseEntity> userResponses = new ArrayList<ResponseEntity>() ;
        userResponses.add(r2) ;

        userQuizzResponses.setResponses(userResponses) ;


        Assert.assertEquals(3.0, QuizzSystem.calculateQuestionScore(question, userQuizzResponses), 0) ;
    }

    @Test
    public void should_not_give_question_points_with_user_quizz_responses()
    {
        ArrayList<ResponseEntity> userResponses = new ArrayList<ResponseEntity>() ;
        userResponses.add(r1) ;
        userResponses.add(r4) ;

        userQuizzResponses.setResponses(userResponses) ;


        Assert.assertEquals(0.0, QuizzSystem.calculateQuestionScore(question, userQuizzResponses), 0) ;
    }

    /* AVEC UNE ARRAYLIST DE LONG */
    @Test
    public void should_calculate_question_score_with_array_list_of_responses_id()
    {
        ArrayList<ResponseEntity> userResponses = new ArrayList<ResponseEntity>() ;

        userResponses.add(r2) ;
        userResponses.add(r3) ;

        float result = QuizzSystem.calculateQuestionScore(question, userResponses) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotEquals(0.0, result, 0) ;
    }

    @Test
    public void should_give_all_question_points_with_array_list_of_responses_id()
    {
        ArrayList<ResponseEntity> userResponses = new ArrayList<ResponseEntity>() ;

        userResponses.add(r2) ;
        userResponses.add(r3) ;


        Assert.assertEquals(6.0, QuizzSystem.calculateQuestionScore(question, userResponses), 0) ;
    }

    @Test
    public void should_give_some_question_points_with_array_list_of_responses_id()
    {
        question.setAllOrNone(false) ;

        ArrayList<ResponseEntity> userResponses = new ArrayList<ResponseEntity>() ;

        userResponses.add(r2) ;


        Assert.assertEquals(3.0, QuizzSystem.calculateQuestionScore(question, userResponses), 0) ;
    }

    @Test
    public void should_not_give_question_points_with_array_list_of_responses_id()
    {
        ArrayList<ResponseEntity> userResponses = new ArrayList<ResponseEntity>() ;

        userResponses.add(r1) ;
        userResponses.add(r4) ;


        Assert.assertEquals(0.0, QuizzSystem.calculateQuestionScore(question, userResponses), 0) ;
    }

    /* AVEC UN ARRAY DE LONG */
    @Test
    public void should_calculate_question_score_with_array_of_responses_id()
    {
        ResponseEntity[] userResponses = new ResponseEntity[2] ;

        userResponses[0] = r2 ;
        userResponses[1] = r3 ;

        float result = QuizzSystem.calculateQuestionScore(question, userResponses) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotEquals(0.0, result, 0) ;
    }

    @Test
    public void should_give_all_question_points_with_array_of_responses_id()
    {
        ResponseEntity[] userResponses = new ResponseEntity[2] ;

        userResponses[0] = r2 ;
        userResponses[1] = r3 ;


        Assert.assertEquals(6.0, QuizzSystem.calculateQuestionScore(question, userResponses), 0) ;
    }

    @Test
    public void should_give_some_question_points_with_array_of_responses_id()
    {
        question.setAllOrNone(false) ;

        ResponseEntity[] userResponses = new ResponseEntity[1] ;

        userResponses[0] = r2 ;


        Assert.assertEquals(3.0, QuizzSystem.calculateQuestionScore(question, userResponses), 0) ;
    }

    @Test
    public void should_not_give_question_points_with_array_of_responses_id()
    {
        ResponseEntity[] userResponses = new ResponseEntity[2] ;

        userResponses[0] = r1 ;
        userResponses[1] = r4 ;


        Assert.assertEquals(0.0, QuizzSystem.calculateQuestionScore(question, userResponses), 0) ;
    }

    /* AVEC L'OBJET USERQUIZZRESPONSES */
    @Test
    public void should_calculate_survey_score_with_user_quizz_responses()
    {
        UserQuizzResponsesEntity[] usersQuizzResponses = new UserQuizzResponsesEntity[3] ;

        ArrayList<ResponseEntity> userResponses = new ArrayList<ResponseEntity>() ;
        userResponses.add(r2) ;
        userResponses.add(r3) ;

        usersQuizzResponses[0] = UserQuizzResponsesEntity.builder()
                .id(new Long(1))
                .userQuizzId(new Long(1))
                .questionId(new Long(1))
                .responses(userResponses)
                .build() ;
        usersQuizzResponses[1] = UserQuizzResponsesEntity.builder()
                .id(new Long(2))
                .userQuizzId(new Long(1))
                .questionId(new Long(2))
                .responses(userResponses)
                .build() ;
        usersQuizzResponses[2] = UserQuizzResponsesEntity.builder()
                .id(new Long(3))
                .userQuizzId(new Long(1))
                .questionId(new Long(3))
                .responses(userResponses)
                .build() ;

        float result = QuizzSystem.calculateSurveyScore(survey, usersQuizzResponses) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotEquals(0.0, result, 0) ;
    }

    @Test
    public void should_give_all_survey_points_with_user_quizz_responses()
    {
        UserQuizzResponsesEntity[] usersQuizzResponses = new UserQuizzResponsesEntity[3] ;

        ArrayList<ResponseEntity> userResponses = new ArrayList<ResponseEntity>() ;
        userResponses.add(r2) ;
        userResponses.add(r3) ;

        usersQuizzResponses[0] = UserQuizzResponsesEntity.builder()
                .id(new Long(1))
                .userQuizzId(new Long(1))
                .questionId(new Long(1))
                .responses(userResponses)
                .build() ;
        usersQuizzResponses[1] = UserQuizzResponsesEntity.builder()
                .id(new Long(2))
                .userQuizzId(new Long(1))
                .questionId(new Long(2))
                .responses(userResponses)
                .build() ;
        usersQuizzResponses[2] = UserQuizzResponsesEntity.builder()
                .id(new Long(3))
                .userQuizzId(new Long(1))
                .questionId(new Long(3))
                .responses(userResponses)
                .build() ;


        Assert.assertEquals(20.0, QuizzSystem.calculateSurveyScore(survey, usersQuizzResponses), 0) ;
    }

    @Test
    public void should_give_some_survey_points_with_user_quizz_responses()
    {
        UserQuizzResponsesEntity[] usersQuizzResponses = new UserQuizzResponsesEntity[3] ;

        ArrayList<ResponseEntity> userResponses1 = new ArrayList<ResponseEntity>() ;
        userResponses1.add(r2) ;
        userResponses1.add(r3) ;

        ArrayList<ResponseEntity> userResponses2 = new ArrayList<ResponseEntity>() ;
        userResponses2.add(r1) ;
        userResponses2.add(r3) ;

        ArrayList<ResponseEntity> userResponses3 = new ArrayList<ResponseEntity>() ;
        userResponses3.add(r2) ;
        userResponses3.add(r4) ;

        usersQuizzResponses[0] = UserQuizzResponsesEntity.builder()
                .id(new Long(1))
                .userQuizzId(new Long(1))
                .questionId(new Long(1))
                .responses(userResponses1)
                .build() ;
        usersQuizzResponses[1] = UserQuizzResponsesEntity.builder()
                .id(new Long(2))
                .userQuizzId(new Long(1))
                .questionId(new Long(2))
                .responses(userResponses2)
                .build() ;
        usersQuizzResponses[2] = UserQuizzResponsesEntity.builder()
                .id(new Long(3))
                .userQuizzId(new Long(1))
                .questionId(new Long(3))
                .responses(userResponses3)
                .build() ;


        Assert.assertEquals(6.0, QuizzSystem.calculateSurveyScore(survey, usersQuizzResponses), 0) ;
    }

    @Test
    public void should_not_give_survey_points_with_user_quizz_responses()
    {
        UserQuizzResponsesEntity[] usersQuizzResponses = new UserQuizzResponsesEntity[3] ;

        ArrayList<ResponseEntity> userResponses = new ArrayList<ResponseEntity>() ;
        userResponses.add(r4) ;
        userResponses.add(r4) ;

        usersQuizzResponses[0] = UserQuizzResponsesEntity.builder()
                .id(new Long(1))
                .userQuizzId(new Long(1))
                .questionId(new Long(1))
                .responses(userResponses)
                .build() ;
        usersQuizzResponses[1] = UserQuizzResponsesEntity.builder()
                .id(new Long(2))
                .userQuizzId(new Long(1))
                .questionId(new Long(2))
                .responses(userResponses)
                .build() ;
        usersQuizzResponses[2] = UserQuizzResponsesEntity.builder()
                .id(new Long(3))
                .userQuizzId(new Long(1))
                .questionId(new Long(3))
                .responses(userResponses)
                .build() ;


        Assert.assertEquals(0.0, QuizzSystem.calculateSurveyScore(survey, usersQuizzResponses), 0) ;
    }

    /* AVEC UNE ARRAYLIST DE ARRAYLIST DE LONG */
    @Test
    public void should_calculate_survey_score_with_array_list_of_responses_id()
    {
        ArrayList<ResponseEntity> firstResponses = new ArrayList<ResponseEntity>() ;
        ArrayList<ResponseEntity> secondResponses = new ArrayList<ResponseEntity>() ;
        ArrayList<ResponseEntity> thirdResponses = new ArrayList<ResponseEntity>() ;

        firstResponses.add(r2) ;
        firstResponses.add(r3) ;
        secondResponses.add(r2) ;
        secondResponses.add(r3) ;
        thirdResponses.add(r2) ;
        thirdResponses.add(r3) ;

        ArrayList<ArrayList<ResponseEntity>> userResponses = new ArrayList<ArrayList<ResponseEntity>>() ;

        userResponses.add(firstResponses) ;
        userResponses.add(secondResponses) ;
        userResponses.add(thirdResponses) ;

        float result = QuizzSystem.calculateSurveyScore(survey, userResponses) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotEquals(0.0, result,0) ;
    }

    @Test
    public void should_give_all_survey_points_with_array_list_of_responses_id()
    {
        ArrayList<ResponseEntity> firstResponses = new ArrayList<ResponseEntity>() ;
        ArrayList<ResponseEntity> secondResponses = new ArrayList<ResponseEntity>() ;
        ArrayList<ResponseEntity> thirdResponses = new ArrayList<ResponseEntity>() ;

        firstResponses.add(r2) ;
        firstResponses.add(r3) ;
        secondResponses.add(r2) ;
        secondResponses.add(r3) ;
        thirdResponses.add(r2) ;
        thirdResponses.add(r3) ;

        ArrayList<ArrayList<ResponseEntity>> userResponses = new ArrayList<ArrayList<ResponseEntity>>() ;

        userResponses.add(firstResponses) ;
        userResponses.add(secondResponses) ;
        userResponses.add(thirdResponses) ;


        Assert.assertEquals(20.0, QuizzSystem.calculateSurveyScore(survey, userResponses), 0) ;
    }

    @Test
    public void should_give_some_survey_points_with_array_list_of_responses_id()
    {
        ArrayList<ResponseEntity> firstResponses = new ArrayList<ResponseEntity>() ;
        ArrayList<ResponseEntity> secondResponses = new ArrayList<ResponseEntity>() ;
        ArrayList<ResponseEntity> thirdResponses = new ArrayList<ResponseEntity>() ;

        firstResponses.add(r2) ;
        firstResponses.add(r3) ;
        secondResponses.add(r1) ;
        secondResponses.add(r3) ;
        thirdResponses.add(r2) ;
        thirdResponses.add(r4) ;

        ArrayList<ArrayList<ResponseEntity>> userResponses = new ArrayList<ArrayList<ResponseEntity>>() ;

        userResponses.add(firstResponses) ;
        userResponses.add(secondResponses) ;
        userResponses.add(thirdResponses) ;


        Assert.assertEquals(6.0, QuizzSystem.calculateSurveyScore(survey, userResponses), 0) ;
    }

    @Test
    public void should_not_give_survey_points_with_array_list_of_responses_id()
    {
        ArrayList<ResponseEntity> firstResponses = new ArrayList<ResponseEntity>() ;
        ArrayList<ResponseEntity> secondResponses = new ArrayList<ResponseEntity>() ;
        ArrayList<ResponseEntity> thirdResponses = new ArrayList<ResponseEntity>() ;

        firstResponses.add(r4) ;
        firstResponses.add(r4) ;
        secondResponses.add(r4) ;
        secondResponses.add(r4) ;
        thirdResponses.add(r4) ;
        thirdResponses.add(r4) ;

        ArrayList<ArrayList<ResponseEntity>> userResponses = new ArrayList<ArrayList<ResponseEntity>>() ;

        userResponses.add(firstResponses) ;
        userResponses.add(secondResponses) ;
        userResponses.add(thirdResponses) ;


        Assert.assertEquals(0.0, QuizzSystem.calculateSurveyScore(survey, userResponses), 0) ;
    }

    /* AVEC UN ARRAY DE ARRAY DE LONG */
    @Test
    public void should_calculate_survey_score_with_array_of_responses_id()
    {
        ResponseEntity[][] userResponses = new ResponseEntity[3][2] ;

        userResponses[0][0] = r2 ;
        userResponses[0][1] = r3 ;
        userResponses[1][0] = r2 ;
        userResponses[1][1] = r3 ;
        userResponses[2][0] = r2 ;
        userResponses[2][1] = r3 ;

        float result = QuizzSystem.calculateSurveyScore(survey, userResponses) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotEquals(0.0, result,0) ;
    }

    @Test
    public void should_give_all_survey_points_with_array_of_responses_id()
    {
        ResponseEntity[][] userResponses = new ResponseEntity[3][2] ;

        userResponses[0][0] = r2 ;
        userResponses[0][1] = r3 ;
        userResponses[1][0] = r2 ;
        userResponses[1][1] = r3 ;
        userResponses[2][0] = r2 ;
        userResponses[2][1] = r3 ;


        Assert.assertEquals(20.0, QuizzSystem.calculateSurveyScore(survey, userResponses), 0) ;
    }

    @Test
    public void should_give_some_survey_points_with_array_of_responses_id()
    {
        ResponseEntity[][] userResponses = new ResponseEntity[3][2] ;

        userResponses[0][0] = r2 ;
        userResponses[0][1] = r3 ;
        userResponses[1][0] = r1 ;
        userResponses[1][1] = r3 ;
        userResponses[2][0] = r2 ;
        userResponses[2][1] = r4 ;


        Assert.assertEquals(6.0, QuizzSystem.calculateSurveyScore(survey, userResponses), 0) ;
    }

    @Test
    public void should_not_give_survey_points_with_array_of_responses_id()
    {
        ResponseEntity[][] userResponses = new ResponseEntity[3][2] ;

        userResponses[0][0] = r4 ;
        userResponses[0][1] = r4 ;
        userResponses[1][0] = r4 ;
        userResponses[1][1] = r4 ;
        userResponses[2][0] = r4 ;
        userResponses[2][1] = r4 ;


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
        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(0)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        Assert.assertTrue(QuizzSystem.surveyIsInfinite(survey)) ;
    }

    @Test
    public void should_survey_is_not_infinite()
    {
        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        Assert.assertFalse(QuizzSystem.surveyIsInfinite(survey)) ;
    }

    @Test
    public void should_survey_is_open()
    {
        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        Assert.assertTrue(QuizzSystem.surveyIsOpen(survey)) ;
    }

    @Test
    public void should_survey_is_open_without_deadline()
    {
        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(null)
                .imageId(new Long(1))
                .build() ;


        Assert.assertTrue(QuizzSystem.surveyIsOpen(survey)) ;
    }

    @Test
    public void should_survey_is_not_open()
    {
        try
        {
            deadLine = (Date) simpleDate.parse("2017-04-29") ;
        }

        catch (ParseException e)
        {
            e.printStackTrace() ;
        }

        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        Assert.assertFalse(QuizzSystem.surveyIsOpen(survey)) ;
    }

    /*
    @Test
    public void should_user_quizz_can_answer_survey()
    {
        userQuizz = UserQuizzEntity.builder()
                .id(new Long(1))
                .userId(student.getId())
                .surveyId(survey.getId())
                .score(new Float(1))
                .build() ;

        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(2)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        Assert.assertTrue(QuizzSystem.userQuizzCanAnswerSurvey(userQuizz, survey)) ;
    }
    */

    /*
    @Test
    public void should_user_quizz_can_answer_infinite_survey()
    {
        userQuizz = UserQuizzEntity.builder()
                .id(new Long(1))
                .userId(student.getId())
                .surveyId(survey.getId())
                .score(new Float(1))
                .build() ;

        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(0)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        Assert.assertTrue(QuizzSystem.userQuizzCanAnswerSurvey(userQuizz, survey)) ;
    }
    */

    /*
    @Test
    public void should_user_quizz_can_not_answer_survey()
    {
        userQuizz = UserQuizzEntity.builder()
                .id(new Long(1))
                .userId(student.getId())
                .surveyId(survey.getId())
                .score(new Float(1))
                .build() ;

        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        Assert.assertFalse(QuizzSystem.userQuizzCanAnswerSurvey(userQuizz, survey)) ;
    }
    */

}
