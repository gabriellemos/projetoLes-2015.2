package models;

import org.apache.commons.lang.NullArgumentException;

import java.util.ArrayList;
import java.util.HashSet;
import javax.management.openmbean.KeyAlreadyExistsException;

/**
 * Classe que modela um Confeiteiro do sistema
 * @author Aline Costa
 *
 */
public class Confeiteiro {
	
	private String nome;
	private ArrayList<TipoAnuncio.Endereco> enderecos;
	private ArrayList<Utils.Email> emails;
	private ArrayList<Contato> contatos;

	private  String idFacebook;

	private final String STRING_VAZIA = "";
	
	private int id;
	
	private HashSet<Anuncio> meusAnuncios;


	/**
	 * Construtor padrão
	 */
	public Confeiteiro() {}

	/**
	 * Construtor padrão
	 * @param nome o nome do Confeiteiro
	 */
	public Confeiteiro(String nome) {
		setNome(nome);
		this.meusAnuncios = new HashSet<>();
	}
	
	/**
	 * Recupera o nome do confeiteiro
	 * @return o nome do confeiteiro
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Modifica o nome do Confeiteiro, caso o mesmo seja válido
	 * @param nome novo nome do Confeiteiro
     */
	public void setNome(String nome) {
		if (nome == null || nome.trim().equals(STRING_VAZIA))
			throw new IllegalArgumentException("Argumento 'Nome' recebendo valores inválidos");
		this.nome = nome;
	}

	/**
	 * Recupera o endere�o do confeiteiro
	 * @return o endere�o do confeiteiro
	 */

	public ArrayList<TipoAnuncio.Endereco> getEnderecos() {
		return this.enderecos;
	}

	/**
	 * Modifica o endereço do Confeiteiro, caso o mesmo seja válido
	 * @param endereco novo enderço do Confeiteiro
     */


	/**
	 * Recupera o email do confeiteiro
	 * @return o email do confeiteiro
	 */
	public ArrayList<Utils.Email> getEmail() {
		return emails;
	}

	/**
	 * Modifica o email do Confeiteiro, caso o mesmo seja válido
	 * @param email novo email do Confeiteiro
     */


	/**
	 * Recupera o contato do confeiteiro
	 * @return o contato do confeiteiro
	 */
	public ArrayList<Contato> getContato() {
		return contatos;
	}

	/**
	 * Modifica o contato do Confeiteiro, caso o mesmo seja válido
	 * @param contato novo contato do Confeiteiro
     */
	
	 /**
     * Recupera o id do confeiteiro
     * @return id do confeiteiro
     */
    public int getId() {
        return id;
    }

	public void setId(int id) {
		this.id = id;
	}

    /**
     * Adiciona um an�ncio na lista de an�ncios do confeiteiro, caso o mesmo ainda n�o exista
     * @param anuncio o anuncio a ser adicionado
     */
	public void addAnuncio(Anuncio anuncio) {
		if(anuncio == null) {
			throw new NullArgumentException("O anuncio a ser adicionado tem valor nulo.");
		}

		// HashSet.add retorna true se o elemento não tiver presente antes da inserção
		if (!this.meusAnuncios.add(anuncio)) {
			throw new KeyAlreadyExistsException("O an�ncio j� existe.");
        }
	}
	
	/**
	 * Recupera um an�ncio do confeiteiro
	 * @param nomeAnuncio o nome do an�ncio a ser pesquisado
	 * @return o anuncio cujo nome coincide com a pesquisa, caso ele exista
	 */
	public Anuncio getAnuncio(String nomeAnuncio) {
		if(nomeAnuncio == null)
			throw new NullArgumentException("O nome dado para procurar por uma Atividade � nulo.");
		for (Anuncio anuncio : this.meusAnuncios) {
			// TODO descomentar isso quando implementar getName() em Anuncio.
            // if (nomeAnuncio.toLowerCase().equals(anuncio.getName().toLowerCase())) 
                return anuncio;
        }
		return null;
	}
	
	/**
	 * Recupera todos os an�ncios do confeiteiro
	 * @return os an�ncios do confeiteiro
	 */
	public HashSet<Anuncio> getAnuncios() {
		return this.meusAnuncios;
	}

	public String getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(String idFacebook) {
		if(idFacebook == null || idFacebook.trim().equals(STRING_VAZIA))
			throw new IllegalArgumentException("Argumento 'IdFacebok' recebendo valores inválidos");
		this.idFacebook = idFacebook;
	}

	public void AddContato (Contato contato){
		this.contatos.add(contato);
	}

	public void AddEndereco (TipoAnuncio.Endereco endereco){
		this.enderecos.add(endereco);
	}
	public void AddEmail (Utils.Email email){
		this.emails.add(email);
	}

	/**
     * Created by jordan on 20/04/2016.
     */
    public static class Contato {

        String numero;
        String CodigoPais;
        int idContato;
        String codigoOperadora;
        String codigoEstado;
        int donoContato;

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

    }
}
