package br.com.ifpe.oxefood.api.produto.categoriaProduto;

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

import br.com.ifpe.oxefood.modelo.produto.categoriaProduto.CategoriaProduto;
import br.com.ifpe.oxefood.modelo.produto.categoriaProduto.CategoriaProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/produto/categoriaProduto")
@CrossOrigin

public class CategoriaProdutoController {
    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @PostMapping
    public ResponseEntity<CategoriaProduto> save(@RequestBody CategoriaProdutoRequest request) {

        CategoriaProduto categoriaProduto = categoriaProdutoService.save(request.build());
        return new ResponseEntity<CategoriaProduto>(categoriaProduto, HttpStatus.CREATED);
    }

   @GetMapping
    public List<CategoriaProduto> findAll() {
  
        return categoriaProdutoService.findAll();
    }

    @ApiOperation(value = "Serviço responsável por listar por id um produto no sistema.")
    @GetMapping("/{id}")
    public CategoriaProduto obterPorID(@PathVariable Long id) {

        return categoriaProdutoService.findByID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProduto> update(@PathVariable("id") Long id,
            @RequestBody CategoriaProdutoRequest request) {

        categoriaProdutoService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        categoriaProdutoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
