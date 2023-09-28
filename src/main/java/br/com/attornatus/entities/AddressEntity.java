package br.com.attornatus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "address")
@Data
@EqualsAndHashCode(callSuper = true)
public class AddressEntity extends GenericEntity {
  @Column(name = "public_place", nullable = false)
  private String publicPlace;

  @Column(name = "zip_code", nullable = false)
  private String zipCode;

  @Column()
  private String number;

  @Column(nullable = false)
  private String city;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  @JsonIgnore
  private PersonEntity person;
}
