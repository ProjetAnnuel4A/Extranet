package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.jupiter.api.BeforeAll ;
import org.springframework.beans.factory.annotation.Autowired ;

import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 01/07/2017.
 */
public class UserQuizzRepositoryTests
{

    @Autowired
    public static UserQuizzRepository userQuizzRepository ;

    private static UserQuizzEntity userQuizzEntity ;


    @BeforeAll
    public static void initialize_datas()
    {
        ArrayList<Long> userResponses = new ArrayList<Long>() ;

        userQuizzEntity = UserQuizzEntity.builder()
                .id(new Long(1))
                .userId(new Long(1))
                .surveyId(new Long(1))
                .questionId(new Long(1))
                .responses(userResponses)
                .count(1)
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

}
