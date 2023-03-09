package ro.fasttrackit.curs8.course8.code.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.curs8.course8.code.model.entity.Address;
import ro.fasttrackit.curs8.course8.code.model.entity.Customer;
import ro.fasttrackit.curs8.course8.code.model.filters.CustomerFilters;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.function.Predicate.not;

@Repository
public class CustomerDao {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public CustomerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Customer> getAll(CustomerFilters filters) {
        CriteriaQuery<Customer> criteria = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);
//        CriteriaQuery<Customer> query = criteria.select(root);
        List<Predicate> wherePredicate = new ArrayList<>();
        Subquery<Address> addressSubquery = criteria.subquery(Address.class);
        Root<Address> addressRoot = addressSubquery.from(Address.class);

        ofNullable(filters.name())
                .ifPresent(name -> wherePredicate.add(criteriaBuilder.equal(root.get("name"), name)));
        ofNullable(filters.age())
                .ifPresent(age -> wherePredicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), age)));
        ofNullable(filters.city())
                .filter(not(List::isEmpty))
                .ifPresent(cities -> wherePredicate.add(root.get("address").get("city").in(cities)));

        CriteriaQuery<Customer> query = criteria.select(root).where(wherePredicate.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
