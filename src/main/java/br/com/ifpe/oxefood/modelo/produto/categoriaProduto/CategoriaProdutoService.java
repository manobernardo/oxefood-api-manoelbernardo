package br.com.ifpe.oxefood.modelo.produto.categoriaProduto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaProdutoService {
    
    @Autowired
    private CategoriaProdutoRepository repository;

    @Transactional
    public CategoriaProduto save(CategoriaProduto catecoriaProduto) {
        catecoriaProduto.setHabilitado(Boolean.TRUE);
        catecoriaProduto.setVersao(1L);
        catecoriaProduto.setDataCriacao(LocalDate.now());
        return repository.save(catecoriaProduto);
    }

    public List<CategoriaProduto> findAll() {
  
        return repository.findAll();
    }

    public CategoriaProduto findByID(Long id) {

        return repository.findById(id).get();
    }

     @Transactional
   public void update(Long id, CategoriaProduto categoriaProdutoAlterado) {

      CategoriaProduto categoriaProduto = repository.findById(id).get();
      categoriaProduto.setDescricao(categoriaProdutoAlterado.getDescricao());
      
	    
      categoriaProduto.setVersao(categoriaProduto.getVersao() + 1);
      repository.save(categoriaProduto);
  }

  @Transactional
   public void delete(Long id) {

       CategoriaProduto catecoriaProduto = repository.findById(id).get();
       catecoriaProduto.setHabilitado(Boolean.FALSE);
       catecoriaProduto.setVersao(catecoriaProduto.getVersao() + 1);

       repository.save(catecoriaProduto);
   }
}
