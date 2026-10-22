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

	public void registrarSaida(Movimentacao movimentacao) {
		movimentacoes.add(movimentacao);
		Produto produto = movimentacao.getProduto();
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
}
