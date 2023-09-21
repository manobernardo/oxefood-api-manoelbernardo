package br.com.ifpe.oxefood.api.cliente;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin

public class ClienteController {
    @Autowired
   private ClienteService clienteService;

    
   @ApiOperation(value = "Serviço responsável por salvar um cliente no sistema.")
   @PostMapping
   public ResponseEntity<Cliente> save(@RequestBody ClienteRequest request) {

       Cliente cliente = clienteService.save(request.build());
       return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
   }

    @ApiOperation(value = "Serviço responsável por listar um cliente no sistema.")
    @GetMapping
    public List<Cliente> findAll() {
  
        return clienteService.findAll();
    }

    @ApiOperation(value = "Serviço responsável por listar por id um cliente no sistema.")
    @GetMapping("/{id}")
    public Cliente obterPorID(@PathVariable Long id) {

        return clienteService.findByID(id);
     }

    @ApiOperation(value = "Serviço responsável por atualizar um cliente no sistema.")
    @PutMapping("/{id}")
   public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody ClienteRequest request) {

       clienteService.update(id, request.build());
       return ResponseEntity.ok().build();
   }

   @ApiOperation(value = "Serviço responsável por deletar um cliente no sistema.")
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       clienteService.delete(id);
       return ResponseEntity.ok().build();
   }

   @PostMapping("/endereco/{clienteId}")
   public ResponseEntity<EnderecoCliente> adicionarEnderecoCliente(@PathVariable("clienteId") Long clienteId, @RequestBody @Valid EnderecoClienteRequest request) {

       EnderecoCliente endereco = clienteService.adicionarEnderecoCliente(clienteId, request.build());
       return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.CREATED);
   }

   @PutMapping("/endereco/{enderecoId}")
   public ResponseEntity<EnderecoCliente> atualizarEnderecoCliente(@PathVariable("enderecoId") Long enderecoId, @RequestBody EnderecoClienteRequest request) {

       EnderecoCliente endereco = clienteService.atualizarEnderecoCliente(enderecoId, request.build());
       return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.OK);
   }
  
   @DeleteMapping("/endereco/{enderecoId}")
   public ResponseEntity<Void> removerEnderecoCliente(@PathVariable("enderecoId") Long enderecoId) {

       clienteService.removerEnderecoCliente(enderecoId);
       return ResponseEntity.noContent().build();
   }



    
}
