package pedidos.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pedidos.dao.IFuncionario;
import pedidos.vo.FuncionarioVO;

public class FuncionarioDAO implements IFuncionario {

	@Override
	public void insereFuncionario(Connection conn, FuncionarioVO funcionario) throws SQLException {

		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("INSERT INTO FUNCIONARIO (NOME, CPF, IDADE, QTDE_ATENDIMENTO) VALUES (?, ?, ?, ?)");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			stmt.setString(++index, funcionario.getNome());
			stmt.setString(++index, funcionario.getCpf());
			stmt.setInt(++index, funcionario.getIdade());
			stmt.setLong(++index, 0L);
			
			stmt.execute();
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}

	}

	@Override
	public void alteraFuncionario(Connection conn, FuncionarioVO funcionario) throws SQLException {
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("UPDATE FUNCIONARIO SET NOME = ?, CPF = ?, IDADE = ? WHERE ID_FUNCIONARIO = ?");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			stmt.setString(++index, funcionario.getNome());
			stmt.setString(++index, funcionario.getCpf());
			stmt.setInt(++index, funcionario.getIdade());
			stmt.setLong(++index, funcionario.getIdFuncionario());
			
			stmt.executeUpdate();
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}

	}

	@Override
	public void deletaFuncionario(Connection conn, FuncionarioVO funcionario) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("DELETE FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ? ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			stmt.setLong(1, funcionario.getIdFuncionario());
			
			stmt.executeUpdate();
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}

	}

	@Override
	public List<FuncionarioVO> selecionaFuncionarios(Connection conn) throws SQLException {
		
		List<FuncionarioVO> funcionarios = new ArrayList<FuncionarioVO>();
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		query.append(" SELECT ID_FUNCIONARIO, NOME, CPF, IDADE, QTDE_ATENDIMENTO FROM FUNCIONARIO ");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				FuncionarioVO funcionario = new FuncionarioVO();
				
				if(rs.getLong("ID_FUNCIONARIO") != 0L){
					funcionario.setIdFuncionario(rs.getLong("ID_FUNCIONARIO"));
				}else{
					funcionario.setIdFuncionario(null);
				}
				
				if(rs.getString("NOME") != null && !"".equals(rs.getString("NOME"))){
					funcionario.setNome(rs.getString("NOME"));
				}else{
					funcionario.setNome(null);
				}
				
				if(rs.getString("CPF") != null && !"".equals(rs.getString("CPF"))){
					funcionario.setCpf(rs.getString("CPF"));
				}else{
					funcionario.setCpf(null);
				}
				
				if(rs.getInt("IDADE") != 0){
					funcionario.setIdade(rs.getInt("IDADE"));
				}else{
					funcionario.setIdade(null);
				}
				
				if(rs.getLong("QTDE_ATENDIMENTO") >= 0){
					funcionario.setQtdeAtendimento(rs.getLong("QTDE_ATENDIMENTO"));
				}else{
					funcionario.setQtdeAtendimento(null);
				}
				
				funcionarios.add(funcionario);
			}
		}catch(RuntimeException e){
			throw new SQLException("Erro de Runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
		
		return funcionarios;
	}

}
