package com.malloc.flousiwili.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Expense {

  private String id;
  private final BigDecimal montant;
  private final LocalDate date;
  private final String description;
  private final String secteur;

  public Expense(String id, BigDecimal montant, LocalDate date, String description, String secteur) {
    this.id = id;
    this.montant = Objects.requireNonNull(montant);
    this.date = Objects.requireNonNull(date);
    this.description = description;
    this.secteur = secteur;
  }

  public Expense(BigDecimal montant, LocalDate date, String description, String secteur) {
    if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Le montant de la dépense doit être strictement positif.");
    }
    this.montant = montant;
    this.date = Objects.requireNonNull(date);
    this.description = description;
    this.secteur = secteur;
  }

  public boolean isHighValue() {
    return this.montant.compareTo(new BigDecimal("1000")) > 0;
  }
}