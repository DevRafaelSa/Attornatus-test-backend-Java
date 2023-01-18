package br.com.rafael.peoplemanagement.controllers.dtos;

import br.com.rafael.peoplemanegement.models.Endereco;
import br.com.rafael.peoplemanegement.models.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Setter @Getter @NoArgsConstructor
public class PessoaDto implements Serializable {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private List<Endereco> enderecos;

    public PessoaDto(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.name = pessoa.getName();
        this.birthDate = pessoa.getBirthDate();
        this.enderecos = pessoa.getEnderecos();
    }
}
