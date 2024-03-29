package by.iba.inteface.specification;

import by.iba.entity.order.Order;
import by.iba.entity.order.SelectionOrder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.Locale;

@NoArgsConstructor
public class OrderSpecification {
    public static Specification<Order> findByCreatorIDLike(Long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("creator"), id);
    }

    public static Specification<SelectionOrder> findByCreatorNameLike(String name, String surname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.join("creator").get("name"), name);
    }

    public static Specification<Order> findByAutoPickerIDLike(Long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("autoPicker"), id);
    }

    public static Specification<Order> getSpecification(String param) {
        return (root, criteriaQuery, criteriaBuilder) ->
        {
            criteriaQuery.distinct(true);
            String searchParam = param.toLowerCase(Locale.ROOT);
            Predicate predicateForData = criteriaBuilder.or(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.join("creator").get("name")), "%" + searchParam + "%"),
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.join("creator").get("surname")), "%" + searchParam + "%"),
                    criteriaBuilder.like(root.get("creationDate").as(String.class), "%" + searchParam + "%"),
                    criteriaBuilder.like(root.get("lastUpdateDate").as(String.class), "%" + searchParam + "%")
            );
            Predicate predicateNonNull = criteriaBuilder.or(
                    criteriaBuilder.isNull(root.get("autoPicker")),
                    criteriaBuilder.like(root.join("autoPicker").get("name").as(String.class), "%" + param + "%"),
                    criteriaBuilder.like(root.join("autoPicker").get("surname").as(String.class), "%" + param + "%"));
            Predicate predicateForAutoPicker = criteriaBuilder.or(predicateNonNull
            );

            return criteriaBuilder.or(predicateForData, predicateForAutoPicker);
        };
    }
}
