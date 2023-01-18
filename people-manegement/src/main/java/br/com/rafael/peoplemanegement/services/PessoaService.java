package br.com.rafael.peoplemanegement.services;

import br.com.rafael.peoplemanegement.models.Pessoa;
import br.com.rafael.peoplemanegement.repositories.PessoaRepository;
import br.com.rafael.peoplemanegement.services.exceptions.DataBaseException;
import br.com.rafael.peoplemanegement.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa encontraId(Long id){
        Optional<Pessoa> pessoa = repository.findById(id);
        if (pessoa.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return pessoa.orElseThrow(() -> new ResourceNotFoundException(id));
    }


    public void deletar(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

}
