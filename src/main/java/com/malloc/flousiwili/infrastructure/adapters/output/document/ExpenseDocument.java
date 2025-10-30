package com.malloc.flousiwili.infrastructure.adapters.output.document;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "depenses")
public class ExpenseDocument {

  @Id
  private String id;
  private BigDecimal montant;
  private LocalDate date;
  private String description;
  private String secteur;
}