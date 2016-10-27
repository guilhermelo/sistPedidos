package pedidos.vo;

import java.util.Date;

public class PedidoVO {
	private Long idPedido;
	private ContaVO conta;
	private MesaVO mesa;
	private ClienteVO cliente;
	private Date dthrPedido;
	private String observacao;
	
	public Long getIdPedido() {
		return idPedido;
	}
	public ContaVO getConta() {
		return conta;
	}
	public MesaVO getMesa() {
		return mesa;
	}
	public ClienteVO getCliente() {
		return cliente;
	}
	public Date getDthrPedido() {
		return dthrPedido;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public void setConta(ContaVO conta) {
		this.conta = conta;
	}
	public void setMesa(MesaVO mesa) {
		this.mesa = mesa;
	}
	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}
	public void setDthrPedido(Date dthrPedido) {
		this.dthrPedido = dthrPedido;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
