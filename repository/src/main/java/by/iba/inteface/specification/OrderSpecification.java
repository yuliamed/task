package by.iba.inteface.specification;

import by.iba.entity.order.Order;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
public class OrderSpecification {
    public static Specification<Order> findByCreatorIDLike(Long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("creator"), id);
    }

    public static Specification<Order> findByCreatorNameLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.join("creator").get("name"), name);
    }

    public static Specification<Order> findByAutoPickerIDLike(Long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("autoPicker"), id);
    }
}
