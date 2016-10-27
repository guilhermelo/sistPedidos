package pedidos.vo;

public class ContaVO {
	
	private Long idConta;
	private Boolean contaStatus;
	private Long valorConta;
	private FormaPagamentoVO pagamento;
	
	public Long getIdConta() {
		return idConta;
	}
	public Boolean getContaStatus() {
		return contaStatus;
	}
	public Long getValorConta() {
		return valorConta;
	}
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	public void setContaStatus(Boolean contaStatus) {
		this.contaStatus = contaStatus;
	}
	public void setValorConta(Long valorConta) {
		this.valorConta = valorConta;
	}
	public FormaPagamentoVO getPagamento() {
		return pagamento;
	}
	public void setPagamento(FormaPagamentoVO pagamento) {
		this.pagamento = pagamento;
	}
	
	
}
