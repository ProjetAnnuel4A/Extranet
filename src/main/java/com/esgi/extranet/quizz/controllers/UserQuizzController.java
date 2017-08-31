package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;
import com.esgi.extranet.quizz.services.interfaces.UserQuizzService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.web.bind.annotation.* ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/usersQuizz")
public class UserQuizzController
{

    private final UserQuizzService userQuizzService ;


    @Autowired
    public UserQuizzController(UserQuizzService userQuizzService)
    {
        this.userQuizzService = userQuizzService ;
    }


    @GetMapping("")
    public List<UserQuizzEntity> getAll()
    {
        return userQuizzService.getAll() ;
    }

    @GetMapping("/getAllUsersQuizzBySurveyId")
    public List<UserQuizzEntity> getAllUsersQuizzBySurveyId(@RequestParam("surveyId") Long surveyId)
    {
        return userQuizzService.getAllUsersQuizzBySurveyId(surveyId) ;
    }

    @GetMapping("/getAllUsersQuizzByUserId")
    public List<UserQuizzEntity> getAllUsersQuizzByUserId(@RequestParam("userId") Long userId)
    {
        return userQuizzService.getAllUsersQuizzByUserId(userId) ;
    }

    @GetMapping("/getUserQuizz")
    public UserQuizzEntity getUserQuizz(@RequestParam("userQuizzId") Long userQuizzId)
    {
        return userQuizzService.getUserQuizz(userQuizzId) ;
    }

    @GetMapping("/getUsersQuizzByUserIdAndSurveyId")
    public UserQuizzEntity getUsersQuizzByUserIdAndSurveyId(@RequestParam(name = "userId") Long userId,
                                                            @RequestParam(name = "surveyId") Long surveyId)
    {
        return userQuizzService.getUserQuizzByUserIdAndSurveyId(userId, surveyId) ;
    }

    @PostMapping("/addUserQuizz")
    public UserQuizzEntity addUserQuizz(@RequestParam(name = "userId") Long userId,
                                        @RequestParam(name = "surveyId") Long surveyId,
                                        @RequestParam(name = "count") int count)
    {

        return userQuizzService.addUserQuizz(userId, surveyId, count) ;
    }

    @PostMapping("/updateUserQuizz")
    public UserQuizzEntity updateUserQuizz(@RequestParam(name = "userQuizzId") Long userQuizzId,
                                           @RequestParam(name = "userId") Long userId,
                                           @RequestParam(name = "surveyId") Long surveyId,
                                           @RequestParam(name = "count") int count)
    {

        return userQuizzService.updateUserQuizz(userQuizzId, userId, surveyId, count) ;
    }

    @RequestMapping(value = "/removeUserQuizz", method = RequestMethod.POST)
    public boolean removeUserQuizz(@RequestParam(name = "userQuizzId") Long userQuizzId)
    {
        return userQuizzService.removeUserQuizz(userQuizzId) ;
    }


    @GetMapping("/getAllUserQuizzResponsesFromAnUserQuizz")
    public List<UserQuizzResponsesEntity> getAllUserQuizzResponsesFromAnUserQuizz(@RequestParam("userQuizzId") Long userQuizzId)
    {
        return userQuizzService.getAllUsersQuizzResponsesFromAnUserQuizz(userQuizzId) ;
    }

    @PostMapping("/removeAllUserQuizzResponsesFromAnUserQuizz")
    public boolean removeAllUserQuizzResponsesFromAnUserQuizz(@RequestParam("userQuizzId") Long userQuizzId)
    {
        return userQuizzService.removeAllUsersQuizzResponsesFromAnUserQuizz(userQuizzId) ;
    }

    @GetMapping("/getUserQuizzResponsesFromAnUserQuizz")
    public UserQuizzResponsesEntity getUserQuizzResponsesFromAnUserQuizz(@RequestParam("userQuizzId") Long userQuizzId,
                                                                         @RequestParam("questionId") Long questionId)
    {
        return userQuizzService.getUserQuizzResponsesFromAnUserQuizz(userQuizzId, questionId) ;
    }

    @GetMapping("/getResponsesFromAnUserQuizz")
    public List<Long> getResponsesFromAnUserQuizz(@RequestParam("userQuizzId") Long userQuizzId,
                                                  @RequestParam("questionId") Long questionId)
    {
        return userQuizzService.getResponsesFromAnUserQuizz(userQuizzId, questionId) ;
    }

    @PostMapping("/addResponseForAnUserQuizz")
    public boolean addResponseForAnUserQuizz(@RequestParam("userQuizzId") Long userQuizzId,
                                             @RequestParam("questionId") Long questionId,
                                             @RequestParam("responseId") Long responseId)
    {
        return userQuizzService.addResponseForAnUserQuizz(userQuizzId, questionId, responseId) ;
    }

    @PostMapping("/removeResponseFromAnUserQuizz")
    public boolean removeResponseFromAnUserQuizz(@RequestParam("userQuizzId") Long userQuizzId,
                                                 @RequestParam("questionId") Long questionId,
                                                 @RequestParam("responseId") Long responseId)
    {
        return userQuizzService.removeResponseFromAnUserQuizz(userQuizzId, questionId, responseId) ;
    }

}
