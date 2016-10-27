package pedidos.vo;

import java.math.BigDecimal;

public class ProdutoVO {
	private Long idProduto;
	private FornecedorVO fornecedor;
	private String nome;
	private BigDecimal valor;
	
	public Long getIdProduto() {
		return idProduto;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public FornecedorVO getFornecedor() {
		if(fornecedor == null){
			fornecedor = new FornecedorVO();
		}
		return fornecedor;
	}
	
	public void setFornecedor(FornecedorVO fornecedor) {
		this.fornecedor = fornecedor;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
