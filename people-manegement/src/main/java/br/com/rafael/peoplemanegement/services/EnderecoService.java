package br.com.rafael.peoplemanegement.services;

import br.com.rafael.peoplemanegement.models.Endereco;
import br.com.rafael.peoplemanegement.models.Pessoa;
import br.com.rafael.peoplemanegement.repositories.EnderecoRepository;
import br.com.rafael.peoplemanegement.repositories.PessoaRepository;
import br.com.rafael.peoplemanegement.services.exceptions.DataBaseException;
import br.com.rafael.peoplemanegement.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco encontraId(Long id){
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return endereco.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
