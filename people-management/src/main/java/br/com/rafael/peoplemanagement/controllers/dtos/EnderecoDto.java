package br.com.rafael.peoplemanagement.controllers.dtos;

import br.com.rafael.peoplemanagement.models.Endereco;
import br.com.rafael.peoplemanagement.models.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class EnderecoDto implements Serializable {

    private Long id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Boolean principal;
    private List<Pessoa> pessoas = new ArrayList<>();

    public EnderecoDto(Endereco endereco) {
        this.id = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
        this.principal = endereco.getPrincipal();
        this.pessoas = endereco.getPessoas();
    }
}
