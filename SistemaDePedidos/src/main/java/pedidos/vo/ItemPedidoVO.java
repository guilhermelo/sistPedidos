package pedidos.vo;

import java.util.Date;

public class ItemPedidoVO {
	
	private PedidoVO pedido;
	private CardapioVO cardapio;
	private Date dthrPedido;
	private Long quantidade;
	
	public ItemPedidoVO(){
		super();
	}
	
	public ItemPedidoVO(PedidoVO pedido, CardapioVO cardapio, Long quantidade){
		this.pedido = pedido;
		this.cardapio = cardapio;
		this.quantidade = quantidade; 
	}
	
	public PedidoVO getPedido() {
		if(this.pedido == null){
			this.pedido = new PedidoVO();
		}
		return pedido;
	}
	public void setPedido(PedidoVO pedido) {
		this.pedido = pedido;
	}
	public CardapioVO getCardapio() {
		if(this.cardapio == null){
			this.cardapio = new CardapioVO();
		}
		return cardapio;
	}
	public void setCardapio(CardapioVO cardapio) {
		this.cardapio = cardapio;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public Date getDthrPedido() {
		return dthrPedido;
	}
	public void setDthrPedido(Date dthrPedido) {
		this.dthrPedido = dthrPedido;
	}
}
