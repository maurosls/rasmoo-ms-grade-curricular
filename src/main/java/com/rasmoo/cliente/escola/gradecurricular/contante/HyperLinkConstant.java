package com.rasmoo.cliente.escola.gradecurricular.contante;

import lombok.Getter;

@Getter
public enum HyperLinkConstant {
    ATUALIZAR("UPDATE"),
    EXCLUIR("DELETE"),
    LISTAR("LISTAR"),
    CONSULTAR("CONSULTAR");

    private final String valor;

    HyperLinkConstant(String valor) {this.valor = valor;}
}
