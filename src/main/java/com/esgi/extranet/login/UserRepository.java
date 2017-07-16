package com.esgi.extranet.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author timotheearnauld
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(nativeQuery = true,
            value = "select * from users u where u.id_classmate = :idClassmate and u.role='STUDENT'")
    List<UserEntity> findAllByIdClassmate(@Param("idClassmate") Long idClassmate);

    UserEntity findByPseudo(String pseudo);

    UserEntity findById(Long id);

    UserEntity findByToken(String token);

    @Query(nativeQuery = true,
    value = "select u.role from users u where u.token = :token")
    String findRoleForToken(@Param("token") String token);

    @Query(nativeQuery = true,
            value = "select * from users u where u.role = 'STUDENT'")
    List<UserEntity>findAllStudents();

    @Query(nativeQuery = true,
            value = "select * from users u where u.role = 'TEACHER'")
    List<UserEntity>findAllTeachers();

    @Query(nativeQuery = true,
            value = "select * from users u where u.email = :email")
    UserEntity findUserByEmail(@Param("email") String email);

    @Query(nativeQuery = true,
            value = "select * from users u where u.token = :token")
    UserEntity findUserByToken(@Param("token") String token);

    @Query(nativeQuery = true,
            value = "select u.token from users u where u.pseudo = :pseudo")
    String getToken(@Param("pseudo") String pseudo);

    @Query(nativeQuery = true,
            value = "select u.pseudo from users u where u.token = :token")
    String getPseudo(@Param("token") String token);

    @Query(nativeQuery = true,
            value = "select u.id from users u where u.pseudo=:pseudo")
    String findIdByPseudo(@Param("pseudo") String pseudo);

    @Query(nativeQuery = true,
            value = "select u.id from users u where u.token = :token")
    int findIdForToken(@Param("token") String token);

    @Query(nativeQuery = true,
            value = "select * from users u where u.token = :token")
    UserEntity findInformations(@Param("token") String token);

    @Query(nativeQuery = true,
            value = "select u.id_classmate from users u where u.id = :id")
    int getClassmate(@Param("id") int id);
}
