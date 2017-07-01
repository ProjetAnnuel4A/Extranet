package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.SurveyEntity ;
import org.junit.Assert ;
import org.junit.Before ;
import org.junit.Test ;
import org.springframework.beans.factory.annotation.Autowired ;

import java.util.Optional ;

/**
 * Created by Samuel Bijou on 01/07/2017.
 */
public class SurveyRepositoryTests
{

    @Autowired
    private SurveyRepository surveyRepository ;

    private SurveyEntity surveyEntity ;


    @Before
    public void initialize_datas()
    {
        surveyEntity = new SurveyEntity(new Long(1), "SurveyTest", null, 1, 1, null, "") ;

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
