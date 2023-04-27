package com.hemanth.Roles.controller;

import com.hemanth.Roles.dto.RolesDto;
import com.hemanth.Roles.entity.Roles;
import com.hemanth.Roles.pageSetting.PageContent;
//import com.hemanth.Roles.pageSetting.PageSetting;
import com.hemanth.Roles.repository.RolesRepository;
import com.hemanth.Roles.service.RolesService;
//import com.hemanth.Roles.specification.CustomSpecification;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RolesController
{

    @Autowired
    RolesService rolesService;

    @Autowired
    RolesRepository rolesRepository;

    @RequestMapping(method = RequestMethod.POST)
    public RolesDto addRoles(@Valid @RequestBody RolesDto roles)
    {

        return rolesService.addRoles(roles);

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RolesDto> getRoles()
    {
        return rolesService.getRoles();

    }



    @RequestMapping(method = RequestMethod.GET,value="/{id}")
    public Optional<Roles> getRolesById(@PathVariable UUID id)
    {
        return rolesService.getRolesById(id);

    }

    @RequestMapping(method = RequestMethod.DELETE,value="/{id}")
    public String deleteRolesById(@PathVariable UUID id)
    {
        return rolesService.deleteById(id);

    }

    @RequestMapping(method = RequestMethod.DELETE,value="/id/{ids}")
    public String deleteRolesByRoleId(@PathVariable("ids") String id)
    {
           return rolesService.deleteByRoleId(id);

    }





    @RequestMapping(method = RequestMethod.PUT,value="/{id}")
    public String updateRolesById(@PathVariable UUID id,@RequestBody RolesDto roles)
    {
        return rolesService.updateRoles(id,roles);

    }


    @RequestMapping(method = RequestMethod.PUT,value="/id/{ids}")
    public String updateRolesById(@PathVariable("ids") String ids,@RequestBody RolesDto roles)
    {

        return rolesService.updateRolesByRoleId(ids,roles);

    }



//    @GetMapping("/page")
//    public Page<Roles> getStudentPage(PageSetting pageSettings)
//    {
//
//        log.info(
//                "Request for Roles page received with data : " + pageSettings);
//
//        return rolesService.getStudentPage(pageSettings);
//    }

    @RequestMapping(method = RequestMethod.GET,value = "/paging")
    public PageContent getStudents(@RequestParam(name="pageSize", required = false, defaultValue = "1") Integer pageSize,
                                   @RequestParam(name="pageNo", required = false, defaultValue = "0") Integer pageNo,
                                   @RequestParam(name="sort", required = false, defaultValue = "roleName") String identity)
    {
        return rolesService.getByPage(pageNo,pageSize,identity);
    }


    @RequestMapping(method = RequestMethod.GET,value="/name/{ids}")
    public Optional<Roles> getByNames(@PathVariable("ids") String id)
    {
        return rolesService.getByRoleStringId(id);

    }







//    @CrossOrigin(origins = "*")
//    @GetMapping("/filtered")
//    public List<Roles> roleFilter(@RequestParam(name="roleName",required = false) String roleName, @RequestParam(name="roleId",required = false) String roleId,
//                                 @RequestParam(name="orgName",required = false) String orgName, @RequestParam(name="roleState",required = false) Boolean roleState,
//                                 @RequestParam(name="createdDate",required = false) LocalDate createdDate)
//    {
//
//        Roles roleNew = new Roles();
//        roleNew.setRoleName(roleName);
//        roleNew.setRoleId(roleId);
//        roleNew.setRoleState(roleState);
//        roleNew.setOrgName(orgName);
//        roleNew.setCreatedDate(createdDate);
//
//
//        CustomSpecification spec = new CustomSpecification(roleNew);
//
//        return rolesRepository.findAll(spec);
//    }

//
//    @GetMapping("/new/filter")
//    public PageContent filterAndPagination(RolesDto rolesDto, @RequestParam(name="pageNo",defaultValue = "0", required = false) Integer pageNo,
//                                          @RequestParam(name="pageSize",defaultValue = "10",required = false) Integer pageSize)
//    {
//        return rolesService.filterRoles(rolesDto,pageNo,pageSize);
//    }



    @GetMapping("/new/filter")
    public PageContent filterAndPagination(
            @RequestParam(name="roleName",required = false) String roleName, @RequestParam(name="roleId",required = false) String roleId,
            @RequestParam(name="orgName",required = false) String orgName, @RequestParam(name="roleState",required = false) Boolean roleState,
            @RequestParam(name="createdDate",required = false) LocalDate createdDate,

            @RequestParam(name="pageNo",defaultValue = "0", required = false) Integer pageNo,
                                           @RequestParam(name="pageSize",defaultValue = "10",required = false) Integer pageSize)
    {

        RolesDto rolesDto=new RolesDto();
        rolesDto.setRoleName(roleName);
        rolesDto.setRoleId(roleId);
        rolesDto.setRoleState(roleState);
        rolesDto.setOrgName(orgName);
        rolesDto.setCreatedDate(createdDate);



        return rolesService.filterRoles(rolesDto,pageNo,pageSize);
    }



}
