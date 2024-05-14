package com.example.ECommerce.resource;

import com.example.ECommerce.interfaces.IResource;
import com.example.ECommerce.model.Endereco;
import com.example.ECommerce.repository.EnderecoRepository;
import com.example.ECommerce.service.EnderecoService;
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
@RequestMapping(value = "/api/endereco")
@Tag(name ="endereco", description = "Api para operaçoes relacionadas ao endereço da pessoa")
public class EnderecoResource implements IResource<Endereco,Integer> {

    @Autowired
    private EnderecoService enderecoService;


    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "criar um endereço",
            description = "metodo responsavel por criar endereço da pessoa"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",description = "Endereço criado com sucesso!",
                            content = @Content(schema = @Schema(implementation = Endereco.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro interno Servidor")
            }
    )
    @Override
    public Endereco creat(@RequestBody Endereco entity) {
        log.info("Metodo criar endereço");
        log.debug("Endereço criado || endereço recebido: {}", entity);
        return enderecoService.creat(entity);
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "listar endereços",
            description = "metodo responsavel por listar endereços da pessoa"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "Endereços listados com sucesso!",
                            content = @Content(schema = @Schema(implementation = Endereco.class))),
                    @ApiResponse(responseCode = "404", description = "Nenhum endereço encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno Servidor")
            }
    )
    @Override
    public List<Endereco> read() {
        log.info("Metodo listar endereços");
        return enderecoService.read();
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "buscar endereço por ID",
            description = "metodo responsavel por buscar um endereço pelo ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "Endereço encontrado com sucesso!",
                            content = @Content(schema = @Schema(implementation = Endereco.class))),
                    @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno Servidor")
            }
    )
    @Override
    public Endereco read(@PathVariable Integer id) {
        log.info("Metodo buscar endereço pelo ID: {}", id);
        return enderecoService.read(id);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "atualizar endereço",
            description = "metodo responsavel por atualizar um endereço existente"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "Endereço atualizado com sucesso!",
                            content = @Content(schema = @Schema(implementation = Endereco.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno Servidor")
            }
    )
    @Override
    public Endereco update(@PathVariable Integer id, @RequestBody Endereco entity) {
        log.info("Metodo atualizar endereço com ID: {}", id);
        return enderecoService.update(id, entity);
    }

    @DeleteMapping(
            value = "/{id}"
    )
    @Operation(
            summary = "deletar endereço",
            description = "metodo responsavel por deletar um endereço pelo ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204",description = "Endereço deletado com sucesso!"),
                    @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno Servidor")
            }
    )
    @Override
    public void delete(@PathVariable Integer id) {
        log.info("Metodo deletar endereço com ID: {}", id);
        enderecoService.delete(id);
    }
}
