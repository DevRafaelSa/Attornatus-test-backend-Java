package br.com.rafael.peoplemanagement.controllers.forms;

import br.com.rafael.peoplemanagement.models.Endereco;
import br.com.rafael.peoplemanagement.models.Pessoa;
import br.com.rafael.peoplemanagement.repositories.PessoaRepository;
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
