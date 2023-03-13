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
import ro.fasttrackit.course8.homework.model.entity.RoomEntity;
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
        List<Predicate> whereClause = new ArrayList<>();

        ofNullable(filters.number())
                .ifPresent(number -> whereClause.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("number")), number.toLowerCase())));
        ofNullable(filters.hotelName())
                .ifPresent(hotelName -> whereClause.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("hotelName")), hotelName.toLowerCase())));
        ofNullable(filters.floor())
                .ifPresent(floor -> whereClause.add(criteriaBuilder.equal(root.get("floor"), floor)));
        ofNullable(filters.tv())
                .ifPresent(tv -> whereClause.add(criteriaBuilder.equal(root.get("tv"), tv)));
        ofNullable(filters.doubleBed())
                .ifPresent(doubleBed -> whereClause.add(criteriaBuilder.equal(root.get("doubleBed"), doubleBed)));

        CriteriaQuery<RoomEntity> query = criteria.select(root).where(whereClause.toArray(new Predicate[0]));
        List<RoomEntity> rooms = entityManager.createQuery(query).getResultList();

        return new PageImpl<>(rooms, pageable, rooms.size());
    }
}
