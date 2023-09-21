package br.com.ifpe.oxefood.api.produto;

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

import br.com.ifpe.oxefood.modelo.produto.Produto;
import br.com.ifpe.oxefood.modelo.produto.ProdutoService;
import br.com.ifpe.oxefood.modelo.produto.categoriaProduto.CategoriaProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin

public class ProdutoController {

    @Autowired
   private ProdutoService produtoService;

   @Autowired
   private CategoriaProdutoService categoriaProdutoService;


   @ApiOperation(value = "Serviço responsável por salvar um produto no sistema.")
   @PostMapping
   public ResponseEntity<Produto> save(@RequestBody ProdutoRequest request) {

    Produto produtoNovo = request.build();
    produtoNovo.setCategoria(categoriaProdutoService.findByID(request.getIdCategoria()));
    Produto produto = produtoService.save(produtoNovo);
    return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);

       /*Produto produto = produtoService.save(request.build());
       return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);*/
   }

   @ApiOperation(value = "Serviço responsável por listar um produto no sistema.")
   @GetMapping
    public List<Produto> findAll() {
  
        return produtoService.findAll();
    }

    @ApiOperation(value = "Serviço responsável por listar por id um produto no sistema.")
    @GetMapping("/{id}")
    public Produto obterPorID(@PathVariable Long id) {

        return produtoService.findByID(id);
    }

    @ApiOperation(value = "Serviço responsável por atualizar um produto no sistema.")
    @PutMapping("/{id}")
   public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody ProdutoRequest request) {

        Produto produto = request.build();
       produto.setCategoria(categoriaProdutoService.findByID(request.getIdCategoria()));
       produtoService.update(id, produto);

       /*produtoService.update(id, request.build());*/
       return ResponseEntity.ok().build();
   }
   @ApiOperation(value = "Serviço responsável por deletar um produto no sistema.")
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       produtoService.delete(id);
       return ResponseEntity.ok().build();
   }
}
