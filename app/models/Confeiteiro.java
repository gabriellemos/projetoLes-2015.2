import java.util.ArrayList;
import java.util.List;

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
	
	private String id;
	
	private List<Anuncio> meusAnuncios;
	
	/**
	 * Construtor padr�o
	 * @param nome o nome do Confeiteiro
	 */
	public Confeiteiro(String nome) {
		setNome(nome);
		this.meusAnuncios = new ArrayList<>();
	}
	
	/**
	 * Recupera o nome do confeiteiro
	 * @return o nome do confeiteiro
	 */
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome == null)
        	throw new NullArgumentException("O nome setado para o Confeiteiro � nulo");
		this.nome = nome;
	}

	/**
	 * Recupera o endere�o do confeiteiro
	 * @return o endere�o do confeiteiro
	 */
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		if(endereco == null)
        	throw new NullArgumentException("O endere�o setado para o Confeiteiro � nulo");
		this.endereco = endereco;
	}

	/**
	 * Recupera o email do confeiteiro
	 * @return o email do confeiteiro
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email == null)
        	throw new NullArgumentException("O email setado para o Confeiteiro � nulo");
		this.email = email;
	}

	/**
	 * Recupera o contato do confeiteiro
	 * @return o contato do confeiteiro
	 */
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		if(contato == null)
        	throw new NullArgumentException("O contato setado para o Confeiteiro � nulo");
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
     * Modifica a id do confeiteiro
     * @param id do confeiteiro
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Adiciona um an�ncio na lista de an�ncios do confeiteiro, caso o mesmo ainda n�o exista
     * @param anuncio o anuncio a ser adicionado
     */
	public void addAnuncio(Anuncio anuncio) {
		if(anuncio == null)
        	throw new NullArgumentException("O anuncio a ser adicionado tem valor nulo.");
		if(!(meusAnuncios.contains(anuncio))) {
            this.meusAnuncios.add(anuncio);
        } else {
            throw new KeyAlreadyExistsException("O an�ncio j� existe.");
        }
	}
	
	/**
	 * Recupera um an�ncio do confeiteiro
	 * @param nomeAnuncio o nome do an�ncio a ser pesquisado
	 * @return o anuncio cujo nome coincide com a pesquisa, caso ele exista
	 */
	public Anuncio getAnuncio(String nomeAnuncio) {
		if(name == null)
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
	public List<Anuncio> getAnuncios() {
		return this.meusAnuncios;
	}

}
