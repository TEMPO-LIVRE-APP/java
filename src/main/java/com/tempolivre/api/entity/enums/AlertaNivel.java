package com.tempolivre.api.entity.enums;

public enum AlertaNivel {

    PERIGO("perigo"),
    ATENCAO("atencao"),
    INFORMATIVO("informativo");

    private String nivel;

    AlertaNivel(String nivel){
        this.nivel = nivel;
    }

    public String getNivel(){
        return nivel;
    }
}
