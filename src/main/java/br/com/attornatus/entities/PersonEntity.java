package br.com.attornatus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@Data
@EqualsAndHashCode(callSuper = true)
public class PersonEntity extends GenericEntity {
  @Column(nullable = false)
  private String name;

  @Column(name = "date_birth", nullable = false)
  private LocalDate dateBirth;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "main_address")
  private AddressEntity mainAddress;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<AddressEntity> addresses = new ArrayList<>();
}
