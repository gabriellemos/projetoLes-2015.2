package models;

import org.apache.commons.lang.NullArgumentException;
import java.util.HashSet;
import javax.management.openmbean.KeyAlreadyExistsException;

/**
 * Classe que modela um Confeiteiro do sistema
 * @author Aline Costa
 *
 */
public class Confeiteiro {
	
	private String nome;
	private String endereco;
	private String email;
	private String contato;

	private final String STRING_VAZIA = "";
	
	private String id;
	
	private HashSet<Anuncio> meusAnuncios;
	
	/**
	 * Construtor padr�o
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
			throw new IllegalArgumentException("Argumento 'nome' recebendo valores inválidos");
		this.nome = nome;
	}

	/**
	 * Recupera o endere�o do confeiteiro
	 * @return o endere�o do confeiteiro
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Modifica o endereço do Confeiteiro, caso o mesmo seja válido
	 * @param endereco novo enderço do Confeiteiro
     */
	public void setEndereco(String endereco) {
		if(endereco == null || endereco.trim().equals(STRING_VAZIA))
			throw new IllegalArgumentException("Argumento 'endereço' recebendo valores inválidos");
		this.endereco = endereco;
	}

	/**
	 * Recupera o email do confeiteiro
	 * @return o email do confeiteiro
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modifica o email do Confeiteiro, caso o mesmo seja válido
	 * @param email novo email do Confeiteiro
     */
	public void setEmail(String email) {
		if(email == null || email.trim().equals(STRING_VAZIA))
			throw new IllegalArgumentException("Argumento 'email' recebendo valores inválidos");
		this.email = email;
	}

	/**
	 * Recupera o contato do confeiteiro
	 * @return o contato do confeiteiro
	 */
	public String getContato() {
		return contato;
	}

	/**
	 * Modifica o contato do Confeiteiro, caso o mesmo seja válido
	 * @param contato novo contato do Confeiteiro
     */
	public void setContato(String contato) {
		if(contato == null || contato.trim().equals(STRING_VAZIA))
			throw new IllegalArgumentException("Argumento 'endereço' recebendo valores inválidos");
		this.contato = contato;
	}
	
	 /**
     * Recupera o id do confeiteiro
     * @return id do confeiteiro
     */
    public String getId() {
        return id;
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

}
