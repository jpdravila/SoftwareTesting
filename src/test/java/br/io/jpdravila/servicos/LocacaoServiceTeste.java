package br.io.jpdravila.servicos;

import static br.io.jpdravila.utils.DataUtils.isMesmaData;
import static br.io.jpdravila.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import br.io.jpdravila.entidades.Filme;
import br.io.jpdravila.entidades.Locacao;
import br.io.jpdravila.entidades.Usuario;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class LocacaoServiceTeste {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void teste() {
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Ususario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //acao
        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificacao

        error.checkThat(locacao.getValor(), CoreMatchers.is(equalTo(5.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), CoreMatchers.is(true));


        /*assertThat(locacao.getValor(), CoreMatchers.is(equalTo(5.0)));
        assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
        assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), CoreMatchers.is(true));

        Assert.assertThat(locacao.getValor(), CoreMatchers.equalTo(5.0));

        Assertions.assertEquals(5.0, locacao.getValor(), 0.01);
        Assertions.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assertions.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));*/

    }

}
