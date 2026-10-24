package servicos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entidades.Movimentacao;
import entidades.Produto;
import entidades.TipoMovimentacao;

// Responsável por salvar e carregar os dados do sistema em arquivos CSV.
public class ArquivoService {

    // Define os caminhos dos arquivos que armazenam os produtos e as movimentações.
    private final String arquivoProdutos = "dados/produtos.csv";
    private final String arquivoMovimentacoes = "dados/movimentacoes.csv";

    // Salva os dados dos produtos no arquivo CSV.
    public void salvarProdutos(List<Produto> produtos)
            throws IOException {

        // Cria a pasta de dados caso ela ainda não exista.
        new File("dados").mkdirs();

        // Abre o arquivo para escrita e o fecha automaticamente ao terminar.
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(arquivoProdutos))) {

            // Percorre os produtos e grava seus dados separados por vírgulas.
            for (Produto p : produtos) {
                escritor.write(
                    p.getCodigo() + ","
                    + p.getNome() + ","
                    + p.getCategoria() + ","
                    + p.getPreco() + ","
                    + p.getQuantidade() + ","
                    + p.getEstoqueMinimo()
                );

                // Inicia uma nova linha para o próximo produto.
                escritor.newLine();
            }
        }
    }

    // Carrega os produtos armazenados no arquivo CSV.
    public List<Produto> carregarProdutos() throws IOException {

        List<Produto> produtos = new ArrayList<>();
        File arquivo = new File(arquivoProdutos);

        // Retorna uma lista vazia caso o arquivo ainda não exista.
        if (!arquivo.exists()) {
            return produtos;
        }

        // Abre o arquivo para leitura e o fecha automaticamente ao terminar.
        try (BufferedReader leitor = new BufferedReader(
                new FileReader(arquivo))) {

            String linha;

            // Lê o arquivo linha por linha até o fim.
            while ((linha = leitor.readLine()) != null) {
                // Ignora linhas vazias.
                if (linha.isBlank()) {
                    continue;
                }

                // Separa os campos da linha usando a vírgula como delimitador.
                String[] dados = linha.split(",", -1);

                // Converte os dados lidos e cria um objeto Produto.
                Produto produto = new Produto(
                    Integer.parseInt(dados[0]),
                    dados[1],
                    dados[2],
                    Double.parseDouble(dados[3]),
                    Integer.parseInt(dados[4]),
                    Integer.parseInt(dados[5])
                );

                produtos.add(produto);
            }
        }

        return produtos;
    }

    // Salva as movimentações registradas no arquivo CSV.
    public void salvarMovimentacoes(
            List<Movimentacao> movimentacoes) throws IOException {

        // Cria a pasta de dados caso ela ainda não exista.
        new File("dados").mkdirs();

        // Abre o arquivo de movimentações para escrita.
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(arquivoMovimentacoes))) {

            // Percorre as movimentações e grava seus dados separados por vírgulas.
            for (Movimentacao m : movimentacoes) {
                escritor.write(
                    m.getProduto().getCodigo() + ","
                    + m.getTipo() + ","
                    + m.getQuantidade() + ","
                    + m.getDataHora()
                );

                // Inicia uma nova linha para a próxima movimentação.
                escritor.newLine();
            }
        }
    }

    // Carrega as movimentações e relaciona cada uma ao produto correspondente.
    public List<Movimentacao> carregarMovimentacoes(
            List<Produto> produtos) throws IOException {

        List<Movimentacao> movimentacoes = new ArrayList<>();
        File arquivo = new File(arquivoMovimentacoes);

        // Retorna uma lista vazia caso o arquivo ainda não exista.
        if (!arquivo.exists()) {
            return movimentacoes;
        }

        // Abre o arquivo de movimentações para leitura.
        try (BufferedReader leitor = new BufferedReader(
                new FileReader(arquivo))) {

            String linha;

            // Lê o arquivo linha por linha até o fim.
            while ((linha = leitor.readLine()) != null) {
                // Ignora linhas vazias.
                if (linha.isBlank()) {
                    continue;
                }

                // Separa os campos e obtém o código do produto relacionado.
                String[] dados = linha.split(",", -1);
                int codigo = Integer.parseInt(dados[0]);

                Produto produtoEncontrado = null;

                // Procura na lista o produto que possui o código informado.
                for (Produto p : produtos) {
                    if (p.getCodigo() == codigo) {
                        produtoEncontrado = p;
                        break;
                    }
                }

                // Interrompe a leitura com erro se o produto não for encontrado.
                if (produtoEncontrado == null) {
                    throw new IOException(
                        "Produto não encontrado: " + codigo
                    );
                }

                // Reconstrói a movimentação usando os dados salvos no arquivo.
                Movimentacao movimentacao = new Movimentacao(
                    produtoEncontrado,
                    TipoMovimentacao.valueOf(dados[1]),
                    Integer.parseInt(dados[2])
                );

                // Restaura a data e a hora originais da movimentação.
                movimentacao.setDataHora(
                    LocalDateTime.parse(dados[3])
                );

                movimentacoes.add(movimentacao);
            }
        }

        return movimentacoes;
    }
}