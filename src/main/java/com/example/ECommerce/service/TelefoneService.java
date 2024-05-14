package com.example.ECommerce.service;

import com.example.ECommerce.interfaces.IService;
import com.example.ECommerce.model.Telefone;
import com.example.ECommerce.repository.TelefoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TelefoneService implements IService<Telefone,Integer> {
    @Autowired
    private TelefoneRepository telefoneRepository;

    @Override
    public Telefone creat(Telefone entity) {
        log.info("Criando um novo telefone: {}", entity);
        return telefoneRepository.save(entity);
    }

    @Override
    public List<Telefone> read() {
        log.info("Listando todos os telefones.");
        return telefoneRepository.findAll();
    }

    @Override
    public Telefone read(Integer id) {
        log.info("Buscando telefone pelo ID: {}", id);
        Optional<Telefone> telefone = telefoneRepository.findById(id);
        return telefone.orElse(null);
    }

    @Override
    public Telefone update(Integer id, Telefone entity) {
        log.info("Atualizando telefone com ID: {}", id);
        Optional<Telefone> telefoneOptional = telefoneRepository.findById(id);
        if (telefoneOptional.isPresent()) {
            Telefone telefone = telefoneOptional.get();
            telefone.setDdd(entity.getDdd());
            telefone.setNumero(entity.getNumero());
            return telefoneRepository.save(telefone);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        log.info("Deletando telefone com ID: {}", id);
        telefoneRepository.deleteById(id);
    }
}
