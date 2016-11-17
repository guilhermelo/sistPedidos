package pedidos.vo;

import java.math.BigDecimal;

public class ContaVO {
	
	private Long idConta;
	private Boolean contaStatus;
	private BigDecimal valorConta;
	private FormaPagamentoVO pagamento;
	private PedidoVO pedido;
	
	public Long getIdConta() {
		return idConta;
	}
	public Boolean getContaStatus() {
		return contaStatus;
	}
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	public void setContaStatus(Boolean contaStatus) {
		this.contaStatus = contaStatus;
	}
	public FormaPagamentoVO getPagamento() {
		return pagamento;
	}
	public void setPagamento(FormaPagamentoVO pagamento) {
		this.pagamento = pagamento;
	}
	public BigDecimal getValorConta() {
		return valorConta;
	}
	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}
	public PedidoVO getPedido() {
		return pedido;
	}
	public void setPedido(PedidoVO pedido) {
		this.pedido = pedido;
	}
	
	
}
