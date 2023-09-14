package br.com.ifpe.oxefood.modelo.produto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    @Transactional
    public Produto save(Produto produto) {
        produto.setHabilitado(Boolean.TRUE);
        produto.setVersao(1L);
        produto.setDataCriacao(LocalDate.now());
        return repository.save(produto);
    }

    public List<Produto> findAll() {
  
        return repository.findAll();
    }

    public Produto findByID(Long id) {

        return repository.findById(id).get();
    }
}
