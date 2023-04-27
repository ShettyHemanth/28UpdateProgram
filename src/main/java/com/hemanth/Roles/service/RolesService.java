package com.hemanth.Roles.service;

import com.hemanth.Roles.dto.RolesDto;
import com.hemanth.Roles.entity.Roles;
import com.hemanth.Roles.exception.EntryInvalidException;
import com.hemanth.Roles.mapper.MapperClass;
import com.hemanth.Roles.pageSetting.PageContent;
//import com.hemanth.Roles.pageSetting.PageSetting;
import com.hemanth.Roles.repository.RolesRepository;
//import com.hemanth.Roles.specification.SpecificationBuilder;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.management.relation.RoleNotFoundException;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RolesService
{

    @Autowired
    RolesRepository rolesRepository;

    public RolesDto addRoles(RolesDto rolesDto)
    {


        if(rolesRepository.existsByRoleId(rolesDto.getRoleId()))
            throw new EntryInvalidException("Role Already Exists");

        Roles newRole=new Roles();

        newRole=MapperClass.rolesDtoToRole(rolesDto);


        rolesRepository.save(newRole);

        return rolesDto;

    }


    public List<RolesDto> getRoles()
    {




        List<Roles> result=rolesRepository.findAll();

        List<RolesDto> newResult=new ArrayList<>();

        for(Roles i:result)
        {
           RolesDto n=MapperClass.rolesToRoleDto(i);
           newResult.add(n);

        }

        return newResult;


    }

    @Transactional()
    public String updateRoles(UUID id,RolesDto roles)
    {

        Roles r= MapperClass.rolesDtoToRole(roles);

        Roles newRole=rolesRepository.findById(id).get();
        newRole.setRoleState(roles.getRoleState());
        newRole.setRoleName(roles.getRoleName());
        newRole.setRoleId(roles.getRoleId());

       // newRole.setCreatedDate(roles.getCreatedDate());

        newRole.setOrgName(roles.getOrgName());
        rolesRepository.save(newRole);

        return "Updated Successfully";
    }


    @Transactional()
    public String updateRolesByRoleId(String id,RolesDto rolesDto)
    {



//        Roles r=MapperClass.rolesDtoToRole(roles);

//        Roles role= Optional.ofNullable(rolesRepository.findByName(id)).get();
//
//        role.setRoleState(roles.getRoleState());
//        role.setRoleName(roles.getRoleName());
//        role.setRoleId(roles.getRoleId());
//        role.setCreatedDate(roles.getCreatedDate());
//        role.setOrgName(roles.getOrgName());
//
//        rolesRepository.save(role);
//
//        return "Updated Successfully";


        Roles roles=Optional.ofNullable(rolesRepository.findByName(id)).get();

//        MapperClass.rolesDtoToRole(rolesDto);
        roles.setRoleState(rolesDto.getRoleState());
        roles.setRoleName(rolesDto.getRoleName());
        roles.setRoleId(rolesDto.getRoleId());
        roles.setOrgName(rolesDto.getOrgName());

        LocalDate oldDate=roles.getCreatedDate();
        LocalDate newDate=rolesDto.getCreatedDate();

        if (newDate.isBefore(oldDate))
        {
        throw new EntryInvalidException("New Date Provide Is Incorrect ");
        }




        roles.setCreatedDate(rolesDto.getCreatedDate());

        rolesRepository.save(roles);

        return "Updated Successfully";

    }

    public String deleteById(UUID id)
    {


        Optional<Roles> result=rolesRepository.findById(id);

        if(result.isEmpty())
            throw new EntryInvalidException("The Id Entered is Invalid");


        rolesRepository.deleteById(id);
        return "Deleted Successfully";
    }

    public String deleteByRoleId(String id)
    {


        Optional<Roles> result= Optional.ofNullable(rolesRepository.findByName(id));

        if(result.isEmpty())
            throw new EntryInvalidException("The Id Entered is Invalid");

        Roles role= Optional.ofNullable(rolesRepository.findByName(id)).get();
        rolesRepository.delete(role);



        return "Deleted Successfully";
    }





    public Optional<Roles> getRolesById(UUID id)
    {
        Optional<Roles> role=rolesRepository.findById(id);
        return role;
    }


//    public Page<Roles> getStudentPage(@NonNull PageSetting pageSetting)
//    {
//
//        Sort roleSort = pageSetting.buildSort();
//        Pageable rolePage = PageRequest.of(pageSetting.getPage(), pageSetting.getElementPerPage(), roleSort);
//
//        return rolesRepository.findAll(rolePage);
//    }


    public PageContent getByPage(int pageNo, int pageSize,String identity)
    {
        Sort sort = Sort.by(
                Sort.Order.desc(identity)

        );

        PageRequest pageable=PageRequest.of(pageNo,pageSize,sort);

        Page<Roles> roleList=rolesRepository.findAll((org.springframework.data.domain.Pageable) pageable);

        List<Roles> role_result=roleList.getContent();

        List<RolesDto> rolesDtos=new ArrayList<>();


        for(Roles i:role_result)
        {
            RolesDto result=MapperClass.rolesToRoleDto(i);
            rolesDtos.add(result);
        }





//        List<RolesDto> content= role_result.stream().map(s->(s)).collect(Collectors.toList());





        PageContent pageContent=new PageContent();
        pageContent.setContent(rolesDtos);
        pageContent.setPageNo(roleList.getNumber());
        pageContent.setPageSize(roleList.getSize());
        pageContent.setTotal_Element((int) roleList.getTotalElements());


        return pageContent;


    }


    public Optional<Roles> getByRoleStringId(String id)
    {
        Optional<Roles> role= Optional.ofNullable(rolesRepository.findByName(id));
       return role;
    }



    //filtering



//    public List<Role> searchRoles(String rn, String on, LocalDate cd, String rs,String ri)
//    {
//        // Create a specification builder
//        SpecificationBuilder<Role> builder = new SpecificationBuilder<>();
//
//        // Add each search criteria to the builder
//        if (cd != null ) {
//            builder.with("name", ":",cd);
//        }
//        if (on != null && !on.isEmpty()) {
//            builder.with("description", ":", description);
//        }
//        if (status != null && !status.isEmpty()) {
//            builder.with("status", ":", status);
//        }
//
//        // Use the builder to create a specification and search for roles
//        Specification<Role> spec = builder.build();
//        return roleRepository.findAll(spec);
//    }






    public PageContent filterRoles(RolesDto rolesDto, Integer pageNumber, Integer pageSize)
    {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        Roles roles=MapperClass.rolesDtoToRole(rolesDto);
        Page<Roles> result = rolesRepository.findByFilter(roles.getRoleName(),roles.getOrgName(),roles.getRoleId(),pageable);

        List<RolesDto> rolesDtos = new ArrayList<>();

        for (Roles companyForPage: result.getContent())
        {
            rolesDtos.add(MapperClass.rolesToRoleDto(companyForPage));
        }

        return new PageContent(rolesDtos, pageNumber,pageSize,result.getTotalElements());
    }




}
