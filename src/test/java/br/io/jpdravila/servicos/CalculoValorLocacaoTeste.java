package br.io.jpdravila.servicos;

import br.io.jpdravila.entidades.Filme;
import br.io.jpdravila.entidades.Locacao;
import br.io.jpdravila.entidades.Usuario;
import br.io.jpdravila.exceptions.FilmeSemEstoqueException;
import br.io.jpdravila.exceptions.LocadoraException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTeste {
    /*
        @Parameterized.Parameter(value = 1)
        O número do value deve ser ordenado crecentemente
        Ex. 1, 2, 3 para cada values que esta no @Parameter(value = 1)
        Ele servirá para ser inserido no @Parameters

        Ex.: @Parameter(value = 1)     - @Parameterized.Parameter(value = 1)
             @Parameters(name = "{1}") - @Parameterized.Parameters(name = "{1}")

    */

    @Parameterized.Parameter
    public List<Filme> filmes;

    @Parameterized.Parameter(value = 1)
    public Double valorLocacao;

    public LocacaoService locacaoService;

    @Parameterized.Parameter(value = 2)
    public String cenario;

    @Before
    public void setup(){
        locacaoService = new LocacaoService();
    }

    public static Filme filme1 = new Filme("Filme 1", 2, 4.0);
    public static Filme filme2 = new Filme("Filme 2", 2, 4.0);
    public static Filme filme3 = new Filme("Filme 3", 2, 4.0);
    public static Filme filme4 = new Filme("Filme 4", 2, 4.0);
    public static Filme filme5 = new Filme("Filme 5", 2, 4.0);
    public static Filme filme6 = new Filme("Filme 6", 2, 4.0);
    public static Filme filme7 = new Filme("Filme 7", 2, 4.0);

    @Parameterized.Parameters(name = "{2}")
    public static Collection<Object[]> getParametros(){
        {
            return Arrays.asList(new Object[][]{
                    {Arrays.asList(filme1, filme2), 8.0, "2 Filmes: Sem desconto"},
                    {Arrays.asList(filme1, filme2, filme3), 11.0, "3 Filmes: 25%"},
                    {Arrays.asList(filme1, filme2, filme3, filme4), 13.0,"4 Filmes: 50%"},
                    {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 Filmes: 75%"},
                    {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6 Filmes: 100%"},
                    {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18.0, "7 Filmes: Sem desconto"}
            });
        }
    }

    @Test
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
        //Cenario
        Usuario usuario = new Usuario("Usuario 1");

        //Ação
        Locacao resultado = locacaoService.alugarFilme(usuario, filmes);
        //Verificação

        assertThat(resultado.getValor(), is(valorLocacao));
    }

    @Test
    public  void print(){
        System.out.println(valorLocacao);
    }

}
