package com.esgi.extranet.quizz.services.implementations ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;
import com.esgi.extranet.quizz.repositories.QuestionRepository ;
import com.esgi.extranet.quizz.repositories.SurveyRepository ;
import com.esgi.extranet.quizz.services.interfaces.SurveyService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Service ;

import javax.transaction.Transactional ;
import java.sql.Date ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
@Service
public class SurveyServiceImpl implements SurveyService
{

    private SurveyRepository surveyRepository ;
    private QuestionRepository questionRepository ;


    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository, QuestionRepository questionRepository)
    {
        this.surveyRepository = surveyRepository ;
        this.questionRepository = questionRepository ;
    }


    @Override
    public List<SurveyEntity> getAll()
    {
        return surveyRepository.findAll() ;
    }

    @Override
    @Transactional
    public SurveyEntity addSurvey(String name, float mark, int chances, Date deadLine, Long imageId)
    {
        SurveyEntity surveyEntity = SurveyEntity.builder()
                .name(name)
                .mark(mark)
                .chances(chances)
                .deadLine(deadLine)
                .imageId(imageId)
                .build() ;

        surveyRepository.save(surveyEntity) ;

        return surveyEntity ;
    }

    @Override
    @Transactional
    public SurveyEntity updateSurvey(Long surveyId, String name, float mark, int chances, Date deadLine, Long imageId)
    {
        SurveyEntity surveyEntity = surveyRepository.findById(surveyId) ;

        surveyEntity.setName(name) ;
        surveyEntity.setMark(mark) ;
        surveyEntity.setChances(chances) ;
        surveyEntity.setDeadLine(deadLine) ;
        surveyEntity.setImageId(imageId) ;

        surveyRepository.save(surveyEntity) ;

        return surveyEntity ;
    }

    @Override
    @Transactional
    public boolean removeSurvey(Long surveyId)
    {
        surveyRepository.delete(surveyId) ;

        return (surveyRepository.findById(surveyId) == null) ;
    }

    @Override
    public SurveyEntity getSurvey(Long surveyId)
    {
        return surveyRepository.findById(surveyId) ;
    }


    @Override
    public List<QuestionEntity> getQuestionsFromASurvey(Long surveyId)
    {
        SurveyEntity surveyEntity = surveyRepository.findById(surveyId) ;

        return surveyEntity.getQuestions() ;
    }

    @Override
    @Transactional
    public boolean addQuestionForASurvey(Long surveyId, Long questionId)
    {
        SurveyEntity surveyEntity = surveyRepository.findById(surveyId) ;
        QuestionEntity questionEntity = questionRepository.findById(questionId) ;

        surveyEntity.getQuestions().add(questionEntity) ;

        surveyRepository.save(surveyEntity) ;

        return true ;
    }

    @Override
    @Transactional
    public boolean removeQuestionFromASurvey(Long surveyId, Long questionId)
    {
        SurveyEntity surveyEntity = surveyRepository.findById(surveyId) ;
        List<QuestionEntity> questionEntities = surveyEntity.getQuestions() ;

        for(int i = 0 ; i < questionEntities.size() ; i++)
        {
            if(questionEntities.get(i).getId().equals(questionId))
            {
                questionEntities.remove(i) ;
                surveyEntity.setQuestions(questionEntities) ;
                surveyRepository.save(surveyEntity) ;

                return true ;
            }
        }

        return false ;
    }

}
