package ro.fasttrackit.course8.homework.repository.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.course8.homework.model.entity.CleanupEntity;
import ro.fasttrackit.course8.homework.model.filters.CleanupFilters;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
public class CleanupDao {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public CleanupDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<CleanupEntity> getAll(CleanupFilters filters, Pageable pageable) {
        CriteriaQuery<CleanupEntity> criteria = criteriaBuilder.createQuery(CleanupEntity.class);
        Root<CleanupEntity> root = criteria.from(CleanupEntity.class);
        List<Predicate> predicates = buildPredicates(filters, root);
        CriteriaQuery<CleanupEntity> query = criteria.select(root).where(predicates.toArray(new Predicate[0]));
        List<CleanupEntity> cleanups = entityManager.createQuery(query).getResultList();

        return new PageImpl<>(cleanups, pageable, cleanups.size());
    }

    private List<Predicate> buildPredicates(CleanupFilters filters, Root<CleanupEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        ofNullable(filters.roomId())
                .ifPresent(roomId -> predicates.add(criteriaBuilder.equal(root.get("room").get("id"), roomId)));
        ofNullable(filters.number())
                .ifPresent(number -> predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("room").get("number")), number.toLowerCase())));
        ofNullable(filters.hotelName())
                .ifPresent(hotelName -> predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("room").get("hotelName")), hotelName.toLowerCase())));
        ofNullable(filters.floor())
                .ifPresent(floor -> predicates.add(criteriaBuilder.equal(root.get("room").get("floor"), floor)));
        ofNullable(filters.id())
                .ifPresent(id -> predicates.add(criteriaBuilder.equal(root.get("id"), id)));
        return predicates;
    }
}
