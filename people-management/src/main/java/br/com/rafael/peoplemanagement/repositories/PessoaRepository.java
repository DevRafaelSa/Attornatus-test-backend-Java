package br.com.rafael.peoplemanagement.repositories;

import br.com.rafael.peoplemanagement.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Boolean existsByNameAndAndBirthDate (String name, LocalDate birthDate);

}
