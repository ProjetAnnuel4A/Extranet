package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import org.junit.Assert ;
import org.junit.Before ;
import org.junit.Test ;
import org.springframework.beans.factory.annotation.Autowired ;

/**
 * Created by Samuel Bijou on 01/07/2017.
 */
public class UserQuizzRepositoryTests
{

    @Autowired
    private UserQuizzRepository userQuizzRepository ;

    private UserQuizzEntity userQuizzEntity ;


    @Before
    public void initialize_datas()
    {
        userQuizzEntity = new UserQuizzEntity(new Long(1), new Long(1), new Long(1), new Long(1), null, 1) ;

        userQuizzRepository.save(userQuizzEntity) ;
    }


    @Test
    public void should_find_user_quizz_by_id()
    {
        UserQuizzEntity result = userQuizzRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertEquals(result, userQuizzEntity) ;
    }

}
