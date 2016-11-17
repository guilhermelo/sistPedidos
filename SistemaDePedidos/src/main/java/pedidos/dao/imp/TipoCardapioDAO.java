package pedidos.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pedidos.dao.ITipoCardapio;
import pedidos.vo.CardapioVO;
import pedidos.vo.TipoCardapioVO;

public class TipoCardapioDAO implements ITipoCardapio{

	@Override
	public void insereTipo(Connection conn, TipoCardapioVO tipo) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alteraTipo(Connection conn, TipoCardapioVO tipo) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletaTipo(Connection conn, TipoCardapioVO tipo) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TipoCardapioVO> selecionaTipos(Connection conn) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<TipoCardapioVO> tipos = new ArrayList<TipoCardapioVO>();
		
		query.append("SELECT ID_TIPO, DESCRICAO FROM TIPO_CARDAPIO ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				TipoCardapioVO tipo = new TipoCardapioVO();
				
				if(rs.getLong("ID_TIPO") != 0L){
					tipo.setIdTipo(rs.getLong("ID_TIPO"));
				}else{
					tipo.setIdTipo(null);
				}
				
				if(rs.getString("DESCRICAO") != null && !"".contentEquals(rs.getString("DESCRICAO"))){
					tipo.setDescricao(rs.getString("DESCRICAO"));
				}else{
					tipo.setDescricao(null);
				}
				
				tipos.add(tipo);
			}
			
			return tipos;
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public TipoCardapioVO selecionaTipo(Connection conn, Long idTipo) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TipoCardapioVO tipo = null;
		
		query.append("SELECT ID_TIPO, DESCRICAO FROM TIPO_CARDAPIO WHERE ID_TIPO = ?");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			stmt.setLong(1, idTipo);
			rs = stmt.executeQuery();
			
			
			if(rs.next()){
				tipo = new TipoCardapioVO();
				
				if(rs.getLong("ID_TIPO") != 0L){
					tipo.setIdTipo(rs.getLong("ID_TIPO"));
				}else{
					tipo.setIdTipo(null);
				}
				
				if(rs.getString("DESCRICAO") != null && !"".contentEquals(rs.getString("DESCRICAO"))){
					tipo.setDescricao(rs.getString("DESCRICAO"));
				}else{
					tipo.setDescricao(null);
				}
				
			}
			
			return tipo;
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

}
