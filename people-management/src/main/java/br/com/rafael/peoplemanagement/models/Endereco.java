package br.com.rafael.peoplemanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_enderecos")
@Getter @Setter @NoArgsConstructor
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Logradouro é obrigatório")
    private String logradouro;

    @NotNull(message = "CEP é obrigatório")
    private String cep;

    @NotNull(message = "Não pode ser nulo")
    private String numero;

    @NotNull(message = "Cidade é obrigatório")
    private String cidade;

    @JoinColumn(name = "endereco_principal")
    private Boolean principal = false;

    @ManyToMany @JsonIgnore
    @JoinTable(name = "tb_endereco_pessoa", joinColumns = @JoinColumn(name = "enderecos_id"), inverseJoinColumns = @JoinColumn(name = "pessoas_id"))
    private List<Pessoa> pessoas;

    public Endereco(String logradouro, String cep, String numero, String cidade, Pessoa pessoa) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        pessoas.add(pessoa);
    }

    public Endereco(String logradouro, String cep, String numero, String cidade) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
}
