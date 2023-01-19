package br.com.rafael.peoplemanagement.repositories;

import br.com.rafael.peoplemanagement.models.Endereco;
import br.com.rafael.peoplemanagement.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Boolean existsByLogradouroAndAndNumero (String logradouro, String numero);
}
