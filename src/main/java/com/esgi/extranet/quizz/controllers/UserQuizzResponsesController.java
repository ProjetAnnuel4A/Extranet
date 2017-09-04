package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.entities.ResponseEntity;
import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;
import com.esgi.extranet.quizz.services.interfaces.UserQuizzResponsesService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.web.bind.annotation.* ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 16/07/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/usersQuizzResponses")
public class UserQuizzResponsesController
{

    private final UserQuizzResponsesService userQuizzResponsesService ;


    @Autowired
    public UserQuizzResponsesController(UserQuizzResponsesService userQuizzResponsesService)
    {
        this.userQuizzResponsesService = userQuizzResponsesService ;
    }


    @GetMapping("")
    public List<UserQuizzResponsesEntity> getAll()
    {
        return userQuizzResponsesService.getAll() ;
    }

    @GetMapping("/getUserQuizzResponses")
    public UserQuizzResponsesEntity getUserQuizzResponses(@RequestParam("userQuizzResponsesId") Long userQuizzResponsesId)
    {
        return userQuizzResponsesService.getUserQuizzResponses(userQuizzResponsesId) ;
    }

    @PostMapping("/addUserQuizzResponses")
    public UserQuizzResponsesEntity addUserQuizzResponses(@RequestParam(name = "userQuizzId") Long userQuizzId,
                                                          @RequestParam(name = "questionId") Long questionId)
    {

        return userQuizzResponsesService.addUserQuizzResponses(userQuizzId, questionId) ;
    }

    @PostMapping("/updateUserQuizzResponses")
    public UserQuizzResponsesEntity updateUserQuizzResponses(@RequestParam(name = "userQuizzResponsesId") Long userQuizzResponsesId,
                                                             @RequestParam(name = "userQuizzId") Long userQuizzId,
                                                             @RequestParam(name = "questionId") Long questionId)
    {

        return userQuizzResponsesService.updateUserQuizzResponses(userQuizzResponsesId, userQuizzId, questionId) ;
    }

    @RequestMapping(value = "/removeUserQuizzResponses", method = RequestMethod.POST)
    public boolean removeUserQuizzResponses(@RequestParam(name = "userQuizzResponsesId") Long userQuizzResponsesId)
    {
        return userQuizzResponsesService.removeUserQuizzResponses(userQuizzResponsesId) ;
    }


    @GetMapping("/getResponsesFromAnUserQuizzResponses")
    public List<ResponseEntity> getResponsesFromAnUserQuizzResponses(@RequestParam("userQuizzResponsesId") Long userQuizzResponsesId)
    {
        return userQuizzResponsesService.getResponsesFromAnUserQuizzResponses(userQuizzResponsesId) ;
    }

    @PostMapping("/addResponseForAnUserQuizzResponses")
    public boolean addResponseForAnUserQuizzResponses(@RequestParam("userQuizzResponsesId") Long userQuizzResponsesId,
                                                      @RequestParam("responseId") Long responseId)
    {
        return userQuizzResponsesService.addResponseForAnUserQuizzResponses(userQuizzResponsesId, responseId) ;
    }

    @PostMapping("/removeResponseFromAnUserQuizzResponses")
    public boolean removeResponseFromAnUserQuizzResponses(@RequestParam("userQuizzResponsesId") Long userQuizzResponsesId,
                                                          @RequestParam("responseId") Long responseId)
    {
        return userQuizzResponsesService.removeResponseFromAnUserQuizzResponses(userQuizzResponsesId, responseId) ;
    }


    @GetMapping("/getUserQuizzFromUserQuizzResponses")
    public UserQuizzEntity getUserQuizzFromUserQuizzResponses(@RequestParam("userQuizzResponsesId") Long userQuizzResponsesId)
    {
        return userQuizzResponsesService.getUserQuizzFromUserQuizzResponses(userQuizzResponsesId) ;
    }

}
