package com.example.ECommerce.resource;

import com.example.ECommerce.interfaces.IResource;
import com.example.ECommerce.model.Contato;
import com.example.ECommerce.model.Pessoa;
import com.example.ECommerce.repository.ContatoRepository;
import com.example.ECommerce.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(value ="/api/contato")
@Tag(name = "contato", description = "Api relacionado ao contato da pessoa")
public class ContatoResource implements IResource<Contato,Integer> {

    @Autowired
    private ContatoService contatoService;
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "Criar um contato",
            description = "Método responsável por criar contato no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contato criada com sucesso",
                    content = @Content(schema = @Schema(implementation = Contato.class))),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "304", description = "Não modificado"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @Override
    public Contato creat(@RequestBody Contato entity) {
        log.info("Metodo para criar contato");
        log.debug("Pessoa creat || valor recebido{}",entity);
        return contatoService.creat(entity);
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "buscar contatos",
            description = "Método responsável por busca contatos no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "contatos encontrada com sucesso",
                    content = @Content(schema = @Schema(implementation = Contato.class))),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "404", description = "Não modificado"),
            @ApiResponse(responseCode = "500", description = "Não encontrado")
    })
    @Override
    public List<Contato> read() {
        log.info("Metodo Listar contato");
        List<Contato> contato = contatoService.read();
        log.debug("Consulta retornou{}",contato.size());
        return contato;
    }
    @Operation(
            summary = "Buscar contato por ID",
            description = "Método responsável por buscar um contato específica pelo seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "contato encontrada com sucesso",
                    content = @Content(schema = @Schema(implementation = Contato.class))),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "404", description = "contato não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public Contato read(@PathVariable Integer id) {
        log.info("Metodo para buscar contatos pelo id");
        log.debug("Consulta por ID retornou o contato com ID:{}",id);
        return contatoService.read(id);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "Atualizar contato",
            description = "Método responsável por atualizar um contato no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "contato atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = Contato.class))),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "404", description = "contato não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @Override
    public Contato update(Integer id, Contato entity) {
        log.info("Metodo para atualizar contato");
        log.debug("Contato atualizado apra o ID:{}, contato{}:",id,entity);
        return contatoService.update(id,entity);
    }

    @DeleteMapping(
            value = "/{id}"
    )
    @Operation(
            summary = "Deletar contato",
            description = "Método responsável por deletar um contato do sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contato deletada com sucesso"),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @Override
    public void delete(@PathVariable Integer id) {
        log.info("Metodo deletar Contato!");
        log.debug("Contato deletato:{}",id);
    }
}
