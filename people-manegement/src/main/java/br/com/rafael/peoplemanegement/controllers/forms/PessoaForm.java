package br.com.rafael.peoplemanegement.controllers.forms;

import br.com.rafael.peoplemanegement.models.Endereco;
import br.com.rafael.peoplemanegement.models.Pessoa;
import br.com.rafael.peoplemanegement.repositories.PessoaRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class PessoaForm {

    @NotBlank
    private String name;

    @NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private List<Endereco> enderecos;

    public PessoaForm(String name, LocalDate birthDate) {
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
