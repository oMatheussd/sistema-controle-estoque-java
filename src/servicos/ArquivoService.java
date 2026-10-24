
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

public class ArquivoService {

    private final String arquivoProdutos = "dados/produtos.csv";
    private final String arquivoMovimentacoes = "dados/movimentacoes.csv";

    // Salvar produtos
    public void salvarProdutos(List<Produto> produtos)
            throws IOException {

        new File("dados").mkdirs();

        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(arquivoProdutos))) {

            for (Produto p : produtos) {
                escritor.write(
                    p.getCodigo() + ","
                    + p.getNome() + ","
                    + p.getCategoria() + ","
                    + p.getPreco() + ","
                    + p.getQuantidade() + ","
                    + p.getEstoqueMinimo()
                );

                escritor.newLine();
            }
        }
    }

    // Carregar produtos
    public List<Produto> carregarProdutos() throws IOException {

        List<Produto> produtos = new ArrayList<>();
        File arquivo = new File(arquivoProdutos);

        if (!arquivo.exists()) {
            return produtos;
        }

        try (BufferedReader leitor = new BufferedReader(
                new FileReader(arquivo))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.isBlank()) {
                    continue;
                }

                String[] dados = linha.split(",", -1);

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

    // Salvar movimentações
    public void salvarMovimentacoes(
            List<Movimentacao> movimentacoes) throws IOException {

        new File("dados").mkdirs();

        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(arquivoMovimentacoes))) {

            for (Movimentacao m : movimentacoes) {
                escritor.write(
                    m.getProduto().getCodigo() + ","
                    + m.getTipo() + ","
                    + m.getQuantidade() + ","
                    + m.getDataHora()
                );

                escritor.newLine();
            }
        }
    }

    // Carregar movimentações
    public List<Movimentacao> carregarMovimentacoes(
            List<Produto> produtos) throws IOException {

        List<Movimentacao> movimentacoes = new ArrayList<>();
        File arquivo = new File(arquivoMovimentacoes);

        if (!arquivo.exists()) {
            return movimentacoes;
        }

        try (BufferedReader leitor = new BufferedReader(
                new FileReader(arquivo))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.isBlank()) {
                    continue;
                }

                String[] dados = linha.split(",", -1);
                int codigo = Integer.parseInt(dados[0]);

                Produto produtoEncontrado = null;

                for (Produto p : produtos) {
                    if (p.getCodigo() == codigo) {
                        produtoEncontrado = p;
                        break;
                    }
                }

                if (produtoEncontrado == null) {
                    throw new IOException(
                        "Produto não encontrado: " + codigo
                    );
                }

                Movimentacao movimentacao = new Movimentacao(
                    produtoEncontrado,
                    TipoMovimentacao.valueOf(dados[1]),
                    Integer.parseInt(dados[2])
                );

                movimentacao.setDataHora(
                    LocalDateTime.parse(dados[3])
                );

                movimentacoes.add(movimentacao);
            }
        }

        return movimentacoes;
    }
}
