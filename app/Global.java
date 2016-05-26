import models.*;
import models.dao.Anuncio_DAO;
import models.dao.Confeiteiro_DAO;
import models.dao.Contato_DAO;
import models.dao.Endereco_DAO;
import models.dao.Tabelas.ScriptSQL;
import play.Application;
import play.GlobalSettings;
import play.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Global extends GlobalSettings {

	/**
	 * Executa operações antes do server da aplicação ligar.
	 */
	@Override
	public void onStart(Application app) {
		Logger.info(">>> Aplicacao wgmt inicializada...");
		Logger.info(">>> Hora = " + LocalDateTime.now());

		restartBD();

		createBruna();
    createRobert();
    createJoana();

		System.out.println("BD configurado com sucesso");
	}

	private void createBruna() {
		Confeiteiro eu = new Confeiteiro();
		String id_face = "21204363705092751";

		int id_conf = -1;
		eu.setNome("Bruna Sousa");
		eu.setIdFacebook(id_face);

		try {
			Confeiteiro_DAO.insertConfeiteiro(eu);
			id_conf = Confeiteiro_DAO.getConfeiteiro(id_face).getId();
			eu.setId(id_conf);
		} catch (Exception e){
			Logger.error(e.getMessage());
		}

		Endereco end = new Endereco();
		end.setCidade("Campina Grande");
		end.setEstado("PB");
		end.setRua("Rua Doutor Francisco Lima Neto");
		end.setNumero("117");
		end.setBairro("Universitário");
		end.setCep("58429-060");
		end.setConfeiteiro(id_conf);

		Contato cont = new Contato();
		cont.setCodigoPais("55");
		cont.setCodigoEstado("83");
		cont.setNumero("3333-5213");
		cont.setDonoContato(id_conf);

		try {
			Endereco_DAO.insertEndereco(end);
			Contato_DAO.insertContato(cont);
		} catch (Exception e){
			Logger.error(e.getMessage());
		}

		Anuncio ad1 = new Anuncio();
		ad1.setCriador(eu);
		ad1.setDataCriacao(Utils.getHoje());
		ad1.setDataEdicao(Utils.getHoje());
		ad1.setDescricao("Este bolo pode acabar com você e seus amigos! Compre logo antes que acabe, e tenha essa" +
				" delícia lhe esperando em casa!");
		ad1.setDisponibilidade(true);
		ad1.setPreco("" + 30);
		ad1.setTipoAnuncio(TipoAnuncio.COMUM);
		ad1.setTitulo("Gordura Latejante");

		Anuncio ad2 = new Anuncio();
		ad2.setCriador(eu);
		ad2.setDataCriacao(Utils.getHoje());
		ad2.setDataEdicao(Utils.getHoje());
		ad2.setDescricao("Este bolo é puro e não faz mal a uma mosca. Compre logo o seu, e tenha um jardim mais florido.");
		ad2.setDisponibilidade(true);
		ad2.setPreco("" + 25);
		ad2.setTipoAnuncio(TipoAnuncio.COMUM);
		ad2.setTitulo("Bolo de Tudo que há de bom");

		Anuncio_DAO.insertAnuncio(ad1);
		Anuncio_DAO.insertAnuncio(ad2);
	}

	private void createRobert() {
		Confeiteiro eu = new Confeiteiro();
		String id_face = "13401764705392750";

		int id_conf = -1;
		eu.setNome("Panificadoras Severo");
		eu.setIdFacebook(id_face);

		try {
			Confeiteiro_DAO.insertConfeiteiro(eu);
			id_conf = Confeiteiro_DAO.getConfeiteiro(id_face).getId();
			eu.setId(id_conf);
		} catch (Exception e){
			Logger.error(e.getMessage());
		}

		Endereco end = new Endereco();
		end.setCidade("Campina Grande");
		end.setEstado("PB");
		end.setRua("Rua José de Alencar");
		end.setNumero("178");
		end.setBairro("Prata");
		end.setCep("53210-080");
		end.setConfeiteiro(id_conf);

		Contato cont = new Contato();
		cont.setCodigoPais("55");
		cont.setCodigoEstado("83");
		cont.setNumero("3321-2021");
		cont.setDonoContato(id_conf);

		try {
			Endereco_DAO.insertEndereco(end);
			Contato_DAO.insertContato(cont);
		} catch (Exception e){
			Logger.error(e.getMessage());
		}

		Anuncio ad1 = new Anuncio();
		ad1.setCriador(eu);
		ad1.setDataCriacao(Utils.getHoje());
		ad1.setDataEdicao(Utils.getHoje());
		ad1.setDescricao("O sonho de qualquer aniversário. Leve um desses para sua festa de aniversário, e saiba o que é" +
                " comer um bolo de paixão!");
		ad1.setDisponibilidade(true);
		ad1.setPreco("" + 65);
		ad1.setTipoAnuncio(TipoAnuncio.ANIVERSARIO);
		ad1.setTitulo("Red Velvet");

		Anuncio ad2 = new Anuncio();
		ad2.setCriador(eu);
		ad2.setDataCriacao(Utils.getHoje());
		ad2.setDataEdicao(Utils.getHoje());
		ad2.setDescricao("Coma dos mais puros e tradicionais bolos da alemanha! Uma torta com muito sabor e prazer a cada" +
                " mordida.");
		ad2.setDisponibilidade(true);
		ad2.setPreco("" + 45);
		ad2.setTipoAnuncio(TipoAnuncio.COMUM);
		ad2.setTitulo("Torta Alemã");

		Anuncio_DAO.insertAnuncio(ad1);
		Anuncio_DAO.insertAnuncio(ad2);
	}

    private void createJoana() {
        Confeiteiro eu = new Confeiteiro();
				String id_face = "43403764205342767";

				int id_conf = -1;
				eu.setNome("Joana Cakes");
				eu.setIdFacebook(id_face);

				try {
					Confeiteiro_DAO.insertConfeiteiro(eu);
					id_conf = Confeiteiro_DAO.getConfeiteiro(id_face).getId();
					eu.setId(id_conf);
				} catch (Exception e){
					Logger.error(e.getMessage());
				}

        Endereco end = new Endereco();
        end.setCidade("João Pessoa");
        end.setEstado("PB");
        end.setRua("Rua Epitácio Pessoa");
        end.setNumero("311");
        end.setBairro("Bancários");
        end.setCep("51310-060");
        end.setConfeiteiro(id_conf);

        Contato cont = new Contato();
        cont.setCodigoPais("55");
        cont.setCodigoEstado("83");
        cont.setNumero("3431-2121");
        cont.setDonoContato(id_conf);

        try {
            Endereco_DAO.insertEndereco(end);
            Contato_DAO.insertContato(cont);
        } catch (Exception e){
            Logger.error(e.getMessage());
        }

        Anuncio ad1 = new Anuncio();
        ad1.setCriador(eu);
        ad1.setDataCriacao(Utils.getHoje());
        ad1.setDataEdicao(Utils.getHoje());
        ad1.setDescricao("Esse chocolate é um perigo! Uma mordida e você se desfaz toda!");
        ad1.setDisponibilidade(true);
        ad1.setPreco("" + 85);
        ad1.setTipoAnuncio(TipoAnuncio.ANIVERSARIO);
        ad1.setTitulo("Nego do Bom");

        Anuncio ad2 = new Anuncio();
        ad2.setCriador(eu);
        ad2.setDataCriacao(Utils.getHoje());
        ad2.setDataEdicao(Utils.getHoje());
        ad2.setDescricao("Este bolo serve para as mais refinadas festas. Com uma mordida você impressionará qualquer" +
                " cliente!");
        ad2.setDisponibilidade(true);
        ad2.setPreco("" + 125);
        ad2.setTipoAnuncio(TipoAnuncio.CASAMENTO);
        ad2.setTitulo("Bolo Executivo");

        Anuncio_DAO.insertAnuncio(ad1);
        Anuncio_DAO.insertAnuncio(ad2);
    }

	private void restartBD() {
		Statement declaracao = null;
		java.sql.Connection conexao = null;

		try {
			conexao = models.dao.Connection.getConnection();
			declaracao = conexao.createStatement();

			// Drop tables
			declaracao.executeUpdate("DROP TABLE Anuncio");
			declaracao.executeUpdate("DROP TABLE Contato");
			declaracao.executeUpdate("DROP TABLE Email");
			declaracao.executeUpdate("DROP TABLE Endereco");
			declaracao.executeUpdate("DROP TABLE Confeiteiro");

			// Create tables
			declaracao.executeUpdate(ScriptSQL.getCreateConfeiteiro());
			declaracao.executeUpdate(ScriptSQL.getCreateContato());
			declaracao.executeUpdate(ScriptSQL.getCreateEmail());
			declaracao.executeUpdate(ScriptSQL.getCreateEndereco());
			declaracao.executeUpdate(ScriptSQL.getCreateAnuncio());

			declaracao.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Logger.info("BD resetado com sucesso");
	}

	/**
	 * Executa operações após o server da aplicação desligar.
	 */
    @Override
	public void onStop(Application app) {
        Logger.info(">>> Aplicacao wgmt finalizando...");
	}

}
