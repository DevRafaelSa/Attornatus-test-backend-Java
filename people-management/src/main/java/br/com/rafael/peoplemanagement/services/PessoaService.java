package br.com.rafael.peoplemanagement.services;

import br.com.rafael.peoplemanagement.models.Pessoa;
import br.com.rafael.peoplemanagement.repositories.PessoaRepository;
import br.com.rafael.peoplemanagement.services.exceptions.DataBaseException;
import br.com.rafael.peoplemanagement.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> findall() {
        return repository.findAll();
    }

    public Pessoa findById(Long id) {
        Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
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

    public Pessoa update(Long id, Pessoa obj) {
        try {
            Pessoa entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Pessoa entity, Pessoa obj) {
        entity.setName(obj.getName());
        entity.setBirthDate(obj.getBirthDate());
    }

}
