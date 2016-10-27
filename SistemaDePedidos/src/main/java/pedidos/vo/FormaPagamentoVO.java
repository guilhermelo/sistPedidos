package pedidos.vo;

public class FormaPagamentoVO {
	private Long idFormaPagamento;
	private String descricao;
	private Long quantidade;
	
	public Long getIdFormaPagamento() {
		return idFormaPagamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setIdFormaPagamento(Long idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
}
