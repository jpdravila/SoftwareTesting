package br.io.jpdravila.servicos;

import br.io.jpdravila.entidades.Filme;
import br.io.jpdravila.entidades.Locacao;
import br.io.jpdravila.entidades.Usuario;
import br.io.jpdravila.exceptions.FilmeSemEstoqueException;
import br.io.jpdravila.exceptions.LocadoraException;
import br.io.jpdravila.matchers.DiaSemanaMatcher;
import br.io.jpdravila.matchers.MatchersProprios;
import br.io.jpdravila.utils.DataUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.io.jpdravila.matchers.MatchersProprios.caiEm;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocacaoServiceTeste {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deveAlugarFilme() throws Exception{

        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Ususario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        //Compilando erros C 5.0 - E 6.0 / C true - E false
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

    }

    //Elegante
    @Test(expected= FilmeSemEstoqueException.class)
    public void naoDeveAlugarFilmeSemEstoque() throws Exception{
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Ususario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 4.0));

        //acao
        service.alugarFilme(usuario, filmes);


    }

    //Robusta
    @Test
    public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException{
       //Cenário
       LocacaoService service = new LocacaoService();
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

       //Ação
        try {
            service.alugarFilme(null, filmes);
            Assert.fail();
        }
        catch (LocadoraException e){
            Assert.assertThat(e.getMessage(), is("Usuario vazio!"));
        }
        System.out.println("Forma robusta");
    }


    @Test
    public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException{
        //Cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");
        //Acao
        service.alugarFilme(usuario, null);

        System.out.println("Forma nova");
    }

    //Métodos transferidos para package br.io.jpdravila.servicos.CalculoValorLocacaoTeste;
    /*@Test
    public void devePagar75PctNoFilme2() throws FilmeSemEstoqueException, LocadoraException {
        //Cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0));
        LocacaoService service = new LocacaoService();
        //Ação
        Locacao resultado = service.alugarFilme(usuario, filmes);
        //Verificação

        assertThat(resultado.getValor(), is(11.0));
    }

    @Test
    public void devePagar50PctNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
        //Cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0));
        LocacaoService service = new LocacaoService();
        //Ação
        Locacao resultado = service.alugarFilme(usuario, filmes);
        //Verificação

        assertThat(resultado.getValor(), is(13.0));
    }

    @Test
    public void devePagar25PctNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
        //Cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0));
        LocacaoService service = new LocacaoService();
        //Ação
        Locacao resultado = service.alugarFilme(usuario, filmes);
        //Verificação

        assertThat(resultado.getValor(), is(14.0));
    }

    @Test
    public void devePagar0PctNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
        //Cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0), new Filme("Filme 6", 2, 4.0));
        LocacaoService service = new LocacaoService();
        //Ação
        Locacao resultado = service.alugarFilme(usuario, filmes);
        //Verificação

        assertThat(resultado.getValor(), is(14.0));
    }*/

    @Test
    public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException{
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));

        //acao
        Locacao retorno = service.alugarFilme(usuario, filmes);

        //verificacao
        boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
        Assert.assertTrue(ehSegunda);


        assertThat(retorno.getDataRetorno(), MatchersProprios.caiNumaSegunda());
    }

}