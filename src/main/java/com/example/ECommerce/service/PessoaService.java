package com.example.ECommerce.service;

import com.example.ECommerce.interfaces.IService;
import com.example.ECommerce.model.Pessoa;
import com.example.ECommerce.repository.PessoaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class PessoaService implements IService<Pessoa, Integer> {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa creat(Pessoa entity) {
        log.info("Criando uma nova Pessoa: {}", entity);
        Pessoa savedPessoa = pessoaRepository.save(entity);
        log.debug("Pessoa criada: {}", savedPessoa);
        return pessoaRepository.save(entity);
    }

    @Override
    public List<Pessoa> read() {
        log.info("Buscando todas as Pessoas");
        List<Pessoa> pessoas = pessoaRepository.findAll();
        log.debug("Recuperadas {} pessoas", pessoas.size());
        return pessoas;
    }

    @Override
    public Pessoa read(Integer id) {
        log.info("Buscando Pessoa pelo ID: {}", id);
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        Pessoa fetchedPessoa = optionalPessoa.orElse(null);

        if (fetchedPessoa != null) {
            log.debug("Pessoa recuperada: {}", fetchedPessoa);
        } else {
            log.warn("Pessoa com ID {} não encontrada", id);
        }

        return fetchedPessoa;

    }

    @Override
    public Pessoa update(Integer id, Pessoa entity) {
        log.info("Atualizando Pessoa com ID: {}", id);
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);

        if (optionalPessoa.isPresent()) {
            Pessoa pessoa = optionalPessoa.get();
            pessoa.setNome(entity.getNome());
            pessoa.setContatos(entity.getContatos());
            pessoa.setDataInicio(entity.getDataInicio());
            pessoa.setDataFim(entity.getDataFim());

            Pessoa updatedPessoa = pessoaRepository.save(pessoa);
            log.debug("Pessoa atualizada: {}", updatedPessoa);
            return updatedPessoa;
        } else {
            log.warn("Pessoa com ID {} não encontrada", id);
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        log.info("Excluindo Pessoa com ID: {}", id);
        pessoaRepository.deleteById(id);
        log.debug("Pessoa excluída com ID: {}", id);

    }
}
