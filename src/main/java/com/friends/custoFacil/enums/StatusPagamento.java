package com.friends.custoFacil.enums;

public enum StatusPagamento {

    PENDENTE_APROVACAO(1),
    EM_ANDAMENTO(2),
    CONCLUIDO(3);

    private int id;

    StatusPagamento(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public static StatusPagamento fromId(long idCusto){
        for (StatusPagamento statusPagamento: StatusPagamento.values()){
            if (statusPagamento.getId() == idCusto){
                return statusPagamento;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + idCusto);
    }

}
