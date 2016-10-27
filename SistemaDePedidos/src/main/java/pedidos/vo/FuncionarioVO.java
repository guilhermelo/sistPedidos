package pedidos.vo;

public class FuncionarioVO {
	private Long idFuncionario;
	private String nome;
	private String cpf;
	private Integer idade;
	private Long qtdeAtendimento;
	
	public Long getIdFuncionario() {
		return idFuncionario;
	}
	
	public String getNome() {
		return nome;
	}
	public Long getQtdeAtendimento() {
		return qtdeAtendimento;
	}
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setQtdeAtendimento(Long qtdeAtendimento) {
		this.qtdeAtendimento = qtdeAtendimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
}
