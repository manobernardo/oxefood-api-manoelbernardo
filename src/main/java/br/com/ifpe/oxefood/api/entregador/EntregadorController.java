package br.com.ifpe.oxefood.api.entregador;

import java.util.List;

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

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import br.com.ifpe.oxefood.modelo.entregador.EntregadorService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/entregador")
@CrossOrigin

public class EntregadorController {
    @Autowired
   private EntregadorService entregadorService;

    
   @ApiOperation(value = "Serviço responsável por atualizar um entregador no sistema.")
   @PostMapping
   public ResponseEntity<Entregador> save(@RequestBody EntregadorRequest request) {

       Entregador entregador = entregadorService.save(request.build());
       return new ResponseEntity<Entregador>(entregador, HttpStatus.CREATED);
   }

   @ApiOperation(value = "Serviço responsável por listar um entregador no sistema.")
   @GetMapping
    public List<Entregador> findAll() {
  
        return entregadorService.findAll();
    }

    @ApiOperation(value = "Serviço responsável por listar por id um entregador no sistema.")
    @GetMapping("/{id}")
    public Entregador obterPorID(@PathVariable Long id) {

        return entregadorService.findByID(id);
    }

    @ApiOperation(value = "Serviço responsável por atualizar por id um entregador no sistema.")
      @PutMapping("/{id}")
   public ResponseEntity<Entregador> update(@PathVariable("id") Long id, @RequestBody EntregadorRequest request) {

       entregadorService.update(id, request.build());
       return ResponseEntity.ok().build();
   }
    
   @ApiOperation(value = "Serviço responsável por deletar um entregador no sistema.")
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       entregadorService.delete(id);
       return ResponseEntity.ok().build();
   }
   
}
