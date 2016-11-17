package pedidos.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import pedidos.dao.ICliente;
import pedidos.vo.ClienteVO;

public class ClienteDAO implements ICliente{

	public void inserirCliente(Connection conn, ClienteVO cliente) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("INSERT INTO CLIENTE (NOME, TELEFONE, BAIRRO, RUA, NUMERO) VALUES (?,?,?,?,?)");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			stmt.setString(++index, cliente.getNome());
			stmt.setString(++index, cliente.getTelefone());
			stmt.setString(++index, cliente.getBairro());
			stmt.setString(++index, cliente.getRua());
			if(cliente.getNumero() == null){
				stmt.setLong(++index, Types.NUMERIC);
			}else{
				stmt.setLong(++index, cliente.getNumero());
			}
			
			
			stmt.execute();
			
		}catch (RuntimeException e) {
			throw new SQLException("Erro de Runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}finally{
			if(stmt != null){
				stmt.close();
			}
		}
		
	}

	public void alterarCliente(Connection conn, ClienteVO cliente) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("UPDATE CLIENTE SET NOME = ?, TELEFONE = ?, BAIRRO = ?, RUA = ?, NUMERO = ? ");
		query.append(" WHERE ID_CLIENTE = ?");
		
		try{
			
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getBairro());
			stmt.setString(4, cliente.getRua());
			stmt.setLong(5, cliente.getNumero());
			stmt.setLong(6, cliente.getIdCliente());
			
			stmt.executeUpdate();
		}catch (RuntimeException e) {
			throw new SQLException(e.getMessage());
		}catch (Exception e) {
			throw new SQLException(e.getMessage());
		}finally{
			if(stmt != null){
				stmt.close();
			}
		}
	}

	public void deletarCliente(Connection conn, ClienteVO cliente) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("DELETE FROM CLIENTE WHERE ID_CLIENTE = ?");
		
		try{
			
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			stmt.setLong(1, cliente.getIdCliente());
			
			stmt.executeUpdate();
		}catch (RuntimeException e) {
			throw new SQLException(e.getMessage());
		}catch (Exception e) {
			throw new SQLException(e.getMessage());
		}finally{
			if(stmt != null){
				stmt.close();
			}
		}
	}

	public List<ClienteVO> selecionaClientes(Connection conn) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		List<ClienteVO> clientes = new ArrayList<ClienteVO>();
		ResultSet rs = null;
		
		query.append(" SELECT ID_CLIENTE, NOME, TELEFONE, BAIRRO, RUA, NUMERO FROM CLIENTE ");
		
		try{
			
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				ClienteVO cliente = new ClienteVO();
				
				if(rs.getLong("ID_CLIENTE") != 0L){
					cliente.setIdCliente(rs.getLong("ID_CLIENTE"));
				}else{
					cliente.setIdCliente(null);
				}
				
				if(rs.getString("NOME") != null && !"".equals(rs.getString("NOME"))){
					cliente.setNome(rs.getString("NOME"));
				}else{
					cliente.setNome(null);
				}
				
				if(rs.getString("TELEFONE") != null && !"".equals(rs.getString("TELEFONE"))){
					cliente.setTelefone(rs.getString("TELEFONE"));
				}else{
					cliente.setTelefone(null);
				}
				
				if(rs.getString("BAIRRO") != null && !"".equals(rs.getString("BAIRRO"))){
					cliente.setBairro(rs.getString("BAIRRO"));
				}else{
					cliente.setBairro(null);
				}
				
				if(rs.getString("RUA") != null && !"".equals(rs.getString("RUA"))){
					cliente.setRua(rs.getString("RUA"));
				}else{
					cliente.setRua(null);
				}
				
				if(rs.getString("NUMERO") != null && !"".equals(rs.getString("NUMERO"))){
					cliente.setNumero(rs.getLong("NUMERO"));
				}else{
					cliente.setNumero(null);
				}
				
				clientes.add(cliente);
			}
		}catch (RuntimeException e) {
			throw new SQLException(e.getMessage());
		}catch (Exception e) {
			throw new SQLException(e.getMessage());
		}finally{
			if(stmt != null){
				stmt.close();
			}
		}
		
		return clientes;
	}

}
