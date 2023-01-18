package br.com.rafael.peoplemanagement.services;

import br.com.rafael.peoplemanegement.models.Endereco;
import br.com.rafael.peoplemanegement.models.Pessoa;
import br.com.rafael.peoplemanegement.repositories.EnderecoRepository;
import br.com.rafael.peoplemanegement.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Endereco> findall() {
        return enderecoRepository.findAll();
    }

    public Endereco findById(Long id) {
        Optional<Endereco> obj = enderecoRepository.findById(id);
        return obj.get();
    }

    public Optional<Pessoa> encontraPessoa(Long pessoaId) {
        if(pessoaRepository.existsById(pessoaId)){
            return pessoaRepository.findById(pessoaId);
        } else return Optional.empty();
    }
}
