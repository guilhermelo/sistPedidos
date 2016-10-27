package pedidos.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pedidos.dao.IFornecedor;
import pedidos.vo.FornecedorVO;

public class FornecedorDAO implements IFornecedor{

	@Override
	public void insereFornecedor(Connection conn, FornecedorVO fornecedor) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("INSERT INTO FORNECEDOR(NOME_EMPRESA, CNPJ, CIDADE, ESTADO) VALUES (?, ?, ?, ?) ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			
			stmt.setString(++index, fornecedor.getNomeEmpresa());
			stmt.setString(++index, fornecedor.getCnpj());
			stmt.setString(++index, fornecedor.getCidade());
			stmt.setString(++index, fornecedor.getEstado());
			
			stmt.execute();
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public void alteraFornecedor(Connection conn, FornecedorVO fornecedor) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("UPDATE FORNECEDOR SET NOME_EMPRESA = ?, CNPJ = ?, CIDADE = ?, ESTADO = ? WHERE ID_FORNECEDOR = ? ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			
			stmt.setString(++index, fornecedor.getNomeEmpresa());
			stmt.setString(++index, fornecedor.getCnpj());
			stmt.setString(++index, fornecedor.getCidade());
			stmt.setString(++index, fornecedor.getEstado());
			stmt.setLong(++index, fornecedor.getIdFornecedor());
			
			stmt.executeUpdate();
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
		
	}

	@Override
	public void deletarFornecedor(Connection conn, FornecedorVO fornecedor) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("DELETE FROM FORNECEDOR WHERE ID_FORNECEDOR = ? ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			stmt.setLong(++index, fornecedor.getIdFornecedor());
			
			stmt.executeUpdate();
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
		
	}

	@Override
	public List<FornecedorVO> selecionaFornecedores(Connection conn) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<FornecedorVO> fornecedores = new ArrayList<FornecedorVO>();
		FornecedorVO fornecedor = null;
		
		query.append("SELECT ID_FORNECEDOR, NOME_EMPRESA, CNPJ, CIDADE, ESTADO FROM FORNECEDOR");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				fornecedor = new FornecedorVO();
				
				if(rs.getLong("ID_FORNECEDOR") != 0L){
					fornecedor.setIdFornecedor(rs.getLong("ID_FORNECEDOR"));
				}else{
					fornecedor.setIdFornecedor(null);
				}
				
				if(rs.getString("NOME_EMPRESA") != null && !"".equals(rs.getString("NOME_EMPRESA"))){
					fornecedor.setNomeEmpresa(rs.getString("NOME_EMPRESA"));
				}else{
					fornecedor.setNomeEmpresa(null);
				}
				
				if(rs.getString("CNPJ") != null && !"".equals(rs.getString("CNPJ"))){
					fornecedor.setCnpj(rs.getString("CNPJ"));
				}else{
					fornecedor.setCnpj(null);
				}
				
				if(rs.getString("CIDADE") != null && !"".equals(rs.getString("CIDADE"))){
					fornecedor.setCidade(rs.getString("CIDADE"));
				}else{
					fornecedor.setCidade(null);
				}
				
				if(rs.getString("ESTADO") != null && !"".equals(rs.getString("ESTADO"))){
					fornecedor.setEstado(rs.getString("ESTADO"));
				}else{
					fornecedor.setEstado(null);
				}
				
				fornecedores.add(fornecedor);
			}
			
			return fornecedores;
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}	
	}

	@Override
	public FornecedorVO selecionaFornecedor(Connection conn, Long idFornecedor) throws SQLException {
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		FornecedorVO fornecedor = null;
		
		query.append("SELECT ID_FORNECEDOR, NOME_EMPRESA, CNPJ, CIDADE, ESTADO FROM FORNECEDOR WHERE ID_FORNECEDOR = ?");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			stmt.setLong(1, idFornecedor);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				fornecedor = new FornecedorVO();
				
				if(rs.getLong("ID_FORNECEDOR") != 0L){
					fornecedor.setIdFornecedor(rs.getLong("ID_FORNECEDOR"));
				}else{
					fornecedor.setIdFornecedor(null);
				}
				
				if(rs.getString("NOME_EMPRESA") != null && !"".equals(rs.getString("NOME_EMPRESA"))){
					fornecedor.setNomeEmpresa(rs.getString("NOME_EMPRESA"));
				}else{
					fornecedor.setNomeEmpresa(null);
				}
				
				if(rs.getString("CNPJ") != null && !"".equals(rs.getString("CNPJ"))){
					fornecedor.setCnpj(rs.getString("CNPJ"));
				}else{
					fornecedor.setCnpj(null);
				}
				
				if(rs.getString("CIDADE") != null && !"".equals(rs.getString("CIDADE"))){
					fornecedor.setCidade(rs.getString("CIDADE"));
				}else{
					fornecedor.setCidade(null);
				}
				
				if(rs.getString("ESTADO") != null && !"".equals(rs.getString("ESTADO"))){
					fornecedor.setEstado(rs.getString("ESTADO"));
				}else{
					fornecedor.setEstado(null);
				}
				
			}
			
			return fornecedor;
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}	
	}

	
	
}
