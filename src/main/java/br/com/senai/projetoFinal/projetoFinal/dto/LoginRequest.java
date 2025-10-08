package br.com.senai.projetoFinal.projetoFinal.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String senha;
}
