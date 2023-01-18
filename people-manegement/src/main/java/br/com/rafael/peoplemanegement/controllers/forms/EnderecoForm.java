package br.com.rafael.peoplemanegement.controllers.forms;

import br.com.rafael.peoplemanegement.models.Endereco;
import br.com.rafael.peoplemanegement.models.Pessoa;
import br.com.rafael.peoplemanegement.repositories.EnderecoRepository;
import br.com.rafael.peoplemanegement.repositories.PessoaRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

    @NotBlank
    private Long idPessoa;

    public EnderecoForm(String logradouro, String cep, String numero, String cidade) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
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
