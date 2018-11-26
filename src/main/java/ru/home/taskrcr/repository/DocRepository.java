package ru.home.taskrcr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.taskrcr.domain.Document;

import java.util.List;

public interface DocRepository extends JpaRepository<Document, Integer> {

    List<Document> findAllByOrderByTimeprintAsc();
    List<Document> findAllByOrderByTimeprintDesc();

}
