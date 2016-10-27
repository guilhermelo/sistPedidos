package pedidos.dao.imp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pedidos.dao.ICardapioProduto;
import pedidos.vo.CardapioProdutoVO;
import pedidos.vo.CardapioVO;
import pedidos.vo.ProdutoVO;

public class CardapioProdutoDAO implements ICardapioProduto{

	@Override
	public void insereProdutoCardapio(Connection conn, CardapioProdutoVO cProduto) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CardapioProdutoVO> selecionaProdutosDoCardapio(Connection conn, Long idCardapio) throws SQLException {

		List<CardapioProdutoVO> cardapios = new ArrayList<CardapioProdutoVO>();	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder query = new StringBuilder();
		ProdutoVO produto = null;
		CardapioVO cardapio = null;
		CardapioProdutoVO cProduto = null;
		
		query.append(" SELECT CP.ID_CARDAPIO, CP.ID_PRODUTO, P.NOME, C.VALOR  FROM CARDAPIO C ");  
		query.append(" INNER JOIN CARDAPIO_PRODUTO CP ON(CP.ID_CARDAPIO = C.ID_CARDAPIO) ");
		query.append(" INNER JOIN  PRODUTO P ON(CP.ID_PRODUTO = P.ID_PRODUTO) WHERE CP.ID_CARDAPIO = ? ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			stmt.setLong(1, idCardapio);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				cProduto = new CardapioProdutoVO();
				cardapio = new CardapioVO();
				produto = new ProdutoVO();
				
				if(rs.getLong("ID_CARDAPIO") != 0L){
					produto.setIdProduto(rs.getLong("ID_CARDAPIO"));
				}else{
					produto.setIdProduto(null);
				}
				
				if(rs.getLong("ID_PRODUTO") != 0L){
					produto.setIdProduto(rs.getLong("ID_PRODUTO"));
				}else{
					produto.setIdProduto(null);
				}
				
				if(rs.getString("NOME") != null && !"".equals(rs.getString("NOME"))){
					produto.setNome(rs.getString("NOME"));
				}else{
					produto.setNome(null);
				}
				
				if(rs.getBigDecimal("VALOR") != BigDecimal.ZERO){
					produto.setValor(rs.getBigDecimal("VALOR"));
				}else{
					produto.setValor(null);
				}
				
				cProduto.setCardapio(cardapio);
				cProduto.setProduto(produto);
				
				cardapios.add(cProduto);
			}
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
		
		return cardapios;
	}

}
