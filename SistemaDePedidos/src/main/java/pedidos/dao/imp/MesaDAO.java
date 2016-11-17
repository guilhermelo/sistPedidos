package pedidos.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pedidos.dao.IMesa;
import pedidos.vo.MesaVO;

public class MesaDAO implements IMesa{

	@Override
	public void insereMesa(Connection conn, MesaVO mesa) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
	
		query.append("INSERT INTO MESA (NOME, MESA_STATUS) VALUES (?, ?)");
		
		try{	
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		
			stmt.setString(1, mesa.getNome());
			stmt.setString(2, "L");
			
			stmt.execute();
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public void deletaMesa(Connection conn, MesaVO mesa) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
	
		query.append("DELETE FROM MESA WHERE ID_MESA = ?");
		
		try{	
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		
			stmt.setLong(1, mesa.getIdMesa());
			
			stmt.executeUpdate();
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
		
	}

	@Override
	public List<MesaVO> selecionaMesas(Connection conn) throws SQLException {

		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<MesaVO> mesas = new ArrayList<MesaVO>();

		query.append("SELECT ID_MESA, NOME, MESA_STATUS FROM MESA ORDER BY ID_MESA");

		try {
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			rs = stmt.executeQuery();
			
			while(rs.next()){
				MesaVO mesa =  new MesaVO();
				
				if(rs.getLong("ID_MESA") != 0L){
					mesa.setIdMesa(rs.getLong("ID_MESA"));
				}else{
					mesa.setIdMesa(null);
				}
				
				if(rs.getString("NOME") != null && !"".equals(rs.getString("NOME"))){
					mesa.setNome(rs.getString("NOME"));
				}else{
					mesa.setNome(null);
				}
				
				if(rs.getString("MESA_STATUS") != null && !"".equals(rs.getString("MESA_STATUS"))){
					mesa.setMesaStatus("L".equals(rs.getString("MESA_STATUS")) ? true : false);
				}else{
					mesa.setMesaStatus(null);
				}
				
				mesas.add(mesa);
			}
			
			return mesas;

		} catch (RuntimeException e) {
			throw new SQLException("Erro de runtime: " + e.getMessage());
		} catch (Exception e) {
			throw new SQLException("Erro: " + e.getMessage());
		}

	}

	@Override
	public void atualizaStatusMesa(Connection conn, Long idMesa) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
	
		query.append("UPDATE MESA SET MESA_STATUS = ? WHERE ID_MESA = ?");
		
		try{	
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		
			stmt.setString(1, "O");
			stmt.setLong(2, idMesa);
			
			stmt.executeUpdate();
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
		
	}
	
}
