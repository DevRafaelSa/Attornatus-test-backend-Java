package br.com.rafael.peoplemanegement.controllers.dtos;

import br.com.rafael.peoplemanegement.models.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
public class EnderecoDto implements Serializable {

    private Long id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Long pessoaId;

    public EnderecoDto(Endereco endereco) {
        this.id = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
        this.pessoaId = endereco.getPessoa().getId();
    }
}
