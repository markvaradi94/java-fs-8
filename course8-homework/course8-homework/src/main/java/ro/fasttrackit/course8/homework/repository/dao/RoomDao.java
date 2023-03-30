package ro.fasttrackit.course8.homework.repository.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.course8.homework.model.entity.RoomEntity;
import ro.fasttrackit.course8.homework.model.entity.RoomFacilitiesEntity;
import ro.fasttrackit.course8.homework.model.filters.RoomFilters;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
public class RoomDao {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public RoomDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<RoomEntity> getAll(RoomFilters filters, Pageable pageable) {
        CriteriaQuery<RoomEntity> criteria = criteriaBuilder.createQuery(RoomEntity.class);
        Root<RoomEntity> root = criteria.from(RoomEntity.class);
        Join<RoomEntity, RoomFacilitiesEntity> facilities = root.join("facilities");
        List<Predicate> predicates = buildPredicates(filters, root, facilities);

        CriteriaQuery<RoomEntity> query = criteria.select(root).where(predicates.toArray(new Predicate[0]));
        List<RoomEntity> rooms = entityManager.createQuery(query).getResultList();

        return new PageImpl<>(rooms, pageable, rooms.size());
    }

    private List<Predicate> buildPredicates(RoomFilters filters, Root<RoomEntity> root, Join<RoomEntity, RoomFacilitiesEntity> facilities) {
        List<Predicate> predicates = new ArrayList<>();
        ofNullable(filters.number())
                .ifPresent(number -> predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("number")), number.toLowerCase())));
        ofNullable(filters.hotelName())
                .ifPresent(hotelName -> predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("hotelName")), hotelName.toLowerCase())));
        ofNullable(filters.floor())
                .ifPresent(floor -> predicates.add(criteriaBuilder.equal(root.get("floor"), floor)));
        ofNullable(filters.tv())
                .ifPresent(tv -> predicates.add(criteriaBuilder.equal(facilities.get("tv"), tv)));
        ofNullable(filters.doubleBed())
                .ifPresent(doubleBed -> predicates.add(criteriaBuilder.equal(facilities.get("doubleBed"), doubleBed)));
        return predicates;
    }
}
