package pedidos.vo;

public class FornecedorVO {
	private Long idFornecedor;
	private String nomeEmpresa;
	private String cnpj;
	private String cidade;
	private String estado;
	
	public Long getIdFornecedor() {
		return idFornecedor;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public String getCidade() {
		return cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
