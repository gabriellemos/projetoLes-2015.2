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
	private ArrayList<Endereco> enderecos;
	private ArrayList<Email> emails;
	private ArrayList<Contato> contatos;

	private  String idFacebook;

	private final String STRING_VAZIA = "";
	
	private int id;
	
	private HashSet<Anuncio> meusAnuncios;


	/**
	 * Construtor padrão
	 */
	public Confeiteiro() {
		this.meusAnuncios = new HashSet<>();
		this.emails = new ArrayList<Email>();
		this.contatos = new ArrayList<Contato>();
		this.enderecos = new ArrayList<Endereco>();
	}

	/**
	 * Construtor padrão
	 * @param nome o nome do Confeiteiro
	 */
	public Confeiteiro(String nome) {
		super();
		setNome(nome);
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

	public ArrayList<Endereco> getEnderecos() {
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
	public ArrayList<Email> getEmail() {
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

	public void AddEndereco (Endereco endereco){
		this.enderecos.add(endereco);
	}
	public void AddEmail (Email email){
		this.emails.add(email);
	}

	@Override
	public boolean equals(Object obj) {
		boolean returnResult = true;
		if (!(obj instanceof Confeiteiro)) {
			return false;
		}

		Confeiteiro that = (Confeiteiro) obj;
		returnResult &= this.getNome().equals(that.getNome());
		//returnResult &= this.getEnderecos().equals(that.getEnderecos());
		//returnResult &= this.getEmail().equals(that.getEmail());
		//returnResult &= this.getContato().equals(that.getContato());
		//returnResult &= this.getIdFacebook().equals(that.getIdFacebook());

		return returnResult;
	}

}
