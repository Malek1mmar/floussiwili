package com.malloc.flousiwili.infrastructure.adapters.output.persistence;

import com.malloc.flousiwili.domain.model.Expense;
import com.malloc.flousiwili.domain.port.ExpenseRepository;
import com.malloc.flousiwili.infrastructure.adapters.output.document.ExpenseDocument;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MongoExpenseAdapter implements ExpenseRepository {

  private final SpringDataMongoExpenseRepo mongoRepo;

  public MongoExpenseAdapter(SpringDataMongoExpenseRepo mongoRepo) {
    this.mongoRepo = mongoRepo;
  }

  private Expense toDomain(ExpenseDocument doc) {
    return new Expense(doc.getId(), doc.getMontant(), doc.getDate(), doc.getDescription(), doc.getSecteur());
  }

  private ExpenseDocument toDocument(Expense domain) {
    ExpenseDocument doc = new ExpenseDocument();
    doc.setId(domain.getId());
    doc.setMontant(domain.getMontant());
    doc.setDate(domain.getDate());
    doc.setDescription(domain.getDescription());
    doc.setSecteur(domain.getSecteur());
    return doc;
  }

  @Override
  public Expense save(Expense expense) {
    ExpenseDocument doc = toDocument(expense);
    ExpenseDocument savedDoc = mongoRepo.save(doc);
    return toDomain(savedDoc);
  }

  @Override
  public List<Expense> findAll() {
    return mongoRepo.findAll().stream().map(this::toDomain).toList();
  }

  @Override
  public void deleteById(String expenseId) {
      mongoRepo.deleteById(expenseId);
  }

  @Override
  public List<Expense> findByDateBetween(LocalDate start, LocalDate end) {
    return mongoRepo.findByDateBetween(start, end).stream().map(this::toDomain).toList();
  }

  @Override
  public List<Expense> findBySecteur(String secteur) {
    return mongoRepo.findBySecteur(secteur).stream().map(this::toDomain).toList();
  }

  @Override
  public List<Expense> findBySecteurAndDateBetween(String secteur, LocalDate start, LocalDate end) {
    return mongoRepo.findBySecteurAndDateBetween(secteur, start, end).stream().map(this::toDomain).toList();
  }
}