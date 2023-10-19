package br.com.ifpe.oxefood.modelo.cliente;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.mensagens.EmailService;
import br.com.ifpe.oxefood.util.exception.EntidadeNaoEncontradaException;

@Service
public class ClienteService {

    @Autowired
private UsuarioService usuarioService;

    
    @Autowired
    private EmailService emailService;

    @Autowired
    private ClienteRepository repository;

     @Autowired
    private EnderecoClienteRepository enderecoRepository;
    

    

    @Transactional
    public Cliente save(Cliente cliente) {

        usuarioService.save(cliente.getUsuario());


        cliente.setHabilitado(Boolean.TRUE);
        cliente.setVersao(1L);
        cliente.setDataCriacao(LocalDate.now());
        //return repository.save(cliente);
        Cliente clienteSalvo = repository.save(cliente);
        //emailService.enviarEmailConfirmacaoCadastroCliente(clienteSalvo);

        return clienteSalvo;


    }

    public List<Cliente> findAll() {
  
        return repository.findAll();
    }

    public Cliente findByID(Long id) {

        Optional<Cliente> consulta = repository.findById(id);
  
       if (consulta.isPresent()) {
           return consulta.get();
       } else {
           throw new EntidadeNaoEncontradaException("Cliente", id);
       }


       
    }

    @Transactional
   public void update(Long id, Cliente clienteAlterado) {

      Cliente cliente = repository.findById(id).get();
      cliente.setNome(clienteAlterado.getNome());
      cliente.setDataNascimento(clienteAlterado.getDataNascimento());
      cliente.setCpf(clienteAlterado.getCpf());
      cliente.setFoneCelular(clienteAlterado.getFoneCelular());
      cliente.setFoneFixo(clienteAlterado.getFoneFixo());
	    
      cliente.setVersao(cliente.getVersao() + 1);
      repository.save(cliente);
  }

  @Transactional
   public void delete(Long id) {

       Cliente cliente = repository.findById(id).get();
       cliente.setHabilitado(Boolean.FALSE);
       cliente.setVersao(cliente.getVersao() + 1);

       repository.save(cliente);
   }

   @Transactional
   public EnderecoCliente adicionarEnderecoCliente(Long clienteId, EnderecoCliente endereco) {

       Cliente cliente = this.findByID(clienteId);
      
       //Primeiro salva o EnderecoCliente:

       endereco.setCliente(cliente);
       endereco.setHabilitado(Boolean.TRUE);
       enderecoRepository.save(endereco);
      
       //Depois acrescenta o endereço criado ao cliente e atualiza o cliente:

       List<EnderecoCliente> listaEnderecoCliente = cliente.getEnderecos();
      
       if (listaEnderecoCliente == null) {
           listaEnderecoCliente = new ArrayList<EnderecoCliente>();
       }
      
       listaEnderecoCliente.add(endereco);
       cliente.setEnderecos(listaEnderecoCliente);
       this.save(cliente);
      
       return endereco;
   }

   @Transactional
   public EnderecoCliente atualizarEnderecoCliente(Long id, EnderecoCliente enderecoAlterado) {

       EnderecoCliente endereco = enderecoRepository.findById(id).get();
       endereco.setRua(enderecoAlterado.getRua());
       endereco.setNumero(enderecoAlterado.getNumero());
       endereco.setBairro(enderecoAlterado.getBairro());
       endereco.setCep(enderecoAlterado.getCep());
       endereco.setCidade(enderecoAlterado.getCidade());
       endereco.setEstado(enderecoAlterado.getEstado());
       endereco.setComplemento(enderecoAlterado.getComplemento());

       return enderecoRepository.save(endereco);
   }

   @Transactional
   public void removerEnderecoCliente(Long id) {

       EnderecoCliente endereco = enderecoRepository.findById(id).get();
       endereco.setHabilitado(Boolean.FALSE);
       enderecoRepository.save(endereco);

       Cliente cliente = this.findById(endereco.getCliente().getId());
       cliente.getEnderecos().remove(endereco);
       this.save(cliente);
   }

        private Cliente findById(Long id) {
             return null;
}

public List<Cliente> filtrar(String nome, String cpf) {

        List<Cliente> listaClientes = repository.findAll();

        if ((nome != null && !"".equals(nome))) {
            listaClientes = repository.consultarPorNome(nome);
        } else if ((cpf != null || "".equals(cpf))) {
            listaClientes = repository.consultarPorCPF(cpf);
        } 
        return listaClientes;
    }




}
