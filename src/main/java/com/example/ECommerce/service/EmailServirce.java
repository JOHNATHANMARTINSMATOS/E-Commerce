package com.example.ECommerce.service;

import com.example.ECommerce.interfaces.IService;
import com.example.ECommerce.model.Email;
import com.example.ECommerce.repository.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmailServirce implements IService<Email,Integer> {

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public Email creat(Email entity) {
        log.info("Criando novo email: {}", entity);
        return emailRepository.save(entity);
    }

    @Override
    public List<Email> read() {
        log.info("Listando todos os emails.");
        return emailRepository.findAll();
    }

    @Override
    public Email read(Integer id) {
        log.info("Buscando email pelo ID: {}", id);
        Optional<Email> optionalEmail = emailRepository.findById(id);
        return optionalEmail.orElse(null);
    }

    @Override
    public Email update(Integer id, Email entity) {
        log.info("Atualizando email com ID: {}", id);
        Optional<Email> optionalEmail = emailRepository.findById(id);

        if (optionalEmail.isPresent()) {
            Email existingEmail = optionalEmail.get();
            existingEmail.setEnderecoDeEmail(entity.getEnderecoDeEmail());
            existingEmail.setDataInicio(entity.getDataInicio());
            existingEmail.setDataFim(entity.getDataFim());
            existingEmail.setContato(entity.getContato());

            return emailRepository.save(existingEmail);
        } else {
            log.warn("Email com ID {} não encontrado.", id);
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        log.info("Deletando email com ID: {}", id);
        emailRepository.deleteById(id);
    }
}
