package br.com.rafael.peoplemanagement.repositories;

import br.com.rafael.peoplemanagement.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Boolean existsByLogradouroAndAndNumero (String logradouro, String numero);
}
