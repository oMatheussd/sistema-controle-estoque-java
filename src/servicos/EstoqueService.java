package servicos;

import java.util.ArrayList;
import java.util.List;

import entidades.Movimentacao;
import entidades.Produto;

public class EstoqueService {

	private List<Produto> produtos = new ArrayList<>();
	private List<Movimentacao> movimentacoes = new ArrayList<>();
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void cadastrarProduto(Produto produto) {
		Produto produtoEncontrado = buscarProduto(produto.getCodigo());
		
		if (produtoEncontrado != null) {
			throw new IllegalArgumentException("O codigo informado já existe, tente novamente."); 
		}
		
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
	
	public void ordenarProdutosPorCodigo() {
		
		for (int i =0; i < produtos.size(); i++) {
			int indiceMenor = i;
			for (int j = i + 1; j < produtos.size(); j++) {
				if (produtos.get(j).getCodigo() < produtos.get(indiceMenor).getCodigo()) {
					indiceMenor = j;
				}
			}
			Produto temporario = produtos.get(i);
			produtos.set(i, produtos.get(indiceMenor));
			produtos.set(indiceMenor, temporario);
		}	
	}

	public void registrarEntrada(Movimentacao movimentacao) {
		Produto produto = movimentacao.getProduto();
		
		if (movimentacao.getQuantidade() <= 0) {
			throw new IllegalArgumentException("A quantidade digitada deve ser maior que zero.");
		}
		
		movimentacoes.add(movimentacao);
		produto.setQuantidade(produto.getQuantidade() + movimentacao.getQuantidade());
	}

	public void registrarSaida(Movimentacao movimentacao) {
		Produto produto = movimentacao.getProduto();
		
		if (movimentacao.getQuantidade() <= 0) {
			throw new IllegalArgumentException("A quantidade digitada deve ser maior que zero.");
		}
		if (movimentacao.getQuantidade() > produto.getQuantidade()) {
			throw new IllegalArgumentException("A quantidade digitada é maior que a do estoque atual. Tente novamente.");
		}
		
		movimentacoes.add(movimentacao);
		produto.setQuantidade(produto.getQuantidade() - movimentacao.getQuantidade());
	}

	public void consultarEstoqueBaixo() {

		for (Produto p : produtos) {
			if (p.getQuantidade() <= p.getEstoqueMinimo()) {
				System.out.println(p);
			}
		}
	}

	public void gerarRelatorio() {
		double somaTotal = 0.0;
		int quantidadeTotal = 0;

		System.out.printf("%-16s | %-10s | %-11s | %-20s%n", "Nome", "Quantidade", "Preço", "Valor em estoque");
		System.out.println();
		for (Produto p : produtos) {
			double total = p.getPreco() * p.getQuantidade();
			System.out.printf("%-16s | %-10d | R$ %8.2f | R$ %13.2f%n", p.getNome(), p.getQuantidade(), p.getPreco(),
					total);
			somaTotal += total;
			quantidadeTotal += p.getQuantidade();
		}

		System.out.println();
		System.out.printf("%-15s | %-10s | %-11s | R$ %13.2f%n", "Total em estoque", quantidadeTotal, "", somaTotal);
	}
	
	public void listarMovimentacoes() {
		System.out.printf("%-15s | %-7s | %-10s | %s%n",
		        "Produto", "Tipo", "Quantidade", "Data e hora");
		System.out.println();
		for (Movimentacao m : movimentacoes) {
			System.out.println(m);
		}
	}
	
	public void definirProdutos(List<Produto> produtos) {
	    this.produtos = produtos;
	}

	public void definirMovimentacoes(List<Movimentacao> movimentacoes) {
	    this.movimentacoes = movimentacoes;
	}
}
