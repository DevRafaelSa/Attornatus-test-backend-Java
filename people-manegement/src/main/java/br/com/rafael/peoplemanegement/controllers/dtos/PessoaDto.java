package br.com.rafael.peoplemanegement.controllers.dtos;

import br.com.rafael.peoplemanegement.models.Endereco;
import br.com.rafael.peoplemanegement.models.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter @Getter @NoArgsConstructor
public class PessoaDto implements Serializable {

    private Long id;
    private String name;
    private Date birthDate;
    private List<Endereco> enderecos;

    public PessoaDto(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.name = pessoa.getName();
        this.birthDate = pessoa.getBirthDate();
        this.enderecos = pessoa.getEnderecos();
    }
}
