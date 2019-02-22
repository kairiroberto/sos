package com.roberto.myapplication.model;

public class Sos {

    private int idsos;
    private String dataSos;
    private String horaSos;
    private int usuario;
    private int ocorrencia;
    private Double latitudeSos;
    private Double longitudeSos;
    private Boolean atendido_sos;
    private int vizualizadoSos;
    private String descricao_sos;

    public Sos() {

    }

    public int getIdsos() {
        return idsos;
    }

    public void setIdsos(int idsos) {
        this.idsos = idsos;
    }

    public String getDataSos() {
        return dataSos;
    }

    public void setDataSos(String dataSos) {
        this.dataSos = dataSos;
    }

    public String getHoraSos() {
        return horaSos;
    }

    public void setHoraSos(String horaSos) {
        this.horaSos = horaSos;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(int ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Double getLatitudeSos() {
        return latitudeSos;
    }

    public void setLatitudeSos(Double latitudeSos) {
        this.latitudeSos = latitudeSos;
    }

    public Double getLongitudeSos() {
        return longitudeSos;
    }

    public void setLongitudeSos(Double longitudeSos) {
        this.longitudeSos = longitudeSos;
    }

    public Boolean getAtendido_sos() {
        return atendido_sos;
    }

    public void setAtendido_sos(Boolean atendido_sos) {
        this.atendido_sos = atendido_sos;
    }

    public int getVizualizadoSos() {
        return vizualizadoSos;
    }

    public void setVizualizadoSos(int vizualizadoSos) {
        this.vizualizadoSos = vizualizadoSos;
    }

    public String getDescricao_sos() {
        return descricao_sos;
    }

    public void setDescricao_sos(String descricao_sos) {
        this.descricao_sos = descricao_sos;
    }

}
