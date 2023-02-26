package com.pdrsoft.pdrfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    MENSAGEM_NAO_LEGIVEL("/mensagem-nao-legivel", "Mensagem não legível"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação da regra de negócio");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://pdrfood.com.br" + path;
        this.title = title;
    }

}
