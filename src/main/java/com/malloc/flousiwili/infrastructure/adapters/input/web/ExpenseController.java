package com.malloc.flousiwili.infrastructure.adapters.input.web;

import com.malloc.flousiwili.application.dto.CreateExpenseCommand;
import com.malloc.flousiwili.application.dto.ExpenseTotalResponse;
import com.malloc.flousiwili.application.port.ExpenseInputPort;
import com.malloc.flousiwili.domain.model.Expense;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/depenses")
public class ExpenseController {

  private final ExpenseInputPort expenseInputPort;

  public ExpenseController(ExpenseInputPort expenseInputPort) {
    this.expenseInputPort = expenseInputPort;
  }

  @PostMapping
  public ResponseEntity<Expense> createExpense(@RequestBody CreateExpenseCommand command) {
    Expense savedExpense = expenseInputPort.recordNewExpense(command);
    return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
  }

  @GetMapping("/total")
  public ResponseEntity<ExpenseTotalResponse> getTotalByPeriod(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

    ExpenseTotalResponse response = expenseInputPort.calculateTotalByPeriod(start, end);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/total-expense")
  public ResponseEntity<ExpenseTotalResponse> getTotalByPeriod() {

    ExpenseTotalResponse response = expenseInputPort.calculateTotal();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/filtre")
  public ResponseEntity<List<Expense>> getFilteredExpenses(
      @RequestParam String secteur,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

    List<Expense> expenses = expenseInputPort.filterExpenses(secteur, start, end);
    return ResponseEntity.ok(expenses);
  }

  @DeleteMapping("/{id}")
  public void getFilteredExpenses(@PathVariable("id") String expenseId) {
    expenseInputPort.deleteExpenseById(expenseId);
  }
}