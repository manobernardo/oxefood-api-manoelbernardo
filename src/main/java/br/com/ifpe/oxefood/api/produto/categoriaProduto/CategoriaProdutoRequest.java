package br.com.ifpe.oxefood.api.produto.categoriaProduto;

import br.com.ifpe.oxefood.modelo.produto.categoriaProduto.CategoriaProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CategoriaProdutoRequest {
     private String descricao;



    public CategoriaProduto build() {

        return CategoriaProduto.builder()
                .descricao(descricao)
                .build();
    }
}
