package ru.home.taskrcr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.taskrcr.domain.PrintingDocument;

import java.util.List;

public interface PrintingDocRepository extends JpaRepository<PrintingDocument, Integer> {
    List<PrintingDocument> findAllByOrderByTimeprintAsc();
    List<PrintingDocument> findAllByOrderByTimeprintDesc();
}
