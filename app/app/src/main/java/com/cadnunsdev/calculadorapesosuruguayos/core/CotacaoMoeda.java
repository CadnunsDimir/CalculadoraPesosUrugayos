package com.cadnunsdev.calculadorapesosuruguayos.core;

import java.util.Date;

/**
 * Created by Tiago Silva on 05/12/2016.
 */

public class CotacaoMoeda {
    String moedaOrigem;
    String moedaDestino;
    Date horaDaAtualizacao;
    String cotacao;
    private String sitioWeb;

    public String getMoedaOrigem() {
        return moedaOrigem;
    }

    public void setMoedaOrigem(String moedaOrigem) {
        this.moedaOrigem = moedaOrigem;
    }

    public String getMoedaDestino() {
        return moedaDestino;
    }

    public void setMoedaDestino(String moedaDestino) {
        this.moedaDestino = moedaDestino;
    }

    public Date getHoraDaAtualizacao() {
        return horaDaAtualizacao;
    }

    public void setHoraDaAtualizacao(Date horaDaAtualizacao) {
        this.horaDaAtualizacao = horaDaAtualizacao;
    }

    public String getCotacao() {
        return cotacao;
    }

    public void setCotacao(String cotacao) {
        this.cotacao = cotacao;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }
}
