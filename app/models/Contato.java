package models;

/**
 * Created by jordan on 20/04/2016.
 */
public class Contato {

    private String numero;
    private String CodigoPais;
    private int idContato;
    private String codigoOperadora;
    private String codigoEstado;
    private int donoContato;

    public Contato(){}
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPais() {
        return CodigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        CodigoPais = codigoPais;
    }

    public int getIdContato() {
        return idContato;
    }

    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public int getDonoContato() {
        return donoContato;
    }

    public void setDonoContato(int donoContato) {
        this.donoContato = donoContato;
    }

    public String getCodigoOperadora() {
        return codigoOperadora;
    }

    public void setCodigoOperadora(String codigoOperadora) {
        this.codigoOperadora = codigoOperadora;
    }

    public String toString(){
        return String.format("+%s (%s) %s", getCodigoPais().trim(), getCodigoEstado().trim(), getNumero().trim());
    }
}