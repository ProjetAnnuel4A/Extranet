package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.jupiter.api.BeforeAll ;
import org.springframework.beans.factory.annotation.Autowired ;

/**
 * Created by Samuel Bijou on 01/07/2017.
 */
public class QuestionRepositoryTests
{

    @Autowired
    public static QuestionRepository questionRepository ;

    private static QuestionEntity questionEntity ;


    @BeforeAll
    public static void initialize_datas()
    {
        questionEntity = QuestionEntity.builder()
                .id(new Long(1))
                .description("QuestionTest")
                .responses(null)
                .correctResponses(null)
                .points(1)
                .allOrNot(true)
                .imageId(new Long(1))
                .build() ;

        questionRepository.save(questionEntity) ;
    }


    @Test
    public void should_find_question_by_id()
    {
        QuestionEntity result = questionRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(result, questionEntity) ;
    }

}
