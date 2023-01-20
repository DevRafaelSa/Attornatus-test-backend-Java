package br.com.rafael.peoplemanagement.services;

import br.com.rafael.peoplemanagement.models.Endereco;
import br.com.rafael.peoplemanagement.models.Pessoa;
import br.com.rafael.peoplemanagement.repositories.EnderecoRepository;
import br.com.rafael.peoplemanagement.repositories.PessoaRepository;
import br.com.rafael.peoplemanagement.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Endereco> encontrandoEnderecosDaPessoa (Long idPessoa) {
        try {
            Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
            return pessoa.get().getEnderecos().stream().toList();
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(idPessoa);
        }
    }

    public Optional<Pessoa> encontraPessoa(Long pessoaId) {
        if(pessoaRepository.existsById(pessoaId)){
            return Optional.of(pessoaRepository.findById(pessoaId).get());
        } else return Optional.empty();
    }

    public Endereco atualizandoEnderecoPrincipal(Long id) {
        try {
            Endereco entity = enderecoRepository.getById(id);
            escolhePrincipal(entity);
            return enderecoRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void escolhePrincipal(Endereco entity) {
        entity.setPrincipal(Boolean.TRUE);
    }
}
