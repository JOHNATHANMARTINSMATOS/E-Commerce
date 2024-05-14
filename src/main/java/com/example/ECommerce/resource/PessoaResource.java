package com.example.ECommerce.resource;

import com.example.ECommerce.interfaces.IResource;
import com.example.ECommerce.model.Pessoa;
import com.example.ECommerce.service.PessoaService;
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
@RequestMapping(value = "/api/pessoa")
@Tag(name = "pessoa", description = "API para operações relacionadas à Pessoa")
public class PessoaResource implements IResource<Pessoa,Integer> {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
             produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "Criar uma pessoa",
            description = "Método responsável por criar pessoa no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso",
                    content = @Content(schema = @Schema(implementation = Pessoa.class))),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "304", description = "Não modificado"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @Override
    public Pessoa creat(@RequestBody Pessoa entity) {
        log.info("Metodo criar Pessoa acessado!");
        log.debug("Pessoa creat || Valor recebido:{}",entity);
        return pessoaService.creat(entity);
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )

    @Operation(
            summary = "buscar uma pessoa",
            description = "Método responsável por busca pessoa no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso",
                    content = @Content(schema = @Schema(implementation = Pessoa.class))),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "404", description = "Não modificado"),
            @ApiResponse(responseCode = "500", description = "Não encontrado")
    })
    @Override
    public List<Pessoa> read() {
        log.info("Metodo Listar Pessoas!");
        List<Pessoa> pessoas = pessoaService.read();
        log.debug("Consulta retornou {} ",pessoas.size());
        return pessoas;
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Buscar pessoa por ID",
            description = "Método responsável por buscar uma pessoa específica pelo seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso",
                    content = @Content(schema = @Schema(implementation = Pessoa.class))),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @Override
    public Pessoa read(Integer id) {
        log.info("Metodo buscar pessoa individual!");
        log.debug("Consuta para obter pessoa com id:{}",id);
        return pessoaService.read(id);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "Atualizar pessoa",
            description = "Método responsável por atualizar uma pessoa no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = Pessoa.class))),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @Override
    public Pessoa update(Integer id, Pessoa entity) {
        log.info("Metodo alterar acessado!");
        log.debug("Pessoa alterada: id - {}, Entidade {}:",id,entity);
        return pessoaService.update(id,entity);
    }

    @DeleteMapping(
            value = "/{id}"
    )
    @Operation(
            summary = "Deletar pessoa",
            description = "Método responsável por deletar uma pessoa do sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa deletada com sucesso"),
            @ApiResponse(responseCode = "303", description = "Redirecionamento"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @Override
    public void delete(@PathVariable Integer id) {
        log.info("Metodo deletar pessoa!");
        log.debug("Pessoa deletada:{}",id);
        pessoaService.delete(id);

    }
}
