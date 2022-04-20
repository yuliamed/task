package by.iba.inteface.specification;

import by.iba.entity.Role;
import by.iba.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

@NoArgsConstructor
public final class UserSpecification {

    public static Specification<User> findByRole(Role role) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.join("roles").get("name"), role.getName());
            return criteriaBuilder.and(predicate);
        };
    }

    public static Specification<User> findByNameLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), '%' + name + '%');
    }

    public static Specification<User> findBySurnameLike(String surname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("surname"), '%' + surname + '%');
    }

    public static Specification<User> findByActiveStatus(Boolean status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isActive"), status);
    }

}