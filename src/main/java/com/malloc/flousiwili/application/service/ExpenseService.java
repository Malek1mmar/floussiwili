package com.malloc.flousiwili.application.service;

import com.malloc.flousiwili.application.dto.CreateExpenseCommand;
import com.malloc.flousiwili.application.dto.ExpenseTotalResponse;
import com.malloc.flousiwili.application.port.ExpenseInputPort;
import com.malloc.flousiwili.domain.model.Expense;
import com.malloc.flousiwili.domain.port.ExpenseRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService implements ExpenseInputPort {

  private final ExpenseRepository expenseRepository;

  public ExpenseService(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public Expense recordNewExpense(CreateExpenseCommand command) {
    Expense newExpense = new Expense(
        command.getMontant(),
        command.getDate(),
        command.getDescription(),
        command.getSecteur()
    );

    return expenseRepository.save(newExpense);
  }

  @Override
  public ExpenseTotalResponse calculateTotalByPeriod(LocalDate start, LocalDate end) {
    List<Expense> expenses = expenseRepository.findByDateBetween(start, end);

    BigDecimal total = expenses.stream()
        .map(Expense::getMontant)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return new ExpenseTotalResponse(total, expenses.size(), start, end);
  }

  @Override
  public ExpenseTotalResponse calculateTotal() {
    List<Expense> expenses = expenseRepository.findAll();

    BigDecimal total = expenses.stream()
        .map(Expense::getMontant)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return new ExpenseTotalResponse(total, expenses.size(), null, null);
  }

  @Override
  public List<Expense> filterExpenses(String secteur, LocalDate start, LocalDate end) {
    if (start != null && end != null) {
      return expenseRepository.findBySecteurAndDateBetween(secteur, start, end);
    } else {
      return expenseRepository.findBySecteur(secteur);
    }
  }

  @Override
  public void deleteExpenseById(String expenseID) {
    expenseRepository.deleteById(expenseID);
  }


}