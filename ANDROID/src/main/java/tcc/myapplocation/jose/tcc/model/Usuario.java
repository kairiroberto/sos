package tcc.myapplocation.jose.tcc.model;

public class Usuario {

    private int idusuario;
    private String nome_usu;
    private String celular_usu;
    private int fake_new_usu;
    private Boolean ativo_usu;
    private String logradouro_uso;
    private String numero_uso;
    private String bairro_uso;
    private String cidade_uf_uso;
    private String email_uso;

    public Usuario(){

    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNome_usu() {
        return nome_usu;
    }

    public void setNome_usu(String nome_usu) {
        this.nome_usu = nome_usu;
    }

    public String getCelular_usu() {
        return celular_usu;
    }

    public void setCelular_usu(String celular_usu) {
        this.celular_usu = celular_usu;
    }

    public int getFake_new_usu() {
        return fake_new_usu;
    }

    public void setFake_new_usu(int fake_new_usu) {
        this.fake_new_usu = fake_new_usu;
    }

    public Boolean getAtivo_usu() {
        return ativo_usu;
    }

    public void setAtivo_usu(Boolean ativo_usu) {
        this.ativo_usu = ativo_usu;
    }

    public String getLogradouro_uso() {
        return logradouro_uso;
    }

    public void setLogradouro_uso(String logradouro_uso) {
        this.logradouro_uso = logradouro_uso;
    }

    public String getNumero_uso() {
        return numero_uso;
    }

    public void setNumero_uso(String numero_uso) {
        this.numero_uso = numero_uso;
    }

    public String getBairro_uso() {
        return bairro_uso;
    }

    public void setBairro_uso(String bairro_uso) {
        this.bairro_uso = bairro_uso;
    }

    public String getCidade_uf_uso() {
        return cidade_uf_uso;
    }

    public void setCidade_uf_uso(String cidade_uf_uso) {
        this.cidade_uf_uso = cidade_uf_uso;
    }

    public String getEmail_uso() {
        return email_uso;
    }

    public void setEmail_uso(String email_uso) {
        this.email_uso = email_uso;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("id = " + idusuario);
        stringBuffer.append("\nnome = " + nome_usu);
        stringBuffer.append("\ncelular = " + celular_usu);
        stringBuffer.append("\nfakes = " + fake_new_usu);
        stringBuffer.append("\nativo = " + ativo_usu);
        stringBuffer.append("\nrua = " + logradouro_uso);
        stringBuffer.append("\nnúmero = " + numero_uso);
        stringBuffer.append("\nbairro = " + bairro_uso);
        stringBuffer.append("\ncidade = " + cidade_uf_uso);
        stringBuffer.append("\ne-mail = " + email_uso);
        return stringBuffer.toString();
    }
}
