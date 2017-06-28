package com.esgi.extranet.system.services.implementations ;

import com.esgi.extranet.system.UploadSystem ;
import com.esgi.extranet.system.entities.ImageEntity ;
import com.esgi.extranet.system.repositories.ImageRepository ;
import com.esgi.extranet.system.services.interfaces.ImageService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Service ;

import javax.transaction.Transactional ;
import java.io.IOException ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@Service
public class ImageServiceImpl implements ImageService
{

    private ImageRepository imageRepository ;


    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository)
    {
        this.imageRepository = imageRepository ;
    }

    @Override
    public List<ImageEntity> getAll()
    {
        return imageRepository.findAll() ;
    }

    @Override
    @Transactional
    public ImageEntity addImage(String name)
    {
        ImageEntity imageEntity = ImageEntity.builder()
                .name(name)
                .build() ;

        imageRepository.save(imageEntity) ;

        return imageEntity ;
    }

    @Override
    @Transactional
    public ImageEntity updateImage(Long imageId, String name)
    {
        ImageEntity imageEntity = imageRepository.findById(imageId) ;

        imageEntity.setName(name) ;

        imageRepository.save(imageEntity) ;

        return imageEntity ;
    }

    @Override
    @Transactional
    public boolean removeImage(Long imageId)
    {
        imageRepository.delete(imageId) ;

        return (imageRepository.findById(imageId) == null) ;
    }

    @Override
    public ImageEntity getImage(Long imageId)
    {
        return imageRepository.findById(imageId) ;
    }


    @Override
    public byte[] getDatasFromAnImage(Long imageId)
    {
        ImageEntity imageEntity = imageRepository.findById(imageId) ;

        return imageEntity.getDatas() ;
    }

    @Override
    @Transactional
    public boolean addDatasForAnImage(Long imageId) throws IOException
    {
        ImageEntity imageEntity = imageRepository.findById(imageId) ;

        byte[] datas = UploadSystem.receiveUpload() ;

        imageEntity.setDatas(datas) ;

        imageRepository.save(imageEntity) ;

        return true ;
    }

    @Override
    @Transactional
    public boolean removeDatasFromAnImage(Long imageId)
    {
        ImageEntity imageEntity = imageRepository.findById(imageId) ;

        imageEntity.setDatas(null) ;

        imageRepository.save(imageEntity) ;

        return true ;
    }

}
