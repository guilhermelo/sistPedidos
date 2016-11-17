package pedidos.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import pedidos.dao.IConta;
import pedidos.vo.ContaVO;

public class ContaDAO implements IConta{

	@Override
	public void insereConta(Connection conn, List<ContaVO> contas) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append(" INSERT INTO CONTA(CONTA_STATUS, VALOR_CONTA, ID_PEDIDO, IF_FORM_PAG) VALUES (?, ?, ?, ?) ");
		
		try{
			
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index;
			
			for(ContaVO c : contas){
				index = 0;
				stmt.setString(++index, "F");
				stmt.setBigDecimal(++index, c.getValorConta());
				stmt.setLong(++index, c.getPedido().getIdPedido());
				stmt.setLong(++index, 1);
				
				stmt.addBatch();
			}
			
			stmt.executeBatch();
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public void alteraconta(Connection conn, ContaVO conta) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long capturaIdConta(Connection conn) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Long id = null;
		
		query.append(" select nextval('id_conta') as id ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				id = rs.getLong("id");
			}
			
			return id;
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}

	}

	@Override
	public List<ContaVO> selecionaContas() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
