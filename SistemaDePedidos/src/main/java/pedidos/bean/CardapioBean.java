package pedidos.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pedidos.dao.Conexao;
import pedidos.dao.ICardapio;
import pedidos.dao.ICardapioProduto;
import pedidos.dao.IProduto;
import pedidos.dao.ITipoCardapio;
import pedidos.dao.imp.CardapioDAO;
import pedidos.dao.imp.CardapioProdutoDAO;
import pedidos.dao.imp.ProdutoDAO;
import pedidos.dao.imp.TipoCardapioDAO;
import pedidos.util.PedidosUtil;
import pedidos.vo.CardapioProdutoVO;
import pedidos.vo.CardapioVO;
import pedidos.vo.ProdutoVO;
import pedidos.vo.TipoCardapioVO;

@ManagedBean
@ViewScoped
public class CardapioBean {

	private CardapioVO cardapio = new CardapioVO();
	private CardapioVO selectedCardapio = new CardapioVO();
	private List<CardapioProdutoVO> cProdutos = new ArrayList<CardapioProdutoVO>();
	private Long idTipo;

	public CardapioVO getCardapio() {
		return cardapio;
	}

	public List<CardapioVO> selecionaCardapios() throws Exception {

		ICardapio dao = new CardapioDAO();

		try {
			Connection conn = Conexao.getConexao();

			return dao.selecionaRefeicoes(conn);
		} catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	public List<TipoCardapioVO> selecionaTiposDeCardapios() throws Exception {

		ITipoCardapio dao = new TipoCardapioDAO();

		try {
			Connection conn = Conexao.getConexao();

			return dao.selecionaTipos(conn);
		} catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}
	}
	
	public void insereCardapio() throws Exception{
		
		cardapio.getTipo().setIdTipo(idTipo);
		
		ICardapio dao = new CardapioDAO();

		try {
			Connection conn = Conexao.getConexao();

			dao.insereCardapio(conn, cardapio);
			
			PedidosUtil.addMessage("success", "Sucesso", "Cardápio inserido com sucesso!");
			
			cardapio = new CardapioVO();
		} catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}
	}
	
	
	public List<CardapioProdutoVO> selecionaProdutosDoCardapio() throws Exception{
		
		Long idCardapio = selectedCardapio.getIdCardapio();
		List<CardapioProdutoVO> cardapios = new ArrayList<CardapioProdutoVO>();
		
		if(idCardapio != null){
			ICardapioProduto dao = new CardapioProdutoDAO();
			
			try{
				Connection conn = Conexao.getConexao();
				
				 cardapios = dao.selecionaProdutosDoCardapio(conn, idCardapio);
			} catch (SQLException e) {
				throw new SQLException("Erro: " + e.getMessage());
			}
		}	
		
		return cardapios;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public CardapioVO getSelectedCardapio() {
		return selectedCardapio;
	}

	public void setSelectedCardapio(CardapioVO selectedCardapio) {
		this.selectedCardapio = selectedCardapio;
	}

	public List<CardapioProdutoVO> getcProdutos() {
		return cProdutos;
	}

	public void setcProdutos(List<CardapioProdutoVO> cProdutos) {
		this.cProdutos = cProdutos;
	}
}
