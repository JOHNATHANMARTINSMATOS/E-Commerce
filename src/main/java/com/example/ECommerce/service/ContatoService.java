package com.example.ECommerce.service;

import com.example.ECommerce.interfaces.IService;
import com.example.ECommerce.model.Contato;
import com.example.ECommerce.repository.ContatoRepository;
import com.example.ECommerce.resource.ContatoResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContatoService implements IService<Contato,Integer> {

    @Autowired
    private ContatoRepository contatoRepository;

    @Override
    public Contato creat(Contato entity) {
        log.info("Criando contato: {}", entity);
        return contatoRepository.save(entity);
    }

    @Override
    public List<Contato> read() {
        log.info("Listando todos os contatos");
        return contatoRepository.findAll();
    }

    @Override
    public Contato read(Integer id) {
        log.info("Buscando contato pelo ID: {}", id);
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        return contatoOptional.orElse(null);
    }

    @Override
    public Contato update(Integer id, Contato entity) {
        log.info("Atualizando contato com ID: {}", id);
        Optional<Contato> optionalContato = contatoRepository.findById(id);

        if (optionalContato.isPresent()) {
            Contato existingContato = optionalContato.get();
            existingContato.setDataInicio(entity.getDataInicio());
            existingContato.setDataFim(entity.getDataFim());
            existingContato.setEmails(entity.getEmails());
            existingContato.setEnderecos(entity.getEnderecos());
            existingContato.setTelefones(entity.getTelefones());

            return contatoRepository.save(existingContato);
        } else {
            log.warn("Contato com ID {} não encontrado.", id);
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        log.info("Deletando contato com ID: {}", id);
        contatoRepository.deleteById(id);
    }
}
