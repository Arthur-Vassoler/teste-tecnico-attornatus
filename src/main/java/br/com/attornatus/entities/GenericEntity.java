package br.com.attornatus.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public class GenericEntity extends RepresentationModel<GenericEntity> implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}
