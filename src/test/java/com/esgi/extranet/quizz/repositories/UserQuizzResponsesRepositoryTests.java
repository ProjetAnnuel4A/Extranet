package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;
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
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserQuizzResponsesRepositoryTests
{

    @Autowired
    public static UserQuizzResponsesRepository userQuizzResponsesRepository ;

    private static UserQuizzResponsesEntity userQuizzResponsesEntity ;


    @BeforeAll
    public static void initialize_datas()
    {
        userQuizzResponsesEntity = UserQuizzResponsesEntity.builder()
                .id(new Long(1))
                .userQuizzId(new Long(1))
                .questionId(new Long(1))
                .responses(new ArrayList<Long>())
                .build() ;

        userQuizzResponsesRepository.save(userQuizzResponsesEntity) ;
    }


    @Test
    public void should_find_user_quizz_responses_by_id()
    {
        UserQuizzResponsesEntity result = userQuizzResponsesRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(result, userQuizzResponsesEntity) ;
    }

    @Test
    public void should_find_users_quizz_responses_by_user_quizz_id()
    {
        List<UserQuizzResponsesEntity> result = userQuizzResponsesRepository.findByUserQuizzId(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(result.get(0), userQuizzResponsesEntity) ;
    }

    @Test
    public void should_find_user_quizz_responses_by_user_quizz_id_and_question_id()
    {
        UserQuizzResponsesEntity result = userQuizzResponsesRepository.findByUserQuizzIdAndQuestionId(new Long(1), new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(result, userQuizzResponsesEntity) ;
    }

}
