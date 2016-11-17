package pedidos.vo;

import java.math.BigDecimal;

public class ItemAdapter {
	private String nome;
	private BigDecimal valor;
	private Long quantidade;
	private Long idCardapio;
	private Long idPedido;
	
	public ItemAdapter(ItemPedidoVO vo){
		
		this.nome = vo.getCardapio().getNome();
		this.valor = vo.getCardapio().getValor();
		this.quantidade = vo.getQuantidade();
		this.idCardapio = vo.getCardapio().getIdCardapio();
		this.idPedido = vo.getPedido().getIdPedido();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Long getIdCardapio() {
		return idCardapio;
	}

	public void setIdCardapio(Long idCardapio) {
		this.idCardapio = idCardapio;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
}
