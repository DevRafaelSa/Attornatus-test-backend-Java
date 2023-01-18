package br.com.rafael.peoplemanagement.repositories;

import br.com.rafael.peoplemanegement.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Boolean existsByLogradouroAndAndNumero (String logradouro, String numero);
    Optional<Endereco> findAllByPessoaId (Long idPessoa);

}
