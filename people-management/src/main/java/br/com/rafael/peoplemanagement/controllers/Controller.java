package br.com.rafael.peoplemanagement.controllers;

import br.com.rafael.peoplemanagement.controllers.dtos.EnderecoDto;
import br.com.rafael.peoplemanagement.controllers.dtos.PessoaDto;
import br.com.rafael.peoplemanagement.controllers.forms.EnderecoForm;
import br.com.rafael.peoplemanagement.controllers.forms.PessoaForm;
import br.com.rafael.peoplemanagement.models.Endereco;
import br.com.rafael.peoplemanagement.models.Pessoa;
import br.com.rafael.peoplemanagement.repositories.EnderecoRepository;
import br.com.rafael.peoplemanagement.repositories.PessoaRepository;
import br.com.rafael.peoplemanagement.services.EnderecoService;
import br.com.rafael.peoplemanagement.services.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequestMapping(value = "/api")
@Api(value = "People-Management")
public class Controller {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @ApiOperation(value = "Cadastra uma pessoa")
    @PostMapping(value = "/cadastrarPessoa")
    public ResponseEntity<?> criarPessoa (@RequestBody PessoaForm pessoaForm){
        if(!pessoaRepository.existsByNameAndAndBirthDate(pessoaForm.getName(), pessoaForm.getBirthDate())){
            Pessoa pessoa = new Pessoa(pessoaForm.getName(), pessoaForm.getBirthDate());
            pessoaRepository.save(pessoa);
            return new ResponseEntity<>(new PessoaDto(pessoa), HttpStatus.CREATED);
        }else return new ResponseEntity<>("Essa pessoa já existe", HttpStatus.BAD_REQUEST);
    }


    @ApiOperation(value = "Edita uma pessoa pelo Id")
    @PutMapping(value = "/pessoas/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        pessoa = pessoaService.update(id, pessoa);
        return ResponseEntity.ok().body(pessoa);
    }

    @ApiOperation(value = "Retorna uma pessoa pelo Id")
    @GetMapping(value = "/pessoas/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        Pessoa obj = pessoaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @ApiOperation(value = "Retorna todas as pessoas")
    @GetMapping(value = "/pessoas/todos")
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> list = pessoaService.findall();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Retorna todas as pessoas")
    @PutMapping("/addEnderecoAPessoa/pessoaId/{pessoaId}/endereco/{enderecoId}")
    @Transactional
    public ResponseEntity<?> addEnderecoAPessoa (@PathVariable Long pessoaId, @PathVariable Long enderecoId){
        Optional<Pessoa> pessoa = enderecoService.encontraPessoa(pessoaId);
        if (pessoa.isPresent()) {
            Optional<Endereco> endereco = enderecoRepository.findById(enderecoId);
            if(endereco.isPresent()) {
                Endereco endereco1 = endereco.get();
                endereco1.setPessoas(Collections.singletonList(pessoa.get()));
                return new ResponseEntity<>(new EnderecoDto(endereco1), HttpStatus.CREATED);
            }else return new ResponseEntity<>("Nenhuma pessoa encontrada", HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>("Nenhum endereco encontrado", HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/cadastrarEndereco")
    public ResponseEntity<?> criarEndereco (@RequestBody EnderecoForm enderecoForm){
        if(!enderecoRepository.existsByLogradouroAndAndNumero(enderecoForm.getLogradouro(), enderecoForm.getNumero())){
            Endereco endereco = new Endereco(enderecoForm.getLogradouro(), enderecoForm.getCep(), enderecoForm.getNumero(), enderecoForm.getCidade());
            enderecoRepository.save(endereco);
            return new ResponseEntity<>(new EnderecoDto(endereco), HttpStatus.CREATED);
        }else return new ResponseEntity<>("Essa pessoa já existe", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/enderecos/{idPessoa}")
    public ResponseEntity<List<Endereco>> listaEnderecos(@PathVariable Long idPessoa){
        enderecoService.encontrandoEnderecosDaPessoa(idPessoa);
        return ResponseEntity.ok().body(enderecoService.encontrandoEnderecosDaPessoa(idPessoa));
    }

    @PutMapping(value = "/escolhendoEnderecoPrincipal/{id}")
    public ResponseEntity<Endereco> escolhendoEnderecoPrincipal(@PathVariable Long id) {
        enderecoService.findById(id);
        Endereco endereco = enderecoService.atualizandoEnderecoPrincipal(id);
        return ResponseEntity.ok().body(endereco);
    }

    @GetMapping(value = "/endereco/todos")
    public ResponseEntity<List<Endereco>> encontraTodos() {
        List<Endereco> list = enderecoService.findall();
        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "/enderecoPrincipal/{idPessoa}")
    public ResponseEntity<Endereco> retornaEnderecoPrincipal(@PathVariable Long idPessoa) {
        Boolean enderecoPrincipal = true;
        List<Endereco> endereco = enderecoService.encontrandoEnderecosDaPessoa(idPessoa);
        Predicate<? super Endereco> predicate = enderecos -> enderecos.getPrincipal().equals(enderecoPrincipal);
        return ResponseEntity.ok().body(endereco.stream().filter(predicate).findFirst().orElse(null));
    }
}
