package pedidos.vo;

public class MesaVO {
	private String nome;
	private Long idMesa;
	private Boolean mesaStatus;
	
	public Long getIdMesa() {
		return idMesa;
	}
	public Boolean getMesaStatus() {
		return mesaStatus;
	}
	public void setIdMesa(Long idMesa) {
		this.idMesa = idMesa;
	}
	public void setMesaStatus(Boolean mesaStatus) {
		this.mesaStatus = mesaStatus;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
