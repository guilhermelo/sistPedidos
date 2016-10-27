package pedidos.vo;

public class CardapioProdutoVO {
	private CardapioVO cardapio;
	private ProdutoVO produto;
	private Long quantidade;
	
	public CardapioVO getCardapio() {
		if(cardapio == null){
			cardapio = new CardapioVO();
		}
		return cardapio;
	}
	
	public void setCardapio(CardapioVO cardapio) {
		this.cardapio = cardapio;
	}
	
	public ProdutoVO getProduto() {
		if(produto == null){
			produto = new ProdutoVO();
		}
		return produto;
	}
	
	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
}
