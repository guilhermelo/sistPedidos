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
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		query.append("INSERT INTO CARDAPIO_PRODUTO (ID_CARDAPIO, ID_PRODUTO, QUANTIDADE) VALUES (?, ?, ?) ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			
			stmt.setLong(++index, cProduto.getCardapio().getIdCardapio());
			stmt.setLong(++index, cProduto.getProduto().getIdProduto());
			stmt.setLong(++index, cProduto.getQuantidade());
			
			stmt.execute();
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public List<CardapioProdutoVO> selecionaProdutosDoCardapio(Connection conn, Long idCardapio) throws SQLException {

		List<CardapioProdutoVO> cardapios = new ArrayList<CardapioProdutoVO>();	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder query = new StringBuilder();
		ProdutoVO produto = null;
		CardapioProdutoVO cProduto = null;
		CardapioVO cardapio = null;
		
		query.append(" SELECT CP.ID_CARDAPIO, CP.ID_PRODUTO, P.NOME, CP.QUANTIDADE  FROM CARDAPIO C ");  
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
					cardapio.setIdCardapio(rs.getLong("ID_CARDAPIO"));
				}else{
					cardapio.setIdCardapio(null);
				}
				
				if(rs.getLong("QUANTIDADE") != 0L){
					cProduto.setQuantidade(rs.getLong("QUANTIDADE"));
				}else{
					cProduto.setQuantidade(null);
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

	@Override
	public void editaProdutoCardapio(Connection conn, CardapioProdutoVO cProduto) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("UPDATE CARDAPIO_PRODUTO SET QUANTIDADE = ? WHERE ID_PRODUTO = ? AND ID_CARDAPIO = ? ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			stmt.setLong(++index, cProduto.getQuantidade());
			stmt.setLong(++index, cProduto.getProduto().getIdProduto());
			stmt.setLong(++index, cProduto.getCardapio().getIdCardapio());
			
			stmt.execute();
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public Boolean existeCardapioProduto(Connection conn, Long idCardapio, Long idProduto) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Long result = 0L;
		
		query.append(" SELECT ID_CARDAPIO FROM CARDAPIO_PRODUTO WHERE ID_CARDAPIO = ? AND ID_PRODUTO = ? ");
		
		try{
			
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			stmt.setLong(1, idCardapio);
			stmt.setLong(2, idProduto);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				if(rs.getLong("ID_CARDAPIO") != 0L){
					return true; 
				}
			}
			
			return false;
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}	
	}

}
