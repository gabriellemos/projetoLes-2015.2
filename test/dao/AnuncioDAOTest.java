package dao;

import models.Anuncio;
import models.Confeiteiro;
import models.TipoAnuncio;
import models.dao.Anuncio_DAO;
import models.dao.Confeiteiro_DAO;
import models.dao.Connection;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 * Created by Gabriel on 11/04/2016.
 */
public class AnuncioDAOTest {

    @Test
    public void TesteVisual() throws Exception {

//        Confeiteiro_DAO.insertConfeiteiro("Bolo Owner", "bolo_owner@bolo", "Bolovile, Bolôpolis",
//                "BoloPhone:bolo", "facebolo7");

        ArrayList<Confeiteiro> resultoConfeiteiros = Confeiteiro_DAO.GetConfeiteiros();

        for (Confeiteiro confeiteiro: resultoConfeiteiros) {
            System.out.println(confeiteiro);
        }

//        Anuncio_DAO.insertAnuncio("Sem titulo", "Sem descrição", new GregorianCalendar(),
//                new GregorianCalendar(), 0.0f, resultoConfeiteiros.get(0).getId(),
//                TipoAnuncio.COMUM.name());

        TipoAnuncio tipo = TipoAnuncio.COMUM;
        String str = tipo.name();
        Assert.assertEquals(tipo, TipoAnuncio.valueOf(str));

        ArrayList<Anuncio> resultoAnuncios = Anuncio_DAO.getAnuncios();

        for (Anuncio anuncio: resultoAnuncios) {
            System.out.println(anuncio);
        }

    }

}
