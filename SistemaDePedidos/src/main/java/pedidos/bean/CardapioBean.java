package pedidos.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.smartcardio.Card;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

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
	private Boolean cardapioSelecionado;
	private ProdutoVO produto = new ProdutoVO();
	private ProdutoVO selectedProduto = new ProdutoVO();
	private CardapioProdutoVO selectCProduto = new CardapioProdutoVO();

	public CardapioVO getCardapio() {
		return cardapio;
	}

	public List<CardapioVO> selecionaCardapios() throws Exception {

		ICardapio dao = new CardapioDAO();
		List<CardapioVO> cardapios = new ArrayList<CardapioVO>();

		try {
			Connection conn = Conexao.getConexao();
			
			cardapios = dao.selecionaRefeicoes(conn);
			
			if(cardapios != null){
				this.cardapioSelecionado = true;
			}
			
			return cardapios;
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
		selectCProduto.getCardapio().setIdCardapio(idCardapio);
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
		
		if(cardapios != null){
			this.cardapioSelecionado = false; 
			cardapio = selectedCardapio;
		}
		
		return cardapios;
	}
	
	public void editarCardapio(CardapioVO cardapio) throws Exception{
		
		ICardapio dao = new CardapioDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.alterarCardapio(conn, cardapio);
			
			PedidosUtil.addMessage("success", "Sucesso", "Cardápio alterado com sucesso!");
		}catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}
	}
	
	
	public void selecionaTipo(Long idTipo) throws Exception{
		
		ITipoCardapio dao = new TipoCardapioDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			this.selectedCardapio.setTipo(dao.selecionaTipo(conn, idTipo));
			
			PedidosUtil.addMessage("success", "Sucesso", "Cardápio alterado com sucesso!");
		}catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}
	}
	
	public void deletaCardapio(CardapioVO cardapio) throws Exception{
		
		ICardapio dao = new CardapioDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.deletarCardapio(conn, cardapio);
			
			PedidosUtil.addMessage("success", "Sucesso", "Cardápio deletado com sucesso!");
		}catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}
	}
	
	public void editaProduto(CardapioProdutoVO cProduto) throws Exception{
		
		ICardapioProduto dao = new CardapioProdutoDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.editaProdutoCardapio(conn, cProduto);
		
			PedidosUtil.addMessage("success", "Sucesso", "Produto alterado com sucesso!");
		}catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}
	}
	
	public void inserirProduto() throws Exception{
		
		ICardapioProduto dao = new CardapioProdutoDAO();
		ICardapioProduto daoCProduto = new CardapioProdutoDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			if(daoCProduto.existeCardapioProduto(conn, selectCProduto.getCardapio().getIdCardapio(), selectCProduto.getProduto().getIdProduto())){
				PedidosUtil.addMessage("danger", "Erro", "Produto já existente no cardápio atual!");
				
				System.out.println("Produto já inserido.");
			}else{
				dao.insereProdutoCardapio(conn, selectCProduto);
				
				PedidosUtil.addMessage("success", "Sucesso", "Produto inserido com sucesso!");
				
				zerarCampos();
				
				this.selectedCardapio.setIdCardapio(this.selectCProduto.getCardapio().getIdCardapio());
				
				this.selecionaProdutosDoCardapio();
			}
			
		}catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}
	}
	
	public void ajustaCampos() throws Exception{
		
		this.selectCProduto.getProduto().setNome(this.selectedProduto.getNome());
		this.selectCProduto.getProduto().setIdProduto(this.selectedProduto.getIdProduto());
	}
	
	private void zerarCampos() throws Exception{
		this.selectCProduto.getProduto().setNome(null);
		this.selectCProduto.setQuantidade(null);
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

	public Boolean getCardapioSelecionado() {
		return cardapioSelecionado;
	}

	public void setCardapioSelecionado(Boolean cardapioSelecionado) {
		this.cardapioSelecionado = cardapioSelecionado;
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	public ProdutoVO getSelectedProduto() {
		return selectedProduto;
	}

	public void setSelectedProduto(ProdutoVO selectedProduto) {
		this.selectedProduto = selectedProduto;
	}

	public CardapioProdutoVO getSelectCProduto() {
		return selectCProduto;
	}
}
