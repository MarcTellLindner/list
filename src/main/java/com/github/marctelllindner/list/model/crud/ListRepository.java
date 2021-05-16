package com.github.marctelllindner.list.model.crud;

import com.github.marctelllindner.list.model.entities.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long> {
    Optional<ListEntity> findByIdentifier(String identifier);
}
