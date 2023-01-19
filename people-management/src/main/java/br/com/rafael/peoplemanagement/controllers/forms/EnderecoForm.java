package br.com.rafael.peoplemanagement.controllers.forms;

import br.com.rafael.peoplemanagement.models.Endereco;
import br.com.rafael.peoplemanagement.models.Pessoa;
import br.com.rafael.peoplemanagement.repositories.EnderecoRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class EnderecoForm {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String cep;

    @NotBlank
    private String numero;

    @NotBlank
    private String cidade;

    private Boolean principal = false;

    @NotBlank
    private List<Pessoa> pessoas = new ArrayList<>();

    public EnderecoForm(String logradouro, String cep, String numero, String cidade) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
//        this.pessoas = Collections.singletonList(pessoa);
    }

    public Endereco atualizar(Long id, EnderecoRepository enderecoRepository){
        Endereco endereco = enderecoRepository.findById(id).get();
        endereco.setLogradouro(this.logradouro);
        endereco.setCep(this.cep);
        endereco.setNumero(this.numero);
        endereco.setCidade(this.cidade);
        return endereco;
    }

}
