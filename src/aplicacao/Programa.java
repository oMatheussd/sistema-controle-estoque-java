package aplicacao;

import java.util.Locale;
import java.util.Scanner;

import entidades.Movimentacao;
import entidades.Produto;
import entidades.TipoMovimentacao;
import servicos.EstoqueService;

public class Programa {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		EstoqueService estoque = new EstoqueService();

		estoque.cadastrarProduto(new Produto(101, "Teclado", "Periféricos", 120.00, 10, 3));
		estoque.cadastrarProduto(new Produto(102, "Mouse", "Periféricos", 55.90, 15, 5));
		estoque.cadastrarProduto(new Produto(103, "Monitor", "Monitores", 899.90, 4, 5));
		estoque.cadastrarProduto(new Produto(104, "Cabo HDMI", "Acessórios", 25.50, 20, 10));
		estoque.cadastrarProduto(new Produto(105, "Notebook", "Computadores", 3500.00, 2, 3));

		int resposta;

		do {

			System.out.println("=== CONTROLE DE ESTOQUE ===");
			System.out.println();
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Listar produtos");
			System.out.println("3 - Buscar produto por codigo");
			System.out.println("4 - Registrar entrada");
			System.out.println("5 - Registrar saida");
			System.out.println("6 - Ver estoque baixo");
			System.out.println("7 - Relatórios");
			System.out.println("8 - Historico de movimentações");
			System.out.println("0 - Sair");

			System.out.println();

			System.out.print("Digite uma opção: ");
			resposta = sc.nextInt();
			sc.nextLine();

			switch (resposta) {

			case 1:

				System.out.println();
				System.out.println("CADASTRAR PRODUTO");
				System.out.println();

				System.out.print("Codigo: ");
				int codigo = sc.nextInt();
				sc.nextLine();
				System.out.print("Nome: ");
				String nome = sc.nextLine();
				System.out.print("Categoria: ");
				String categoria = sc.nextLine();
				System.out.print("Preço: ");
				double preco = sc.nextDouble();
				System.out.print("Quantidade: ");
				int quantidade = sc.nextInt();
				System.out.print("Estoque minimo: ");
				int estoqueMinimo = sc.nextInt();
				sc.nextLine();

				estoque.cadastrarProduto(new Produto(codigo, nome, categoria, preco, quantidade, estoqueMinimo));

				System.out.println();
				System.out.println("Produto cadastrado com sucesso!");

				sc.nextLine();

				break;

			case 2:

				System.out.println();
				System.out.println("PRODUTOS CADASTRADOS");
				System.out.println();

				estoque.listarProdutos();

				sc.nextLine();

				break;
			case 3:

				System.out.println();
				System.out.println("BUSCAR PRODUTO");
				System.out.println();

				System.out.print("Codigo do produto: ");
				codigo = sc.nextInt();
				sc.nextLine();

				Produto produto = estoque.buscarProduto(codigo);
				System.out.println();

				if (produto == null) {
					System.out.println("Produto não encontrado. Tente novamente.");
				} else {
					System.out.println(produto);
				}

				System.out.println();
				sc.nextLine();

				break;
			case 4:
				
				System.out.println();
				System.out.println("REGISTRAR ENTRADA DE PRODUTO");
				System.out.println();
				
				do {

					System.out.print("Codigo do produto: ");
					codigo = sc.nextInt();
					sc.nextLine();

					produto = estoque.buscarProduto(codigo);

					if (produto == null) {
						System.out.println("Produto não encontrado. Tente novamente.");
						
					}
				} while (produto == null);
				
				System.out.println();
				System.out.print("Quantidade de entrada: ");
				quantidade = sc.nextInt();
				sc.nextLine();
				
				estoque.registrarEntrada(new Movimentacao(produto, TipoMovimentacao.ENTRADA, quantidade));
				
				System.out.println();
				System.out.println("Entrada registrada com sucesso!");
				sc.nextLine();

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:

				break;
			case 8:

				break;
			case 0:

				System.out.println("Saindo...");

				break;
			default:

				System.out.println("Opção inválida. Tente novamente.");
				sc.nextLine();

				break;
			}

		} while (resposta != 0);

		sc.close();
	}
}
