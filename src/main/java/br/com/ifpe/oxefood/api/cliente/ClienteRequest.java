package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;
import java.util.Arrays;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ClienteRequest {

    @NotNull(message = "O Nome é de preenchimento obrigatório")
   @NotBlank(message = "O Nome é de preenchimento obrigatório")
   
   @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;


    @NotNull(message = "O CPF é de preenchimento obrigatório")
   @NotBlank(message = "O CPF é de preenchimento obrigatório")
   @CPF
    private String cpf;

    @Length(min = 8, max = 20, message = "O campo Fone celular tem que ter entre {min} e {max} caracteres")
    private String foneCelular;

    @Length(min = 8, max = 20, message = "O campo Fone fixo tem que ter entre {min} e {max} caracteres")
    private String foneFixo;

    @NotBlank(message = "O e-mail é de preenchimento obrigatório")
    @Email
    private String email;


    @NotBlank(message = "A senha é de preenchimento obrigatório")
    private String password;



    public Cliente buildCliente() {

        return Cliente.builder()
                .usuario(buildUsuario())
                .nome(nome)
                .dataNascimento(dataNascimento)
                .cpf(cpf)
                .foneCelular(foneCelular)
                .foneFixo(foneFixo)
                .email(email)
                .build();
    }
    public Usuario buildUsuario() {
	
	return Usuario.builder()
		.username(email)
		.password(password)
		.roles(Arrays.asList(Usuario.ROLE_CLIENTE))
		.build();
    }


}
