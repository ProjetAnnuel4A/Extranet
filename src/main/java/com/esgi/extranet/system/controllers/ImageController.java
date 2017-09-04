package com.esgi.extranet.system.controllers ;

import com.esgi.extranet.system.entities.ImageEntity ;
import com.esgi.extranet.system.services.interfaces.ImageService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.web.bind.annotation.* ;

import java.io.IOException ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/images")
public class ImageController
{

    private final ImageService imageService ;


    @Autowired
    public ImageController(ImageService imageService)
    {
        this.imageService = imageService ;
    }


    @GetMapping("")
    public List<ImageEntity> getAll()
    {
        return imageService.getAll() ;
    }

    @GetMapping("/getImage")
    public ImageEntity getImage(@RequestParam("imageId" ) Long imageId)
    {
        return imageService.getImage(imageId) ;
    }

    @PostMapping("/addImage")
    public ImageEntity addImage(@RequestParam(name = "name") String name)
    {
        return imageService.addImage(name) ;
    }

    @PostMapping("/updateImage")
    public ImageEntity updateImage(@RequestParam(name = "imageId") Long imageId,
                                   @RequestParam(name = "name") String name)
    {

        return imageService.updateImage(imageId, name) ;
    }

    @RequestMapping(value = "/removeImage", method = RequestMethod.POST)
    public boolean removeImage(@RequestParam(name = "imageId") Long imageId)
    {
        return imageService.removeImage(imageId) ;
    }


    @GetMapping("/getDatasFromAnImage")
    public byte[] getDatasFromAnImage(@RequestParam("imageId") Long imageId)
    {
        return imageService.getDatasFromAnImage(imageId) ;
    }

    @PostMapping("/addDatasForAnImage")
    public boolean addDatasForAnImage(@RequestParam("imageId") Long imageId) throws IOException
    {
        return imageService.addDatasForAnImage(imageId) ;
    }

    @PostMapping("/removeDatasFromAnImage")
    public boolean removeDatasFromAnImage(@RequestParam("imageId") Long imageId)
    {
        return imageService.removeDatasFromAnImage(imageId) ;
    }

}
