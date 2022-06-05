package by.iba.inteface.specification;

import by.iba.entity.*;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class OrderSpecification {

    public static Specification<SelectionOrder> findByCreatorIDLike(Long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("creator"), id);
    }

    public static Specification<SelectionOrder> findByAutoPickerIDLike(Long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("autoPicker"), id);
    }

    public static Specification<SelectionOrder> findByMinYear(Integer minYear) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("minYear"), minYear);
    }

    public static Specification<SelectionOrder> findByMaxMileage(Integer mileage) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("mileage"), mileage);
    }

    public static Specification<SelectionOrder> findByMinEngineVolume(Double minEngineVolume) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("minEngineVolume"),minEngineVolume);
    }

    public static Specification<SelectionOrder> findByMaxEngineVolume(Double maxEngineVolume) {
        Specification<SelectionOrder> res = (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("maxEngineVolume"),maxEngineVolume);
        res.or((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("maxEngineVolume"),maxEngineVolume));
        return res;
    }

    public static Specification<SelectionOrder> findByOrderStatus(OrderStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("orderStatus").get("name"), status.getName());
    }

    public static Specification<SelectionOrder> findAllByBrands(List<CarBrand> brands) {
        return (Specification<SelectionOrder>) (root, query, builder) -> {
            List<Predicate> predicates = buildPredicates(root, builder, brands);
            return
                    builder.or(predicates.toArray(new Predicate[predicates.size()]));

        };
    }

    private static List<Predicate> buildPredicates(Root<SelectionOrder> root,
                                                   CriteriaBuilder criteriaBuilder,
                                                   List<CarBrand> brands) {
        List<Predicate> predicates = new ArrayList<>();
        brands.forEach(brand -> predicates.add(
                criteriaBuilder.equal(
                        root.join("brands").get("name"), brand.getName())));
        return predicates;
    }
}
