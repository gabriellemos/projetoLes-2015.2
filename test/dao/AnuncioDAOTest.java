package dao;

import models.Anuncio;
import models.Confeiteiro;
import models.TipoAnuncio;
import models.dao.Anuncio_DAO;
import models.dao.Confeiteiro_DAO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Gabriel on 11/04/2016.
 */
public class AnuncioDAOTest {

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
