package entidades;

public class Produto {
	
	private int codigo;
	private String nome;
	private String categoria;
	private double preco;
	private int quantidade;
	private int estoqueMinimo;
	
	public Produto(int codigo, String nome, String categoria, double preco, int quantidade, int estoqueMinimo) {
		this.codigo = codigo;
		this.nome = nome;
		this.categoria = categoria;
		this.preco = preco;
		this.quantidade = quantidade;
		this.estoqueMinimo = estoqueMinimo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(int estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
	
	@Override
	public String toString() {
		return "Código: " 
				+ codigo
				+ " | Nome: "
				+ nome
				+ " | Quantidade: "
				+ quantidade 
				+ " | Preço: "
				+ preco;
	}
}
