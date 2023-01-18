package br.com.rafael.peoplemanegement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity @Table(name = "tb_enderecos")
@Getter @Setter @NoArgsConstructor
public class Endereco implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Logradouro é obrigatório")
    private String logradouro;

    @NotNull(message = "CEP é obrigatório")
    private String cep;

    @NotNull(message = "Não pode ser nulo")
    private String numero;

    @NotNull(message = "Cidade é obrigatório")
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    public Endereco(String logradouro, String cep, String numero, String cidade, Pessoa pessoa) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        pessoa.insereEndereco(this);
    }
}
