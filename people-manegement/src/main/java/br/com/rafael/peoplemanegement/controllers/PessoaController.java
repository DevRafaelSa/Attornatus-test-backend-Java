package br.com.rafael.peoplemanegement.controllers;

import br.com.rafael.peoplemanegement.controllers.dtos.PessoaDto;
import br.com.rafael.peoplemanegement.controllers.forms.PessoaForm;
import br.com.rafael.peoplemanegement.models.Pessoa;
import br.com.rafael.peoplemanegement.repositories.PessoaRepository;
import br.com.rafael.peoplemanegement.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

//    DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<?> criarPessoa (@RequestBody PessoaForm pessoaForm){
        if(!pessoaRepository.existsByNameAndAndBirthDate(pessoaForm.getName(), pessoaForm.getBirthDate())){
            Pessoa pessoa = new Pessoa(pessoaForm.getName(), pessoaForm.getBirthDate());
            pessoaRepository.save(pessoa);
            return new ResponseEntity<>(new PessoaDto(pessoa), HttpStatus.CREATED);
        }else return new ResponseEntity<>("Essa pessoa já existe", HttpStatus.BAD_REQUEST);
    }
}
