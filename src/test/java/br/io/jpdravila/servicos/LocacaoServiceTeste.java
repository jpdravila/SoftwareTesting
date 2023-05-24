package br.io.jpdravila.servicos;

import br.io.jpdravila.entidades.Filme;
import br.io.jpdravila.entidades.Locacao;
import br.io.jpdravila.entidades.Usuario;
import br.io.jpdravila.exceptions.FilmeSemEstoqueException;
import br.io.jpdravila.exceptions.LocadoraException;
import br.io.jpdravila.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static java.util.function.Predicate.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LocacaoServiceTeste {

    private LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    /*@Before
    public void setup(){
        System.out.println("Before/Antes");
    }

    @After
    public void tearDown(){
        System.out.println("After/Depois");
    }

    @BeforeClass
    public static void setupClass(){
        System.out.println("Before Class/Antes");
    }

    @AfterClass
    public static void tearDownClass(){
        System.out.println("After Class/Depois");
    }*/

    @Test
    public void teste() throws Exception{
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Ususario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //acao
        Locacao locacao = service.alugarFilme(usuario, filme);



        //verificacao
//        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
//        Assert.assertEquals(5.0, locacao.getValor(), 0.01);
//        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

//        assertThat(locacao.getValor(), is(equalTo(5.0)));
//        assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
//        assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

        //Compilando erros C 5.0 - E 6.0 / C true - E false
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

    }

    //Elegante
    @Test(expected= FilmeSemEstoqueException.class)
    public void testeLocacao_filmeSemEstoque() throws Exception{
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Ususario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        //acao
        service.alugarFilme(usuario, filme);


    }

    //Com a criação da forma elegante foi necessario apenas a criação de uma classe Exception para
    //o mesmo
    /*@Test
    public void testeLocacao_filmeSemEstoque_2(){
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Ususario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        try {
            //acao
            service.alugarFilme(usuario, filme);
            //Assert.fail("Deveria ter lançado uma exceção");
        }catch (Exception e){
            e.printStackTrace();
            Assert.assertThat(e.getMessage(), is("Filme sem estoque"));
        }
    }

    @Test
    public void testeLocacao_filmeSemEstoque_3() throws Exception{
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Ususario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque");

        //acao
        service.alugarFilme(usuario, filme);

    }*/

    //Robusta
    @Test
    public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException{
       //Cenário
       LocacaoService service = new LocacaoService();
       Filme filme = new Filme("Filme Dois", 1, 4.0);

       //Ação
        try {
            service.alugarFilme(null, filme);
            Assert.fail();
        }
        catch (LocadoraException e){
            Assert.assertThat(e.getMessage(), is("Usuario vazio!"));
        }
        System.out.println("Forma robusta");
    }


    @Test
    public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException{
        //Cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");
        //Acao
        service.alugarFilme(usuario, null);

        System.out.println("Forma nova");
    }

}
