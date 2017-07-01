package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import org.junit.Assert ;
import org.junit.Before ;
import org.junit.Test ;
import org.springframework.beans.factory.annotation.Autowired ;

/**
 * Created by Samuel Bijou on 01/07/2017.
 */
public class QuestionRepositoryTests
{

    @Autowired
    private QuestionRepository questionRepository ;

    private QuestionEntity questionEntity ;


    @Before
    public void initialize_datas()
    {
        questionEntity = new QuestionEntity(new Long(1), "QuestionTest", null, null, 1, true, "") ;

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
