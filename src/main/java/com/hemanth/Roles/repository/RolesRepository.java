package com.hemanth.Roles.repository;

import com.hemanth.Roles.entity.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<Roles, UUID>, JpaSpecificationExecutor<Roles>
{

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Roles r WHERE r.roleId = :roleId")
    boolean existsByRoleId(@Param("roleId") String roleId);

    @Query("SELECT r FROM Roles r WHERE r.roleId= ?1")
    Roles findByName(@Param("roleId") String roleId);




    List<Roles> findAll(Specification<Roles> spec);

    List<LocalDate> findBycreatedDate(LocalDate createdDate);



    @Query("SELECT r FROM Roles r WHERE (:name IS NULL OR r.roleName LIKE %:name%) " +
            "AND (:orgName IS NULL OR r.orgName LIKE %:orgName%) " +
            "AND (:roleId IS NULL OR r.roleId LIKE %:roleId%)" 
    )

    Page<Roles> findByFilter(String name,String orgName,String roleId, Pageable pageable);














}
