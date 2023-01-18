package br.com.rafael.peoplemanagement.repositories;

import br.com.rafael.peoplemanegement.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Boolean existsByNameAndAndBirthDate (String name, LocalDate birthDate);

}