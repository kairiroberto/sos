package tcc.myapplocation.jose.tcc.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class Sos {

    private int idsos;
    private String dataSos;
    private String horaSos;
    private int usuario;
    private int ocorrencia;
    private Double latitudeSos;
    private Double longitudeSos;
    private int atendidoSos;
    private int vizualizadoSos;
    private int canceladoSos;
    private String descricaoSos;

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

    public int getAtendidoSos() {
        return atendidoSos;
    }

    public void setAtendidoSos(int atendidoSos) {
        this.atendidoSos = atendidoSos;
    }

    public int getVizualizadoSos() {
        return vizualizadoSos;
    }

    public void setVizualizadoSos(int vizualizadoSos) {
        this.vizualizadoSos = vizualizadoSos;
    }

    public int getCanceladoSos() {
        return canceladoSos;
    }

    public void setCanceladoSos(int canceladoSos) {
        this.canceladoSos = canceladoSos;
    }

    public String getDescricaoSos() {
        return descricaoSos;
    }

    public void setDescricaoSos(String descricaoSos) {
        this.descricaoSos = descricaoSos;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\nid = " + idsos);
        stringBuffer.append("\ndata = " + dataSos);
        stringBuffer.append("\nhora = " + horaSos);
        stringBuffer.append("\nusuário = " + usuario);
        stringBuffer.append("\nocorrencia = " + ocorrencia);
        stringBuffer.append("\nlatitude = " + latitudeSos);
        stringBuffer.append("\nlongitude = " + longitudeSos);
        stringBuffer.append("\natendido = " + atendidoSos);
        stringBuffer.append("\ncancelado = " + canceladoSos);
        stringBuffer.append("\nvazualizado = " + vizualizadoSos);
        stringBuffer.append("\ndescrição = " + descricaoSos + "\n");
        return stringBuffer.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            Sos sos = (Sos) o;
            return idsos == sos.idsos;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(idsos);
    }

}
