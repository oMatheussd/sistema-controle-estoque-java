package entidades;

import java.time.LocalDateTime;

public class Movimentacao {
	
	private Produto produto;
	private TipoMovimentacao tipo;
	private int quantidade;
	private LocalDateTime dataHora;
	
	public Movimentacao(Produto produto, TipoMovimentacao tipo, int quantidade) {
		this.produto = produto;
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.dataHora = LocalDateTime.now();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
}
