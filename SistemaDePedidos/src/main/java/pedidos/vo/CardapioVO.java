package pedidos.vo;

import java.math.BigDecimal;

public class CardapioVO {
	private Long idCardapio;
	private String nome;
	private BigDecimal valor;
	private TipoCardapioVO tipo;
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getIdCardapio() {
		return idCardapio;
	}
	public void setIdCardapio(Long idCardapio) {
		this.idCardapio = idCardapio;
	}
	public TipoCardapioVO getTipo() {
		if(tipo == null){
			tipo = new TipoCardapioVO();
		}
		return tipo;
	}
	public void setTipo(TipoCardapioVO tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
