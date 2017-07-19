package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.SurveyEntity ;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.jupiter.api.BeforeAll ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.util.Optional ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 01/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SurveyRepositoryTests
{

    @Autowired
    public static SurveyRepository surveyRepository ;

    private static SurveyEntity surveyEntity ;


    @BeforeAll
    public static void initialize_datas()
    {
        surveyEntity = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(null)
                .mark(1)
                .chances(1)
                .deadLine(null)
                .imageId(new Long(1))
                .build() ;

        surveyRepository.save(surveyEntity) ;
    }


    @Test
    public void should_find_survey_by_id()
    {
        SurveyEntity result = surveyRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(result, surveyEntity) ;
    }

    @Test
    public void should_find_survey_by_name()
    {
        Optional<SurveyEntity> result = surveyRepository.findByName("SurveyTest") ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(result, surveyEntity) ;
    }

}
