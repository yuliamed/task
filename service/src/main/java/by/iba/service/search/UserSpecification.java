package by.iba.service.search;

import by.iba.dto.req.UserSearchCriteria;
import by.iba.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public final class UserSpecification implements Specification<User> {
    private UserSearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate predicate = null;
        switch (criteria.getOperation()) {
            case IN: {
                predicate = builder.in(root.get(criteria.getKey())).value(criteria.getValue());
                break;
            }
            case NOT_IN: {
                predicate = builder.not(root.get(criteria.getKey())).in(criteria.getValue());
                break;
            }
            case GREATER_THAN: {
                predicate = builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
                break;
            }
            case LESS_THAN: {
                predicate = builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
                break;
            }
            case EQUAL: {
                predicate = builder.equal(root.get(criteria.getKey()), criteria.getValue());
                break;
            }
            case NOT_EQUAL: {
                predicate = builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
                break;
            }

        }
        return predicate;
    }
//    public static Specification<User> findByCriteria(UserSearchCriteria userSearchCriteria){
////        TypeOfRole role = userSearchCriteria.getRole();
////        if (Objects.isNull(role)) {
////            return null;
////        }
////        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("role"), role));
//        return new Specification<User>() {
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.equal(root.get("roles").get("name"), userSearchCriteria.getRole().name());
//            }
//        };
//    }

}
