//package com.hemanth.Roles.specification;
//
//import com.hemanth.Roles.entity.Roles;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomSpecification implements Specification<Roles>
//{
//
//    private final  Roles roles;
//
//
//    public CustomSpecification(Roles role)
//    {
//
//        this.roles = role;
//    }
//
//
//
//    @Override
//    public Specification<Roles> and(Specification<Roles> other)
//    {
//        return Specification.super.and(other);
//    }
//
//    @Override
//    public Specification<Roles> or(Specification<Roles> other)
//    {
//        return Specification.super.or(other);
//    }
//
//    @Override
//    public Predicate toPredicate(Root<Roles> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
//    {
//
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (roles.getRoleName() != null) {
//            String roleName = "%" + roles.getRoleName().toLowerCase() + "%";
//            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("roleName")), roleName));
//        }
//
//        if (roles.getRoleId() != null) {
//            String roleId = "%" + roles.getRoleId().toLowerCase() + "%";
//            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("roleId")), roleId));
//        }
//
//        if (roles.getOrgName() != null) {
//            String orgName = "%" + roles.getOrgName().toLowerCase() + "%";
//            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("orgName")), orgName));
//        }
//
//        if (roles.getCreatedDate()!= null)
//        {
//            predicates.add(criteriaBuilder.equal(root.get("createdDate"),roles.getCreatedDate()));
//        }
//
//        if (roles.getRoleState()!= null)
//        {
//            predicates.add(criteriaBuilder.equal(root.get("roleState"),roles.getRoleState()));
//        }
//
//        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//
//
//    }
//}
