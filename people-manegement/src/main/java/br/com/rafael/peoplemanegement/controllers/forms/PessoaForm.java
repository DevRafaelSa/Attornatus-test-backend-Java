package br.com.rafael.peoplemanegement.controllers.forms;

import br.com.rafael.peoplemanegement.models.Endereco;
import br.com.rafael.peoplemanegement.models.Pessoa;
import br.com.rafael.peoplemanegement.repositories.PessoaRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class PessoaForm {

    private String name;
    private Date birthDate;
    private List<Endereco> enderecos;

    public PessoaForm(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Pessoa atualizar(Long id, PessoaRepository pessoaRepository){
        Pessoa pessoa = pessoaRepository.findById(id).get();
        pessoa.setName(this.name);
        pessoa.setBirthDate(this.birthDate);
        return pessoa;
    }
}
