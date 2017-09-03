package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.jupiter.api.BeforeAll ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.util.List ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 01/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserQuizzRepositoryTests
{

    @Autowired
    public static UserQuizzRepository userQuizzRepository ;

    private static UserQuizzEntity userQuizzEntity ;


    @BeforeAll
    public static void initialize_datas()
    {
        userQuizzEntity = UserQuizzEntity.builder()
                .id(new Long(1))
                .userId(new Long(1))
                .surveyId(new Long(1))
                .score(new Float(1))
                .build() ;

        userQuizzRepository.save(userQuizzEntity) ;
    }


    @Test
    public void should_find_user_quizz_by_id()
    {
        UserQuizzEntity result = userQuizzRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(result, userQuizzEntity) ;
    }

    @Test
    public void should_find_all_users_quizz_by_user_id()
    {
        List<UserQuizzEntity> result = userQuizzRepository.findAllByUserId(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(result.get(0), userQuizzEntity) ;
    }

    @Test
    public void should_find_user_quizz_by_user_id_and_survey_id()
    {
        List<UserQuizzEntity> result = userQuizzRepository.findAllByUserIdAndSurveyId(new Long(1), new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(result, userQuizzEntity) ;
    }

}
