package dao;

import models.Anuncio;
import models.Confeiteiro;
import models.TipoAnuncio;
import models.Utils;
import models.dao.Anuncio_DAO;
import models.dao.Confeiteiro_DAO;
import org.apache.maven.wagon.observers.Debug;
import org.junit.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Gabriel on 11/04/2016.
 */
public class AnuncioDAOTest {

    private static Confeiteiro confeiteiro;
    private static ArrayList<Anuncio> anuncios;

    @BeforeClass
    public static void SetUp() {
        anuncios = Anuncio_DAO.getAnuncios();
        System.out.println(anuncios);

        EspereBdRequest();

        confeiteiro = Confeiteiro_DAO.GetConfeiteiros().get(0);
    }

    @Before
    public void Antes() {
        EspereBdRequest();
    }

    @After
    public void Depois() {
        ArrayList<Anuncio> anunciosDepois = Anuncio_DAO.getAnuncios();

        anunciosDepois.removeAll(anuncios);
        for (Anuncio anuncio: anunciosDepois) {
            System.out.println("Blalab:" + anuncio);
            //EspereBdRequest();
            //Anuncio_DAO.removeAnuncio(anuncio);
        }
    }

    private static void EspereBdRequest() {
        //try {
        //    TimeUnit.MILLISECONDS.sleep(250);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
    }

    @Test
    public void TesteCreate() {
        Anuncio anuncio = new Anuncio("Anuncio teste #01", confeiteiro, "Descrição macabra",
                TipoAnuncio.COMUM, 5.0f);
        anuncio.setDataCriacao(Utils.getHoje());
        anuncio.setDataEdicao(Utils.getHoje());

        //Anuncio_DAO.insertAnuncio(anuncio);
//
        //EspereBdRequest();
        //ArrayList<Anuncio> resultAnuncios = Anuncio_DAO.getAnuncios();
//
        //Assert.assertTrue(resultAnuncios.contains(anuncio));
    }

    @Test
    public void TesteVisual() throws Exception {

        ArrayList<Confeiteiro> resultoConfeiteiros = Confeiteiro_DAO.GetConfeiteiros();

        for (Confeiteiro confeiteiro: resultoConfeiteiros) {
            System.out.println(confeiteiro);
        }

        TipoAnuncio tipo = TipoAnuncio.COMUM;
        String str = tipo.name();
        Assert.assertEquals(tipo, TipoAnuncio.valueOf(str));

        ArrayList<Anuncio> resultoAnuncios = Anuncio_DAO.getAnuncios();

        for (Anuncio anuncio: resultoAnuncios) {
            System.out.println(anuncio);
        }

    }
}
