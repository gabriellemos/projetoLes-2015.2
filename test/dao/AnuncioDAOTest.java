package dao;

import models.Anuncio;
import models.Confeiteiro;
import models.TipoAnuncio;
import models.dao.Anuncio_DAO;
import models.dao.Confeiteiro_DAO;
import org.junit.*;

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
            EspereBdRequest();
            Anuncio_DAO.removeAnuncio(anuncio);
        }
    }

    private static void EspereBdRequest() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TesteCreate() {
        Anuncio anuncio = new Anuncio("Anuncio teste #01", confeiteiro, "Descrição macabra",
                TipoAnuncio.COMUM, 5.0f);

        Anuncio_DAO.insertAnuncio(anuncio);
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
