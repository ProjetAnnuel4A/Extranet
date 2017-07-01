package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.services.interfaces.UserQuizzService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.web.bind.annotation.* ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/userResponses")
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

    @GetMapping("/getUserQuizz")
    public UserQuizzEntity getUserQuizz(@RequestParam("userQuizzId") Long userQuizzId)
    {
        return userQuizzService.getUserQuizz(userQuizzId) ;
    }

    @PostMapping("/addUserQuizz")
    public UserQuizzEntity addUserQuizz(@RequestParam(name = "userId") Long userId,
                                        @RequestParam(name = "surveyId") Long surveyId,
                                        @RequestParam(name = "questionId") Long questionId)
    {

        return userQuizzService.addUserQuizz(userId, surveyId, questionId) ;
    }

    @PostMapping("/updateUserQuizz")
    public UserQuizzEntity updateUserQuizz(@RequestParam(name = "userQuizzId") Long userQuizzId,
                                           @RequestParam(name = "userId") Long userId,
                                           @RequestParam(name = "surveyId") Long surveyId,
                                           @RequestParam(name = "questionId") Long questionId)
    {

        return userQuizzService.updateUserQuizz(userQuizzId, userId, surveyId, questionId) ;
    }

    @RequestMapping(value = "/removeUserQuizz", method = RequestMethod.POST)
    public boolean removeUserQuizz(@RequestParam(name = "userQuizzId") Long userQuizzId)
    {
        return userQuizzService.removeUserQuizz(userQuizzId) ;
    }


    @GetMapping("/getResponsesFromAnUserQuizz")
    public List<Long> getResponsesFromAnUserQuizz(@RequestParam("userQuizzId") Long userQuizzId)
    {
        return userQuizzService.getResponsesFromAnUserQuizz(userQuizzId) ;
    }

    @PostMapping("/addResponseForAnUserQuizz")
    public boolean addResponseForAnUserQuizz(@RequestParam("userQuizzId") Long userQuizzId,
                                             @RequestParam("responseId") Long responseId)
    {
        return userQuizzService.addResponseForAnUserQuizz(userQuizzId, responseId) ;
    }

    @PostMapping("/removeResponseFromAnUserQuizz")
    public boolean removeResponseFromAnUserQuizz(@RequestParam("userQuizzId") Long userQuizzId,
                                                 @RequestParam("responseId") Long responseId)
    {
        return userQuizzService.removeResponseFromAnUserQuizz(userQuizzId, responseId) ;
    }

}
