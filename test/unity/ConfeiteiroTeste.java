package unity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import models.Anuncio;
import models.Confeiteiro;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashSet;

/**
 * Teste de unidade para a classe de modelo Confeiteiro.
 *
 * @author Gabriel
 */
public class ConfeiteiroTeste {

    protected Anuncio anuncioDefault;

    protected Confeiteiro confeteiroDefault;
    protected Confeiteiro confeiteiroSemAnuncios;
    protected Confeiteiro confeiteiroComAnuncios;

    @Before
    public void setUp() {
        anuncioDefault = new Anuncio();

        confeteiroDefault = new Confeiteiro();
        confeiteiroSemAnuncios = new Confeiteiro("Bolo");
        confeiteiroComAnuncios = new Confeiteiro("Master");
        confeiteiroComAnuncios.addAnuncio(anuncioDefault);
    }

    @Test
    public void TestaConstrutorDefault() {
        Confeiteiro confeiteiroTeste;

        confeiteiroTeste = new Confeiteiro();
        Assert.assertNotEquals(confeiteiroTeste, confeiteiroSemAnuncios);
        Assert.assertEquals(confeteiroDefault, confeiteiroTeste);

        Assert.assertNull(confeiteiroTeste.getNome());
        Assert.assertNull(confeiteiroTeste.getEndereco());
        Assert.assertNull(confeiteiroTeste.getEmail());
        Assert.assertNull(confeiteiroTeste.getContato());
        Assert.assertNull(confeiteiroTeste.getId());

        Assert.assertNotNull(confeiteiroTeste.getAnuncios());
        Assert.assertTrue(confeiteiroTeste.getAnuncios().isEmpty());
        Assert.assertEquals(HashSet.class, confeiteiroTeste.getAnuncios().getClass());
    }

    @Test
    public void TestaConstrutorComNome() {
        String nomeConfeiteiro = "Bolo Master";
        Confeiteiro confeiteiroTeste;

        confeiteiroTeste = new Confeiteiro(nomeConfeiteiro);
        Assert.assertNotEquals(confeiteiroSemAnuncios, confeiteiroTeste);
        Assert.assertNotEquals(confeteiroDefault, confeiteiroTeste);

        Assert.assertEquals(nomeConfeiteiro, confeiteiroTeste.getNome());
        Assert.assertNull(confeiteiroTeste.getEndereco());
        Assert.assertNull(confeiteiroTeste.getEmail());
        Assert.assertNull(confeiteiroTeste.getContato());
        Assert.assertNull(confeiteiroTeste.getId());

        Assert.assertNotNull(confeiteiroTeste.getAnuncios());
        Assert.assertEquals(confeiteiroSemAnuncios.getAnuncios(), confeiteiroTeste.getAnuncios());
    }

    @Test
    public void TestaConstrutorNomeInvalido() {
        Confeiteiro confeiteiro;
        String[] nomesInvalidos = {null, "", "     "};

        for (String nomeInvalido : nomesInvalidos) {
           try {
               confeiteiro = new Confeiteiro(nomeInvalido);
               Assert.fail("Foi possível criar um confeiteiro com o nome: " + nomeInvalido);
           } catch (IllegalArgumentException exception) {
                // Ok, continue o teste
           } catch (Exception exception) {
               // Sempre irá falhar ao chegar aqui
               Assert.assertEquals(IllegalArgumentException.class, exception.getClass());
           }
        }
    }

    @Test
    public void TestaSetNomeValido() {
        String[] nomesValidos = {".", "A", "João", "BoloPink"};

        for (String nomeValido : nomesValidos) {
            try {
                confeteiroDefault.setNome(nomeValido);
                // Ok, continue o teste
            } catch (IllegalArgumentException exception) {
                Assert.fail("Não foi possível setar o 'Nome' do confeiteiro para: : " + nomeValido);
            }
        }
    }

    @Test
    public void TestaSetNomeInvalido() {
        String[] nomesInvalidos = {null, "", "     "};

        for (String nomeInvalido : nomesInvalidos) {
            try {
                confeteiroDefault.setNome(nomeInvalido);
                Assert.fail("Foi possível setar o 'Endereço' do confeiteiro para: " + nomeInvalido);
            } catch (IllegalArgumentException exception) {
                // Ok, continue o teste
            } catch (Exception exception) {
                // Sempre irá falhar ao chegar aqui
                Assert.assertEquals(IllegalArgumentException.class, exception.getClass());
            }
        }
    }

    // Testa setEndereco
    @Test
    public void TestaSetEnderecoValido() {
        String[] enderecosValidos = {".", "A", "Campina Grande",
                "R. Aprígio Veloso, 882 - Universitário, Campina Grande - PB, 58429-900"};

        for (String enderecoValido : enderecosValidos) {
            try {
                confeteiroDefault.setEndereco(enderecoValido);
                // Ok, continue o teste
            } catch (IllegalArgumentException exception) {
                Assert.fail("Não foi possível setar o 'Endereço' do confeiteiro para: " + enderecoValido);
            }
        }
    }

    @Test
    public void TestaSetEnderecoInvalido() {
        String[] enderecoInvalidos = {null, "", "     "};

        for (String enderecoInvalido : enderecoInvalidos) {
            try {
                confeteiroDefault.setEndereco(enderecoInvalido);
                Assert.fail("Foi possível setar o 'Endereço' do confeiteiro para: " + enderecoInvalido);
            } catch (IllegalArgumentException exception) {
                // Ok, continue o teste
            } catch (Exception exception) {
                // Sempre irá falhar ao chegar aqui
                Assert.assertEquals(IllegalArgumentException.class, exception.getClass());
            }
        }
    }

    // Testa SetEmail
    @Test
    public void TestaSetEmailValido() {
        String[] emailValidos = {".", "f", "fulano", "fulano@mail.com"};

        for (String emailValido : emailValidos) {
            try {
                confeteiroDefault.setEmail(emailValido);
                // Ok, continue o teste
            } catch (IllegalArgumentException exception) {
                Assert.fail("Não foi possível setar o 'Email' do confeiteiro para: " + emailValido);
            }
        }
    }

    @Test
    public void TestaSetEmailInvalido() {
        String[] emailInvalidos = {null, "", "     "};

        for (String emailInvalido : emailInvalidos) {
            try {
                confeteiroDefault.setEmail(emailInvalido);
                Assert.fail("Foi possível setar o 'Email' do confeiteiro para: " + emailInvalido);
            } catch (IllegalArgumentException exception) {
                // Ok, continue o teste
            } catch (Exception exception) {
                // Sempre irá falhar ao chegar aqui
                Assert.assertEquals(IllegalArgumentException.class, exception.getClass());
            }
        }
    }

    // Testa SetContato
    @Test
    public void TestaSetContatoValido() {
        String[] contatoValidos = {".", "c", "contato", "Phone: (83) 2101-1000"};

        for (String contatoValido : contatoValidos) {
            try {
                confeteiroDefault.setContato(contatoValido);
                // Ok, continue o teste
            } catch (IllegalArgumentException exception) {
                Assert.fail("Não foi possível setar o 'Contato' do confeiteiro para: " + contatoValido);
            }
        }
    }

    @Test
    public void TestaSetContatoInvalido() {
        String[] contatoInvalidos = {null, "", "     "};

        for (String contatoInvalido : contatoInvalidos) {
            try {
                confeteiroDefault.setContato(contatoInvalido);
                Assert.fail("Foi possível setar o 'Contato' do confeiteiro para: " + contatoInvalido);
            } catch (IllegalArgumentException exception) {
                // Ok, continue o teste
            } catch (Exception exception) {
                // Sempre irá falhar ao chegar aqui
                Assert.assertEquals(IllegalArgumentException.class, exception.getClass());
            }
        }
    }

    // Testa SetId
    @Test
    public void TestaSetIdValido() {
        String[] idValidos = {".", "0", "012-abcd"};
//
        for (String idValido : idValidos) {
            try {
                confeteiroDefault.setId(idValido);
                // Ok, continue o teste
            } catch (IllegalArgumentException exception) {
                Assert.fail("Não foi possível setar o 'ID' do confeiteiro para: " + idValido);
            }
        }
    }
//
    @Test
    public void TestaReSetId() {
        confeteiroDefault.setId("Primeiro ID");
        try {
            confeteiroDefault.setId("Novo ID");
            Assert.fail("Foi possível setar um novo 'ID' para o confeiteiro");
        } catch (IllegalArgumentException exception) {
            // Ok, continue o teste
        } catch (Exception exception) {
            // Sempre irá falhar ao chegar aqui
            Assert.assertEquals(IllegalArgumentException.class, exception.getClass());
        }
    }

    @Test
    public void TestaSetIdInvalido() {
        Confeiteiro confeiteiroTeste;
        String[] idInvalidos = {null, "", "     "};

        for (String idInvalido : idInvalidos) {
            try {
                confeiteiroTeste = new Confeiteiro();
                confeiteiroTeste.setId(idInvalido);
                Assert.fail("Foi possível setar o 'ID' do confeiteiro para: " + idInvalido);
            } catch (IllegalArgumentException exception) {
                // Ok, continue o teste
            } catch (Exception exception) {
                // Sempre irá falhar ao chegar aqui
                Assert.assertEquals(IllegalArgumentException.class, exception.getClass());
            }
        }
    }

    // Testa addAnuncio
    @Test
    public void TestaAddAnuncio() {
        Assert.assertTrue(confeteiroDefault.getAnuncios().isEmpty());

        // Nenhuma exceção é esperada.
        confeteiroDefault.addAnuncio(anuncioDefault);

        try {
            confeteiroDefault.addAnuncio(anuncioDefault);
            Assert.fail("É possível existir dois ou mais anúncios duplicados");
        } catch (KeyAlreadyExistsException exception) {
            // Ok, continue o teste
        } catch (Exception exception) {
            // Sempre irá falhar ao chegar aqui
            Assert.assertEquals(KeyAlreadyExistsException.class, exception.getClass());
        }

        // Nem mesmo se for adicionado um anúncio que já foi andicionado previamente.
        confeteiroDefault.addAnuncio(anuncioDefault);
    }

    // Testa getAnuncio
    @Test
    public void TestaGetAnuncio() {
        Assert.assertTrue(confeteiroDefault.getAnuncios().isEmpty());
        Assert.assertEquals(confeteiroDefault.getAnuncios(), confeiteiroSemAnuncios.getAnuncios());
        Assert.assertNotEquals(confeteiroDefault.getAnuncios(), confeiteiroComAnuncios.getAnuncios());

        Assert.assertTrue(confeiteiroComAnuncios.getAnuncios().contains(anuncioDefault));
    }
}
