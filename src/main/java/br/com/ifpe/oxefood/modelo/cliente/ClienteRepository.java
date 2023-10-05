package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    @Query(value = "SELECT n FROM Cliente n WHERE n.nome like %:nome% ORDER BY n.nome")
    List<Cliente> consultarPorNome(String nome);

    @Query(value = "SELECT c FROM Cliente c WHERE c.cpf like %:cpf% ORDER BY c.cpf")
    List<Cliente> consultarPorCPF(String cpf);


}
