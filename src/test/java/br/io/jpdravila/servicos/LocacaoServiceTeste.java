package br.io.jpdravila.servicos;

import br.io.jpdravila.entidades.Filme;
import br.io.jpdravila.entidades.Locacao;
import br.io.jpdravila.entidades.Usuario;
import br.io.jpdravila.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;

import java.util.Date;

import static java.util.function.Predicate.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
//        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
//        Assert.assertEquals(5.0, locacao.getValor(), 0.01);
//        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

//        assertThat(locacao.getValor(), is(equalTo(5.0)));
//        assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
//        assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

        //Compilando erros C 5.0 - E 6.0 / C true - E false
        error.checkThat(locacao.getValor(), is(equalTo(6.0)));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(false));

    }

}
