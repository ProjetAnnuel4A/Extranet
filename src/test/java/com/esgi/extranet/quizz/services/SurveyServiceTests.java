package com.esgi.extranet.quizz.services ;

import com.esgi.extranet.quizz.repositories.SurveyRepository ;
import com.esgi.extranet.quizz.services.interfaces.SurveyService ;
import org.junit.Test ;
import org.springframework.beans.factory.annotation.Autowired ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
public class SurveyServiceTests
{

    @Autowired
    SurveyService surveyService ;

    @Autowired
    SurveyRepository surveyRepository ;


    @Test
    public void should_add_survey() throws Exception
    {

    }

    @Test
    public void should_update_survey() throws Exception
    {

    }

    @Test
    public void should_remove_survey() throws Exception
    {

    }

    @Test
    public void should_get_survey() throws Exception
    {

    }


    @Test
    public void should_get_questions_from_a_survey() throws Exception
    {

    }

    @Test
    public void should_add_question_for_a_survey() throws Exception
    {

    }

    @Test
    public void should_remove_question_from_a_survey() throws Exception
    {

    }

}
