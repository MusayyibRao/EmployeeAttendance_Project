package com.emp.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.emp.management.dataModel.AdminEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {


    Optional<AdminEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE mraocompany.admindata as a \n" +
            "SET a.firstname =:firstname, a.lastname =:lastname ,a.password=:password \n" +
            "WHERE email=:email", nativeQuery = true)
    void updateAdmin(String firstname, String lastname, String password, String email);

    @Modifying
    @Transactional
    @Query(value = "delete from mraocompany.admindata as a where a.email=:email", nativeQuery = true)
    void deleteByEmail(@Param("email") String email);


}
