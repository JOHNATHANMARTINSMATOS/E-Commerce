package com.example.ECommerce.resource;

import com.example.ECommerce.interfaces.IResource;
import com.example.ECommerce.model.Email;
import com.example.ECommerce.service.EmailServirce;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(value = "/api/email")
@Tag(name = "email", description = "Api para email da pessoa")
public class EmailResource implements IResource<Email,Integer> {

    @Autowired
    private EmailServirce emailServirce;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "criar email",
            description = "Metodo para criar email da pessoa"

    )
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Email criado com sucesso",
            content = @Content(schema = @Schema(implementation = Email.class))),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "304",description = "Não modificado"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
        }
    )

    @Override
    public Email creat(@RequestBody Email entity) {

        log.info("metodo para criar email");
        log.debug("Emeil criado {}",entity);
        return emailServirce.creat(entity);
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )

    @Operation(
            summary = "Buscar email",
            description = "Metodo responsavel por buscar email da pessoa"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "email encontrado",
                    content = @Content(schema = @Schema(implementation = Email.class))),
                    @ApiResponse(responseCode = "303", description =  "Redirecionamento"),
                    @ApiResponse(responseCode = "404", description = "Não modificado"),
                    @ApiResponse(responseCode = "500", description = "Não encontrado")

            }
    )
    @Override
    public List<Email> read() {
        log.info("Metodo listar email");
        List<Email> emails = emailServirce.read();
        log.debug("Consulta retornou {}",emails.size());
        return emails;
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Buscar emails por ID",
            description = "Método responsável por buscar um email específica pelo seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "email encontrada com sucesso",
                    content = @Content(schema = @Schema(implementation =Email.class))),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "404", description = "Email não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @Override
    public Email read(@PathVariable Integer id) {
        log.info("Metodo para buscar email por ID");
        log.debug("Email retornda para ID{}:",id);
        return emailServirce.read(id);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "Atualizar emails",
            description = "Método responsável por atualizar uma pessoa no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = Email.class))),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "404", description = "Email não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @Override
    public Email update(Integer id, Email entity) {

        log.info("Metodo alterar acessado!");
        log.debug("Email alterada: id - {}, Entidade {}:",id,entity);
        return emailServirce.update(id,entity);
    }

    @DeleteMapping(
            value = "/{id}"
    )
    @Operation(
            summary = "Deletar email",
            description = "Método responsável por deletar uma pessoa do sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Email deletada com sucesso"),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "404", description = "Email não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @Override
    public void delete(@PathVariable Integer id) {

        log.info("Metodo deletar email!");
        log.debug("Email deletada:{}",id);
        emailServirce.delete(id);
    }
}
