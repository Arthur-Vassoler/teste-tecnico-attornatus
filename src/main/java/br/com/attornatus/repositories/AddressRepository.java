package br.com.attornatus.repositories;

import br.com.attornatus.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
  @Query("SELECT a FROM AddressEntity a WHERE a.person.id = :idPerson")
  List<AddressEntity> findByPersonId(Long idPerson);
}
