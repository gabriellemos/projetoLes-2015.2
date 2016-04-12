package unity;

import models.Anuncio;
import models.Confeiteiro;
import models.TipoAnuncio;
import models.Utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

/**
 * Teste de unidade para a classe de modelo Confeiteiro.
 *
 * @author Gabriel
 */
public class AnuncioTeste {


    protected Anuncio anuncioDefault2;
    protected Anuncio anuncioComum;
    protected Anuncio anuncioAniversario;
    protected Anuncio anuncioCasamento;

    protected Confeiteiro confeteiroDefault;
    protected Confeiteiro confeiteiroSemAnuncios;

    @Before
    public void setUp() {
        confeteiroDefault = new Confeiteiro();
        confeiteiroSemAnuncios = new Confeiteiro("Bolo");

        anuncioDefault2 = new Anuncio();

        anuncioComum = new Anuncio("Este é um anuncio comum", confeteiroDefault);
        anuncioAniversario = new Anuncio("Este é um anuncio de aniversario", confeiteiroSemAnuncios, "Anuncio de aniversario para testes", TipoAnuncio.ANIVERSARIO, 5);
        anuncioCasamento = new Anuncio("Este é um anuncio de casamento", confeiteiroSemAnuncios, "Anuncio de casamento para testes", TipoAnuncio.CASAMENTO, 50);

    }

    @Test
    public void TestaConstrutorDefault() {
        Anuncio anuncioDefault;

        anuncioDefault = new Anuncio();
        Assert.assertNotEquals(anuncioDefault, anuncioAniversario);
        Assert.assertNotEquals(anuncioDefault, anuncioDefault2);

        Assert.assertNull(anuncioDefault.getTitulo());
        Assert.assertNull(anuncioDefault.getCriador());
        Assert.assertNull(anuncioDefault.getDescricao());
        Assert.assertNull(anuncioDefault.getDataCriacao());
        Assert.assertNull(anuncioDefault.getDataEdicao());
        Assert.assertNull(anuncioDefault.getTipoAnuncio());
        Assert.assertTrue(anuncioDefault.getPreco() == 0.0);
    }

    @Test
    public void TestaConstrutorComTituloEConfeiteiro() {
        Assert.assertNotNull(anuncioComum.getTitulo());
        Assert.assertNotNull(anuncioComum.getCriador());
        Assert.assertNotNull(anuncioComum.getDescricao());
        Assert.assertNotNull(anuncioComum.getTipoAnuncio());
        Assert.assertNotNull(anuncioComum.getDataCriacao());

        Assert.assertNull(anuncioComum.getDataEdicao());

        Assert.assertTrue(anuncioComum.getPreco() == 0.0);

    }

    @Test
    public void TestaConstrutorComTodosParametros() {
        Assert.assertNotNull(anuncioAniversario.getTitulo());
        Assert.assertNotNull(anuncioAniversario.getCriador());
        Assert.assertNotNull(anuncioAniversario.getDescricao());
        Assert.assertNotNull(anuncioAniversario.getTipoAnuncio());
        Assert.assertNotNull(anuncioAniversario.getDataCriacao());

        Assert.assertNull(anuncioAniversario.getDataEdicao());

        Assert.assertTrue(anuncioAniversario.getPreco() != 0.0);

    }

    @Test
    public void TestaGetSetTitulo() {
        Assert.assertNull(anuncioDefault2.getTitulo());
        anuncioDefault2.setTitulo("Novo titulo para anuncio default 2");
        Assert.assertEquals(anuncioDefault2.getTitulo(), "Novo titulo para anuncio default 2");

        Assert.assertEquals(anuncioComum.getTitulo(), "Este é um anuncio comum");
        anuncioComum.setTitulo("Novo titulo para anuncio comum");
        Assert.assertEquals(anuncioComum.getTitulo(), "Novo titulo para anuncio comum");

        Assert.assertEquals(anuncioCasamento.getTitulo(), "Este é um anuncio de casamento");
        anuncioCasamento.setTitulo("Novo titulo para anuncio casamento");
        Assert.assertEquals(anuncioCasamento.getTitulo(), "Novo titulo para anuncio casamento");
    }

    @Test
    public void TestaSetTituloInvalido() {
        String[] titulosInvalidos = {null, "", "     "};

        for (String tituloInvalido : titulosInvalidos) {
            try {
                anuncioAniversario.setTitulo(tituloInvalido);
                Assert.fail("Foi possível setar o titulo do anuncio para: " + tituloInvalido);
            } catch (IllegalArgumentException exception) {
                Assert.assertEquals(IllegalArgumentException.class, exception.getClass());
                Assert.assertEquals(exception.getMessage(), "Argumento 'Título' recebendo valores inválidos");
            }
        }
    }

    @Test
    public void TestaGetSetDescricao() {
        Assert.assertNull(anuncioDefault2.getDescricao());
        anuncioDefault2.setDescricao("Nova descricao para anuncio default 2");
        Assert.assertEquals(anuncioDefault2.getDescricao(), "Nova descricao para anuncio default 2");

        Assert.assertEquals(anuncioComum.getDescricao(), "Sem descrição");
        anuncioComum.setDescricao("Adiciona descricao para anuncio comum");
        Assert.assertEquals(anuncioComum.getDescricao(), "Adiciona descricao para anuncio comum");

        Assert.assertEquals(anuncioCasamento.getDescricao(), "Anuncio de casamento para testes");
        anuncioCasamento.setDescricao("Nova descricao para anuncio casamento");
        Assert.assertEquals(anuncioCasamento.getDescricao(), "Nova descricao para anuncio casamento");
    }

    @Test
    public void TestaSetDescricaoInvalida() {
        String[] descricoesInvalidas = {null, "", "     "};

        for (String descricaoInvalida : descricoesInvalidas) {
            try {
                anuncioAniversario.setDescricao(descricaoInvalida);
                Assert.fail("Foi possível setar a descricao do anuncio para: " + descricaoInvalida);
            } catch (IllegalArgumentException exception) {
                Assert.assertEquals(IllegalArgumentException.class, exception.getClass());
                Assert.assertEquals(exception.getMessage(), "Argumento 'Descrição' recebendo valores inválidos");
            }
        }
    }

    @Test
    public void TestaGetSetPreco() {
        Assert.assertTrue(anuncioDefault2.getPreco() == 0.0);
        anuncioDefault2.setPreco(7);
        Assert.assertTrue(anuncioDefault2.getPreco() == 7.0);

        Assert.assertTrue(anuncioComum.getPreco() == 0.0);
        anuncioComum.setPreco(100);
        Assert.assertTrue(anuncioComum.getPreco() == 100);

        Assert.assertTrue(anuncioAniversario.getPreco() == 5);
        anuncioAniversario.setPreco(1000);
        Assert.assertTrue(anuncioAniversario.getPreco() == 1000);
    }
    
}
