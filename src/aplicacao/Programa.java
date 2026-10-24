
package aplicacao;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Movimentacao;
import entidades.Produto;
import entidades.TipoMovimentacao;
import servicos.ArquivoService;
import servicos.EstoqueService;

public class Programa {

	public static void main(String[] args) {

		// Configura a leitura de números e inicializa os serviços do sistema.
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		EstoqueService estoque = new EstoqueService();
		ArquivoService arquivoService = new ArquivoService();

		// Carrega os produtos e o histórico de movimentações salvos nos arquivos CSV.
		try {
		    List<Produto> produtos = arquivoService.carregarProdutos();
		    estoque.definirProdutos(produtos);

		    List<Movimentacao> movimentacoes =
		            arquivoService.carregarMovimentacoes(produtos);
		    estoque.definirMovimentacoes(movimentacoes);

		} catch (IOException e) {
		    System.out.println("Erro ao carregar arquivos: " + e.getMessage());
		    return;
		}

		int resposta, codigo, quantidade;
		double preco;

		// Mantém o menu em execução até o usuário escolher a opção de saída.
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

			// Direciona a execução para a funcionalidade escolhida no menu.
			switch (resposta) {

			case 1:

				// Solicita os dados, cadastra o produto e salva a lista atualizada.
				try {
					System.out.println();
					System.out.println("CADASTRAR PRODUTO");
					System.out.println();

					System.out.print("Codigo: ");
					codigo = sc.nextInt();
					sc.nextLine();
					System.out.print("Nome: ");
					String nome = sc.nextLine();
					System.out.print("Categoria: ");
					String categoria = sc.nextLine();
					System.out.print("Preço: ");
					preco = sc.nextDouble();
					System.out.print("Quantidade: ");
					quantidade = sc.nextInt();
					System.out.print("Estoque minimo: ");
					int estoqueMinimo = sc.nextInt();
					sc.nextLine();

					estoque.cadastrarProduto(new Produto(codigo, nome, categoria, preco, quantidade, estoqueMinimo));
					arquivoService.salvarProdutos(estoque.getProdutos());

					System.out.println();
					System.out.println("Produto cadastrado com sucesso!");

				} catch (IllegalArgumentException e) {
					System.out.println();
					System.out.println(e.getMessage());
					System.out.println();
				}
				catch (IOException e) {
				    System.out.println("Erro ao salvar arquivos: " + e.getMessage());
				}

				sc.nextLine();

				break;

			case 2:

				// Ordena os produtos pelo código e exibe a listagem.
				System.out.println();
				System.out.println("PRODUTOS CADASTRADOS");
				System.out.println();

				estoque.ordenarProdutosPorCodigo();
				estoque.listarProdutos();

				sc.nextLine();

				break;

			case 3:

				// Busca um produto pelo código informado e exibe o resultado.
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

				// Registra uma entrada, atualiza o estoque e salva os dados nos CSVs.
				try {
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
					arquivoService.salvarProdutos(estoque.getProdutos());
					arquivoService.salvarMovimentacoes(estoque.getMovimentacoes());

					System.out.println();
					System.out.println("Entrada registrada com sucesso!");

				} catch (IllegalArgumentException e) {
					System.out.println();
					System.out.println(e.getMessage());
				}
				catch (IOException e) {
				    System.out.println("Erro ao salvar arquivos: " + e.getMessage());
				}

				sc.nextLine();

				break;

			case 5:

				// Registra uma saída, validando as regras de estoque no serviço.
				try {
					System.out.println();
					System.out.println("REGISTRAR SAIDA DE PRODUTO");
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

					System.out.print("Quantidade de saida: ");
					quantidade = sc.nextInt();
					sc.nextLine();

					estoque.registrarSaida(new Movimentacao(produto, TipoMovimentacao.SAIDA, quantidade));
					arquivoService.salvarProdutos(estoque.getProdutos());
					arquivoService.salvarMovimentacoes(estoque.getMovimentacoes());

					System.out.println();
					System.out.println("Saida registrada com sucesso!");

				} catch (IllegalArgumentException e) {
					System.out.println();
					System.out.println(e.getMessage());
				}
				catch (IOException e) {
				    System.out.println("Erro ao salvar arquivos: " + e.getMessage());
				}

				sc.nextLine();

				break;

			case 6:

				// Exibe os produtos que atingiram ou ficaram abaixo do estoque mínimo.
				System.out.println();
				System.out.println("ESTOQUE BAIXO");
				System.out.println();

				estoque.consultarEstoqueBaixo();
				sc.nextLine();

				break;

			case 7:

				// Gera o relatório com os dados e os valores do estoque.
				System.out.println();
				System.out.println("RELATORIO");
				System.out.println();

				estoque.gerarRelatorio();
				System.out.println();
				sc.nextLine();

				break;

			case 8:

				// Exibe o histórico das movimentações registradas.
				System.out.println();
				System.out.println("MOVIMENTAÇÕES");
				System.out.println();

				estoque.listarMovimentacoes();

				sc.nextLine();

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

		// Encerra a leitura de dados ao finalizar o programa.
		sc.close();
	}
}
