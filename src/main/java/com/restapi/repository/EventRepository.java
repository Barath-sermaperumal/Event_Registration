package com.restapi.repository;

import com.restapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    @Query("select u from Event u where u.category.id=?1")
    List<Event> findCategory(Long id);

//    @Query("update soldTickets=:count from event a where a.id=:id")
//    void updateSoldTickets(long id,int count);
}
