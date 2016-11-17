package pedidos.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pedidos.dao.ICardapio;
import pedidos.vo.CardapioVO;

public class CardapioDAO implements ICardapio {

	@Override
	public void insereCardapio(Connection conn, CardapioVO cardapio) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("INSERT INTO CARDAPIO (NOME, VALOR, ID_TIPO) VALUES (?,?,?) ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			stmt.setString(++index, cardapio.getNome());		
			stmt.setBigDecimal(++index, cardapio.getValor());		
			stmt.setLong(++index, cardapio.getTipo().getIdTipo());		
			
			stmt.execute();
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public void deletarCardapio(Connection conn, CardapioVO cardapio) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append(" DELETE FROM CARDAPIO WHERE ID_CARDAPIO = ? AND ID_TIPO = ? ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			stmt.setLong(++index, cardapio.getIdCardapio());
			stmt.setLong(++index, cardapio.getTipo().getIdTipo());		
			
			stmt.execute();
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public void alterarCardapio(Connection conn, CardapioVO cardapio) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("UPDATE CARDAPIO SET NOME = ?, VALOR = ?, ID_TIPO = ? WHERE ID_CARDAPIO = ? ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			stmt.setString(++index, cardapio.getNome());
			stmt.setBigDecimal(++index, cardapio.getValor());
			stmt.setLong(++index, cardapio.getTipo().getIdTipo());
			stmt.setLong(++index, cardapio.getIdCardapio());
			
			stmt.executeUpdate();
			
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}

	}

	@Override
	public List<CardapioVO> selecionaRefeicoes(Connection conn) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CardapioVO> cardapios = new ArrayList<CardapioVO>();
		
		query.append("SELECT C.ID_CARDAPIO, C.NOME, C.VALOR, C.ID_TIPO, T.DESCRICAO FROM CARDAPIO C ");
		query.append(" INNER JOIN TIPO_CARDAPIO T ON (T.ID_TIPO = C.ID_TIPO) ORDER BY C.NOME");
		
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				CardapioVO cardapio = new CardapioVO();
				
				if(rs.getLong("ID_CARDAPIO") != 0L){
					cardapio.setIdCardapio(rs.getLong("ID_CARDAPIO"));
				}else{
					cardapio.setIdCardapio(null);
				}
				
				if(rs.getString("NOME") != null && !"".contentEquals(rs.getString("NOME"))){
					cardapio.setNome(rs.getString("NOME"));
				}else{
					cardapio.setNome(null);
				}
				
				if(rs.getBigDecimal("VALOR") != null){
					cardapio.setValor(rs.getBigDecimal("VALOR"));
				}else{
					cardapio.setValor(null);
				}
				
				if(rs.getLong("ID_TIPO") != 0L){
					cardapio.getTipo().setIdTipo(rs.getLong("ID_TIPO"));
				}else{
					cardapio.getTipo().setIdTipo(null);
				}
				
				if(rs.getString("DESCRICAO") != null && !"".equals(rs.getString("DESCRICAO"))){
					cardapio.getTipo().setDescricao(rs.getString("DESCRICAO"));
				}else{
					cardapio.getTipo().setDescricao(null);
				}
				
				cardapios.add(cardapio);
			}
			
			return cardapios;
			
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

}
