package br.com.rafael.peoplemanegement.controllers;

import br.com.rafael.peoplemanegement.controllers.dtos.EnderecoDto;
import br.com.rafael.peoplemanegement.controllers.dtos.PessoaDto;
import br.com.rafael.peoplemanegement.controllers.forms.EnderecoForm;
import br.com.rafael.peoplemanegement.controllers.forms.PessoaForm;
import br.com.rafael.peoplemanegement.models.Endereco;
import br.com.rafael.peoplemanegement.models.Pessoa;
import br.com.rafael.peoplemanegement.repositories.EnderecoRepository;
import br.com.rafael.peoplemanegement.repositories.PessoaRepository;
import br.com.rafael.peoplemanegement.services.EnderecoService;
import br.com.rafael.peoplemanegement.services.PessoaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/pessoas")
public class Controller {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;


    @PostMapping(value = "/cadastrarPessoa")
    public ResponseEntity<?> criarPessoa (@RequestBody PessoaForm pessoaForm){
        if(!pessoaRepository.existsByNameAndAndBirthDate(pessoaForm.getName(), pessoaForm.getBirthDate())){
            Pessoa pessoa = new Pessoa(pessoaForm.getName(), pessoaForm.getBirthDate());
            pessoaRepository.save(pessoa);
            return new ResponseEntity<>(new PessoaDto(pessoa), HttpStatus.CREATED);
        }else return new ResponseEntity<>("Essa pessoa já existe", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        pessoa = pessoaService.update(id, pessoa);
        return ResponseEntity.ok().body(pessoa);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        Pessoa obj = pessoaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> list = pessoaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/addEndereco/pessoaId/{pessoaId}/endereco/{enderecoId}")
    @Transactional
    public ResponseEntity<?> addEndereco (@PathVariable Long pessoaId, @PathVariable Long enderecoId){
        Optional<Pessoa> pessoa = enderecoService.encontraPessoa(pessoaId);
        if (pessoa.isPresent()) {
            Optional<Endereco> endereco = enderecoRepository.findById(enderecoId);
            if(endereco.isPresent()) {
                Endereco endereco1 = endereco.get();
                endereco1.setPessoa(pessoa.get());
                return new ResponseEntity<>(new EnderecoDto(endereco1), HttpStatus.CREATED);
            }else return new ResponseEntity<>("Nenhuma pessoa encontrada", HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>("Nenhum endereco encontrado", HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/cadastrarEndereco")
    public ResponseEntity<?> criarEndereco (@RequestBody EnderecoForm enderecoForm){
        if(!enderecoRepository.existsByLogradouroAndAndNumero(enderecoForm.getLogradouro(), enderecoForm.getNumero())){
            Pessoa pessoa = pessoaService.findById(enderecoForm.getIdPessoa());
            Endereco endereco = new Endereco(enderecoForm.getLogradouro(), enderecoForm.getCep(), enderecoForm.getNumero(), enderecoForm.getCidade(), pessoa);
            enderecoRepository.save(endereco);
            return new ResponseEntity<>(new EnderecoDto(endereco), HttpStatus.CREATED);
        }else return new ResponseEntity<>("Essa pessoa já existe", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/enderecos/{idPessoa}")
    public ResponseEntity<?> listaEnderecosDaPessoa(@PathVariable Long idPessoa){
        Optional<Endereco> enderecos = enderecoRepository.findAllByPessoaId(idPessoa);
        if(enderecos.isPresent()) return new ResponseEntity<>(enderecos.stream().map(EnderecoDto::new).collect(Collectors.toList()), HttpStatus.OK);
        else return new ResponseEntity<>("Nenhum endereço encontrado", HttpStatus.NO_CONTENT);
    }

}
