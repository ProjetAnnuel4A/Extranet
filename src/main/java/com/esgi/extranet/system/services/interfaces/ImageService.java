package com.esgi.extranet.system.services.interfaces ;

import com.esgi.extranet.system.entities.ImageEntity ;

import java.io.IOException;
import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
public interface ImageService
{

    List<ImageEntity> getAll() ;

    ImageEntity addImage(String name) ;
    ImageEntity updateImage(Long imageId, String name) ;
    boolean removeImage(Long imageId) ;
    ImageEntity getImage(Long imageId) ;

    byte[] getDatasFromAnImage(Long imageId) ;
    boolean addDatasForAnImage(Long imageId) throws IOException;
    boolean removeDatasFromAnImage(Long imageId) ;

}
