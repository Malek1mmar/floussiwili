package com.malloc.flousiwili.domain.port;

import com.malloc.flousiwili.domain.model.Expense;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository {
  Expense save(Expense expense);

  List<Expense> findAll();

  void deleteById(String expenseId);

  List<Expense> findByDateBetween(LocalDate start, LocalDate end);

  List<Expense> findBySecteur(String secteur);

  List<Expense> findBySecteurAndDateBetween(String secteur, LocalDate start, LocalDate end);

}