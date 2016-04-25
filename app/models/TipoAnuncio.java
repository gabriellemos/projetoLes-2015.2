package models;

/**
 * Created by Gabriel on 08/04/2016.
 */
public enum TipoAnuncio {
    COMUM, ANIVERSARIO, CASAMENTO;

    /**
     * Created by jordan on 18/04/2016.
     */
    public static class Endereco {

        private String rua;
        private int id;
        private String cidade;
        private String numero;
        private String bairro;
        private String cep;
        private String estado;
        private int confeiteiro;

        public  Endereco(){}

        public String getRua() {
            return rua;
        }

        public void setRua(String rua) {
            this.rua = rua;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public int getConfeiteiro() {
            return this.confeiteiro;
        }

        public void setConfeiteiro(int codigo){this.confeiteiro=codigo; }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }


    }
}
