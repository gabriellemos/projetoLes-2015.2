package unity;

import models.Anuncio;
import models.Confeiteiro;
import org.junit.Test;

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
    }

}
