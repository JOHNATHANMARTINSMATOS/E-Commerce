package com.example.ECommerce.service;

import com.example.ECommerce.interfaces.IService;
import com.example.ECommerce.model.Endereco;
import com.example.ECommerce.repository.EnderecoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EnderecoService implements IService<Endereco,Integer> {

   @Autowired
   private EnderecoRepository enderecoRepository;
    @Override
    public Endereco creat(Endereco entity) {
        log.info("Criando um novo endereco: {}", entity);
        return enderecoRepository.save(entity);
    }

    @Override
    public List<Endereco> read() {
        log.info("Listando todos os enderecos.");
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco read(Integer id) {
        log.info("Buscando endereco pelo ID: {}", id);
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.orElse(null);
    }

    @Override
    public Endereco update(Integer id, Endereco entity) {
        log.info("Atualizando endereco com ID: {}", id);
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()) {
            Endereco endereco = enderecoOptional.get();
            endereco.setLogradouro(entity.getLogradouro());
            endereco.setDataInicio(entity.getDataFim());
            endereco.setDataFim(entity.getDataFim());
            return enderecoRepository.save(endereco);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        log.info("Deletando endereco com ID: {}", id);
        enderecoRepository.deleteById(id);
    }
}
