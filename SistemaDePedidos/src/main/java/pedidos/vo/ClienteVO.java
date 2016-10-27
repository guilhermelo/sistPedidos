package pedidos.vo;

public class ClienteVO {
	
	private Long idCliente;
	private String nome;
	private String telefone;
	private String bairro;
	private String rua;
	private Long numero;
	
	
	public Long getIdCliente() {
		return idCliente;
	}
	public String getNome() {
		return nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getBairro() {
		return bairro;
	}
	public String getRua() {
		return rua;
	}
	public Long getNumero() {
		return numero;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	} 
	
}
