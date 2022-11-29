package com.solbs.unov3.entities.enums;

public enum StatusAmostra {
    Analise_Finalizada(1),
    Em_Analise(2),
    Aguardando_Analise(3),
    Amostra_em_Falta(4);

    private int code;

    StatusAmostra(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StatusAmostra valor(int code){
        for(StatusAmostra value: StatusAmostra.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Código de status inválido");
    }
}
