package com.malloc.flousiwili.application.port;

import com.malloc.flousiwili.application.dto.CreateExpenseCommand;
import com.malloc.flousiwili.application.dto.ExpenseTotalResponse;
import com.malloc.flousiwili.domain.model.Expense;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseInputPort {

  Expense recordNewExpense(CreateExpenseCommand command);

  ExpenseTotalResponse calculateTotalByPeriod(LocalDate start, LocalDate end);

  ExpenseTotalResponse calculateTotal();

  List<Expense> filterExpenses(String secteur, LocalDate start, LocalDate end);

  void deleteExpenseById(String expenseID);
}