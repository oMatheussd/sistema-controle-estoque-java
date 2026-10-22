package servicos;

import java.util.ArrayList;
import java.util.List;

import entidades.Movimentacao;
import entidades.Produto;

public class EstoqueService {
	
	private List<Produto> produtos = new ArrayList<>();
	private List<Movimentacao> movimentacoes = new ArrayList<>();
	
	public void cadastrarProduto(Produto produto) {
		produtos.add(produto);
	}
	
	/*
	
	public void listarProdutos() {
		
	}
	
	public Produto buscarProduto(int id) {
		return null;
	}
	
	public ??? registrarEntrada() {
		
	}

	public ??? registrarSaida() {
		
	}
	
	public ??? consultarEstoqueBaixo() {
		
	}
	
	public ??? gerarRelatorio() {
		
	}
	
	*/
}
