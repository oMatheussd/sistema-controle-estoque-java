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
	
	public void listarProdutos() {
		for (Produto p : produtos) {
			System.out.println(p);
		}
	}
	
	public Produto buscarProduto(int codigo) {
		for (Produto p : produtos) {
			if (p.getCodigo() == codigo) {
				return p;
			}
		}
		return null;
	}	
	
	public void registrarEntrada(Movimentacao movimentacao) {
		movimentacoes.add(movimentacao);
		Produto produto = movimentacao.getProduto();
		produto.setQuantidade(produto.getQuantidade() + movimentacao.getQuantidade());
			
		
	}
	
	/*

	public ??? registrarSaida() {
		
	}
	
	public ??? consultarEstoqueBaixo() {
		
	}
	
	public ??? gerarRelatorio() {
		
	}
	
	*/
}
