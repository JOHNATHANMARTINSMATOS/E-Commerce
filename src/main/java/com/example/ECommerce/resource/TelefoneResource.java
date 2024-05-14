package com.example.ECommerce.resource;

import com.example.ECommerce.interfaces.IResource;
import com.example.ECommerce.model.Telefone;
import com.example.ECommerce.service.TelefoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(value = "/api/telefone")
@Tag(name ="telefone", description = "API para operações relacionadas ao telefone da pessoa")
public class TelefoneResource implements IResource<Telefone,Integer> {

    private TelefoneService telefoneService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "criar um telefone",
            description = "metodo responsavel por criar telefone da pessoa"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",description = "Telefone criado com sucesso!",
                            content = @Content(schema = @Schema(implementation = Telefone.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro interno Servidor")
            }
    )
    @Override
    public Telefone creat(@RequestBody Telefone entity) {
        log.info("Metodo criar telefone");
        log.debug("Telefone criado || telefone recebido: {}", entity);
        return telefoneService.creat(entity);
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "listar telefones",
            description = "metodo responsavel por listar telefones da pessoa"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "Telefones listados com sucesso!",
                            content = @Content(schema = @Schema(implementation = Telefone.class))),
                    @ApiResponse(responseCode = "404", description = "Nenhum telefone encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno Servidor")
            }
    )
    @Override
    public List<Telefone> read() {
        log.info("Metodo listar telefones");
        return telefoneService.read();
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "buscar telefone por ID",
            description = "metodo responsavel por buscar um telefone pelo ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "Telefone encontrado com sucesso!",
                            content = @Content(schema = @Schema(implementation = Telefone.class))),
                    @ApiResponse(responseCode = "404", description = "Telefone não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno Servidor")
            }
    )
    @Override
    public Telefone read(@PathVariable Integer id) {
        log.info("Metodo buscar telefone pelo ID: {}", id);
        return telefoneService.read(id);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "atualizar telefone",
            description = "metodo responsavel por atualizar um telefone existente"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "Telefone atualizado com sucesso!",
                            content = @Content(schema = @Schema(implementation = Telefone.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "404", description = "Telefone não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno Servidor")
            }
    )
    @Override
    public Telefone update(@PathVariable Integer id, @RequestBody Telefone entity) {
        log.info("Metodo atualizar telefone com ID: {}", id);
        return telefoneService.update(id, entity);
    }

    @DeleteMapping(
            value = "/{id}"
    )
    @Operation(
            summary = "deletar telefone",
            description = "metodo responsavel por deletar um telefone pelo ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204",description = "Telefone deletado com sucesso!"),
                    @ApiResponse(responseCode = "404", description = "Telefone não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno Servidor")
            }
    )
    @Override
    public void delete(@PathVariable Integer id) {
        log.info("Metodo deletar telefone com ID: {}", id);
        telefoneService.delete(id);
    }
}
