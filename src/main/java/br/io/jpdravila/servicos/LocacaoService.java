package br.io.jpdravila.servicos;

import java.util.Date;

import br.io.jpdravila.entidades.Filme;
import br.io.jpdravila.entidades.Locacao;
import br.io.jpdravila.entidades.Usuario;
import br.io.jpdravila.exceptions.FilmeSemEstoqueException;
import br.io.jpdravila.exceptions.LocadoraException;
import br.io.jpdravila.utils.DataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocacaoService{


	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException{

		if(usuario == null){
			throw new LocadoraException("Usuario vazio!");
		}
		if(filme == null){
			throw new LocadoraException("Filme vazio");
		}
		if(filme.getEstoque() == 0){
			throw new FilmeSemEstoqueException();
		}

		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = DataUtils.adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	@Test
	public void teste() {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Ususario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		//acao
			/*Locacao locacao = service.alugarFilme(usuario, filme);

			Assertions.assertTrue(locacao.getValor() == 5.0);
			Assertions.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
			Assertions.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));*/
		}



		//verificacao

}