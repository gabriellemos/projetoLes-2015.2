package unity;

import models.Anuncio;
import models.Confeiteiro;
import models.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Teste de unidade para a classe de modelo Confeiteiro.
 *
 * @author Gabriel
 */
public class AnuncioTeste {


    @Test
    public void TestaConstrutorDefault() {
        Confeiteiro conf = new Confeiteiro();
        Anuncio anuncio = new Anuncio("Sem titulo", conf);

        GregorianCalendar ontem = Utils.getHoje();
        ontem.add(Calendar.DATE, -1);

        GregorianCalendar hoje = Utils.getHoje();

        GregorianCalendar amanha = Utils.getHoje();
        amanha.add(Calendar.DATE, -1);

        // Data passado
        Assert.assertTrue(Utils.dataValida(ontem, hoje));

        // Data Futuro
        Assert.assertFalse(Utils.dataValida(hoje, amanha));
    }

}
