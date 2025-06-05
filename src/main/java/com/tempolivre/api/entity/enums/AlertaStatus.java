package com.tempolivre.api.entity.enums;

public enum AlertaStatus {

    ATIVO("ativo"),
    EXPIRADO("expirado"),
    RESOLVIDO("resolvido");

    private String status;

    AlertaStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
