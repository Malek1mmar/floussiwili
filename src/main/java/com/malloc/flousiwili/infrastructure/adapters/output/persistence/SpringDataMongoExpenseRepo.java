package com.malloc.flousiwili.infrastructure.adapters.output.persistence;

import com.malloc.flousiwili.infrastructure.adapters.output.document.ExpenseDocument;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataMongoExpenseRepo extends MongoRepository<ExpenseDocument, String> {
    List<ExpenseDocument> findByDateBetween(LocalDate start, LocalDate end);
    List<ExpenseDocument> findBySecteur(String secteur);
    List<ExpenseDocument> findBySecteurAndDateBetween(String secteur, LocalDate start, LocalDate end);
}