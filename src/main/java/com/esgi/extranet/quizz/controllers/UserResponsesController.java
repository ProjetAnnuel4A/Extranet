package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.entities.UserResponsesEntity ;
import com.esgi.extranet.quizz.services.interfaces.UserResponsesService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.web.bind.annotation.* ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/userResponses")
public class UserResponsesController
{

    private final UserResponsesService userResponsesService ;


    @Autowired
    public UserResponsesController(UserResponsesService userResponsesService)
    {
        this.userResponsesService = userResponsesService ;
    }


    @GetMapping("")
    public List<UserResponsesEntity> getAll()
    {
        return userResponsesService.getAll() ;
    }

    @GetMapping("/getUserResponses")
    public UserResponsesEntity getUserResponses(@RequestParam("userResponsesId") Long userResponsesId)
    {
        return userResponsesService.getUserResponses(userResponsesId) ;
    }

    @PostMapping("/addUserResponses")
    public UserResponsesEntity addUserResponses(@RequestParam(name = "userId") Long userId,
                                                @RequestParam(name = "surveyId") Long surveyId,
                                                @RequestParam(name = "questionId") Long questionId)
    {

        return userResponsesService.addUserResponses(userId, surveyId, questionId) ;
    }

    @PostMapping("/updateUserResponses")
    public UserResponsesEntity updateUserResponses(@RequestParam(name = "userResponsesId") Long userResponsesId,
                                                   @RequestParam(name = "userId") Long userId,
                                                   @RequestParam(name = "surveyId") Long surveyId,
                                                   @RequestParam(name = "questionId") Long questionId)
    {

        return userResponsesService.updateUserResponses(userResponsesId, userId, surveyId, questionId) ;
    }

    @RequestMapping(value = "/removeUserResponses", method = RequestMethod.POST)
    public boolean removeUserResponses(@RequestParam(name = "userResponsesId") Long userResponsesId)
    {
        return userResponsesService.removeUserResponses(userResponsesId) ;
    }


    @GetMapping("/getResponsesFromAUserResponses")
    public List<Long> getResponsesFromAUserResponses(@RequestParam("userResponsesId") Long userResponsesId)
    {
        return userResponsesService.getResponsesFromAUserResponses(userResponsesId) ;
    }

    @PostMapping("/addResponseForAUserResponses")
    public boolean addResponseForAUserResponses(@RequestParam("userResponsesId") Long userResponsesId,
                                                @RequestParam("responseId") Long responseId)
    {
        return userResponsesService.addResponseForAUserResponses(userResponsesId, responseId) ;
    }

    @PostMapping("/removeResponseFromAUserResponses")
    public boolean removeResponseFromAUserResponses(@RequestParam("userResponsesId") Long userResponsesId,
                                                    @RequestParam("responseId") Long responseId)
    {
        return userResponsesService.removeResponseFromAUserResponses(userResponsesId, responseId) ;
    }

}
