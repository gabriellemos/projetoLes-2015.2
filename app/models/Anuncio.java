package models;

import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Classe que modela um Anuncio do sistema
 */
public class Anuncio {

    private int id;

    private String titulo;
    private String descricao;

    private GregorianCalendar dataEdicao;
    private GregorianCalendar dataCriacao;

    private float preco;
    private Confeiteiro criador;
    private TipoAnuncio tipoAnuncio;
    private boolean disponibilidade;
    private  final float valorDefault= 0.0f;

    private final String ARGUMENTO_INVALIDO = "Argumento '%s' recebendo valores inválidos";

    /**
     * Construtor padrão
     */
    public Anuncio() {
        setDisponibilidade(true);
    }

    /**
     * Construtor mínimo onde são recebidos os parâmetros 'Título' e 'Confeiteiro' e os outros
     * valores são criados como DEFAULT.
     *
     * @param titulo Título do anúncio.
     * @param confeiteiro Confeiteiro associado.
     */
    public Anuncio(String titulo, Confeiteiro confeiteiro) {
        this(titulo, confeiteiro, "Sem descrição", TipoAnuncio.COMUM, 0.0f);
    }

    /**
     * Construtor onde todos os campos são recebidos como parâmetro.
     *
     * @param titulo        Título do anúncio.
     * @param confeiteiro   Confeiteiro associado.
     * @param descricao     Descrição do anúncio.
     * @param tipoAnuncio   Tipo do anúncio.
     * @param preco         Preço do anúncio.
     */
    public Anuncio(String titulo, Confeiteiro confeiteiro, String descricao,
                   TipoAnuncio tipoAnuncio, float preco) {
        setTitulo(titulo);
        setCriador(confeiteiro);
        setDescricao(descricao);
        setTipoAnuncio(tipoAnuncio);
        setPreco(preco);

        setDataCriacao(Utils.getHoje());
    }

    public String getTitulo() {
        return titulo;
    }

    /**
     * Modifica o titulo do Anúncio, caso o mesmo seja válido (Não nulo, não vazio ou composto
     * apenas por espaços vazios).
     *
     * @param titulo novo titulo do Anúncio
     */
    public void setTitulo(String titulo) {
        if (!Utils.checaStringValida(titulo)) {
            throw new IllegalArgumentException(String.format(ARGUMENTO_INVALIDO, "Título"));
        }
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Modifica o descricao do Anúncio, caso seja válido (Não nulo, não vazio ou composto apenas
     * por espaços vazios).
     *
     * @param descricao nova descricao do Anúncio
     */
    public void setDescricao(String descricao) {
        if (!Utils.checaStringValida(descricao)) {
            throw new IllegalArgumentException(String.format(ARGUMENTO_INVALIDO, "Descrição"));
        }
        this.descricao = descricao;
    }

    public GregorianCalendar getDataEdicao() {
        return dataEdicao;
    }

    /**
     * Modifica o dataEdicao do Anúncio, caso seja válida (Igual ou posterior ao dia atual).
     *
     * @param dataEdicao nova dataEdicao do Anúncio
     */
    public void setDataEdicao(GregorianCalendar dataEdicao) {
        /*
         * TODO: Modificar validação para invalidar data no futuro. A data no passado dá problema na
         * hora de recuperar as datas no BD cuja marioria é anterior ao dia de hoje.
         */
        //if (!Utils.dataValida(dataEdicao)) {
        //    throw new IllegalArgumentException(String.format(ARGUMENTO_INVALIDO, "Data de edição"));
        //}
        this.dataEdicao = dataEdicao;
    }

    public GregorianCalendar getDataCriacao() {
        return dataCriacao;
    }

    /**
     * Modifica o dataCriacao do Anúncio, caso seja válida (Igual ou posterior ao dia atual).
     *
     * @param dataCriacao nova dataCriacao do Anúncio
     */
    public void setDataCriacao(GregorianCalendar dataCriacao) {
        /*
         * TODO: Modificar validação para invalidar data no futuro. A data no passado dá problema na
         * hora de recuperar as datas no BD cuja marioria é anterior ao dia de hoje.
         */
        if (!Utils.dataValida(dataCriacao, Utils.getHoje())) {
            throw new IllegalArgumentException(String.format(ARGUMENTO_INVALIDO, "Data de criação"));
        }
        this.dataCriacao = dataCriacao;
    }

    public float getPreco() {
        return preco;
    }

    /**
     * Modifica o preco do Anúncio, caso seja válido (Maior ou igual a zero).
     *
     * @param preco novo preco do Anúncio
     */
    public void setPreco(float preco) {
        if (preco < valorDefault) {
            throw new IllegalArgumentException(String.format(ARGUMENTO_INVALIDO, "Preço"));
        }
        this.preco = preco;
    }

    public Confeiteiro getCriador() {
        return criador;
    }

    /**
     * Modifica o criador do Anúncio, caso não seja nulo.
     *
     * @param criador novo criador do Anúncio
     */
    public void setCriador(Confeiteiro criador) {
        if (criador == null) {
            throw new IllegalArgumentException(String.format(ARGUMENTO_INVALIDO, "Criador"));
        }
        this.criador = criador;
    }

    public TipoAnuncio getTipoAnuncio() {
        return tipoAnuncio;
    }

    /**
     * Modifica o tipoAnuncio do Anúncio, caso não seja nulo.
     *
     * @param tipoAnuncio novo tipoAnuncio do Anúncio
     */
    public void setTipoAnuncio(TipoAnuncio tipoAnuncio) {
        if (tipoAnuncio == null) {
            throw new IllegalArgumentException(String.format(ARGUMENTO_INVALIDO, "Tipo de Anúncio"));
        }
        this.tipoAnuncio = tipoAnuncio;
    }

    public boolean getDisponibilidade (){return this.disponibilidade;}

    public void setDisponibilidade(boolean dispor){
        if(this.disponibilidade == dispor){
            throw new IllegalArgumentException(String.format(ARGUMENTO_INVALIDO,"Disponibilidade sendo utilizada" ));
        }
        this.disponibilidade= dispor;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.titulo;
    }

    @Override
    public boolean equals(Object obj) {
        boolean returnResult = true;
        if (!(obj instanceof Anuncio)) {
            return false;
        }

        Anuncio that = (Anuncio) obj;
        returnResult &= this.getTitulo().equals(that.getTitulo());
        returnResult &= this.getDescricao().equals(that.getDescricao());
        returnResult &= this.getDataEdicao().equals(that.getDataEdicao());
        returnResult &= this.getDataCriacao().equals(that.getDataCriacao());
        returnResult &= this.getPreco() == (that.getPreco());
        returnResult &= this.getCriador().equals(that.getCriador());
        returnResult &= this.getTipoAnuncio().equals(that.getTipoAnuncio());

        return returnResult;
    }
}
