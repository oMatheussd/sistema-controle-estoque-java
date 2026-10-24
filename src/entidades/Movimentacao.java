package entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Representa uma movimentação de entrada ou saída de um produto no estoque.
public class Movimentacao {

    // Armazena o produto, o tipo de movimentação, a quantidade e a data e hora.
	private Produto produto;
	private TipoMovimentacao tipo;
	private int quantidade;
	private LocalDateTime dataHora;

    // Inicializa a movimentação com os dados recebidos e registra o momento atual.
	public Movimentacao(Produto produto, TipoMovimentacao tipo, int quantidade) {
		this.produto = produto;
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.dataHora = LocalDateTime.now();
	}

    // Retorna o produto relacionado à movimentação.
	public Produto getProduto() {
		return produto;
	}

    // Retorna o tipo da movimentação.
	public TipoMovimentacao getTipo() {
		return tipo;
	}

    // Altera o tipo da movimentação.
	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

    // Retorna a quantidade movimentada.
	public int getQuantidade() {
		return quantidade;
	}

    // Retorna a data e hora da movimentação.
	public LocalDateTime getDataHora() {
		return dataHora;
	}

    // Permite definir a data e hora da movimentação.
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

    // Formata e apresenta os dados da movimentação como texto.
	@Override
	public String toString() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	    return String.format("%-15s | %-7s | %-10d | %s",
	            produto.getNome(),
	            tipo,
	            quantidade,
	            dataHora.format(formatador));
	}
}