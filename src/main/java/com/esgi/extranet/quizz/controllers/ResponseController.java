package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.services.interfaces.ResponseService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.web.bind.annotation.* ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/reponses")
public class ResponseController
{

    private final ResponseService responseService ;


    @Autowired
    public ResponseController(ResponseService responseService)
    {
        this.responseService = responseService ;
    }


    @GetMapping("")
    public List<ResponseEntity> getAll()
    {
        return responseService.getAll() ;
    }

    @GetMapping("/getResponse")
    public ResponseEntity getResponse(@RequestParam("id")Long id)
    {
        return responseService.getResponse(id) ;
    }

    @PostMapping("/addResponse")
    public ResponseEntity addResponse(@RequestParam(name = "id") Long id,
                                      @RequestParam(name = "description") String description,
                                      @RequestParam(name = "imagePath") String imagePath)
    {

        return responseService.addResponse(id, description, imagePath) ;
    }

    @PostMapping("/updateResponse")
    public ResponseEntity updateResponse(@RequestParam(name = "id") Long id,
                                         @RequestParam(name = "description") String description,
                                         @RequestParam(name = "imagePath") String imagePath)
    {

        return responseService.updateResponse(id, description, imagePath) ;
    }

    @RequestMapping(value = "/removeResponse", method = RequestMethod.POST)
    public boolean removeResponse(@RequestParam(name = "id") Long id)
    {
        return responseService.removeResponse(id) ;
    }

}
