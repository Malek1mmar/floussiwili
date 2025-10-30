package com.malloc.flousiwili.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Value;

@Value
public class ExpenseTotalResponse {
  BigDecimal totalDepense;
  int nombreTransactions;
  LocalDate dateDebut;
  LocalDate dateFin;
}