package com.esgi.extranet.QCM.services.implementations ;

import com.esgi.extranet.QCM.entities.QuestionEntity ;
import com.esgi.extranet.QCM.entities.SurveyEntity ;
import com.esgi.extranet.QCM.repositories.QuestionRepository ;
import com.esgi.extranet.QCM.repositories.SurveyRepository ;
import com.esgi.extranet.QCM.services.interfaces.SurveyService ;
import org.springframework.beans.factory.annotation.Autowired ;

import javax.transaction.Transactional ;
import java.sql.Date ;
import java.util.ArrayList ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
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
    public SurveyEntity addSurvey(Long id, String name, ArrayList<QuestionEntity> questions, float mark, int chances, Date deadLine, String imagePath)
    {
        SurveyEntity surveyEntity = SurveyEntity.builder()
                .name(name)
                .questions(questions)
                .mark(mark)
                .chances(chances)
                .deadLine(deadLine)
                .imagePath(imagePath)
                .build() ;

        surveyRepository.save(surveyEntity) ;

        return surveyEntity ;
    }

    @Override
    public SurveyEntity updateSurvey(Long id, String name, ArrayList<QuestionEntity> questions, float mark, int chances, Date deadLine, String imagePath)
    {
        SurveyEntity surveyEntity = surveyRepository.findById(id) ;

        surveyEntity.setName(name) ;
        surveyEntity.setQuestions(questions) ;
        surveyEntity.setMark(mark) ;
        surveyEntity.setChances(chances) ;
        surveyEntity.setDeadLine(deadLine) ;
        surveyEntity.setImagePath(imagePath) ;

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
    public ArrayList<QuestionEntity> getQuestionsFromASurvey(Long surveyId)
    {
        SurveyEntity surveyEntity = surveyRepository.findById(surveyId) ;

        return surveyEntity.getQuestions() ;
    }

    @Override
    public boolean addQuestionForASurvey(Long surveyId, Long questionId)
    {
        SurveyEntity surveyEntity = surveyRepository.findById(surveyId) ;
        QuestionEntity questionEntity = questionRepository.findById(questionId) ;

        surveyEntity.getQuestions().add(questionEntity) ;

        surveyRepository.save(surveyEntity) ;

        return false ;
    }

    @Override
    public boolean removeQuestionFromASurvey(Long surveyId, Long questionId)
    {
        SurveyEntity surveyEntity = surveyRepository.findById(surveyId) ;
        List<QuestionEntity> questionEntities = surveyEntity.getQuestions() ;

        for(int i = 0 ; i < questionEntities.size() ; i++)
        {
            if(questionEntities.get(i).getId().equals(questionId))
            {
                questionEntities.remove(i) ;
                surveyRepository.save(surveyEntity) ;

                return true ;
            }
        }

        return false ;
    }

}
