package com.malloc.flousiwili.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class CreateExpenseCommand {
  private BigDecimal montant;
  private LocalDate date;
  private String description;
  private String secteur;
}