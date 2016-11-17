package pedidos.vo;

import java.util.Date;

public class PedidoVO {
	private Long idPedido;
	private FuncionarioVO funcionario;
	private MesaVO mesa;
	private ClienteVO cliente;
	private Date dtHrPedido;
	private String observacao;
	private String status;
	
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public FuncionarioVO getFuncionario() {
		if(this.funcionario == null){
			this.funcionario = new FuncionarioVO();
		}
		return funcionario;
	}
	public void setFuncionario(FuncionarioVO funcionario) {
		this.funcionario = funcionario;
	}
	public MesaVO getMesa() {
		if(this.mesa == null){
			this.mesa = new MesaVO();
		}
		return mesa;
	}
	public void setMesa(MesaVO mesa) {
		this.mesa = mesa;
	}
	public ClienteVO getCliente() {
		if(this.cliente == null){
			this.cliente = new ClienteVO();
		}
		return cliente;
	}
	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}
	public Date getDtHrPedido() {
		return dtHrPedido;
	}
	public void setDtHrPedido(Date dtHrPedido) {
		this.dtHrPedido = dtHrPedido;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
