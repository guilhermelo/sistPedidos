package pedidos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pedidos.vo.FuncionarioVO;

public interface IFuncionario {
	
	public void insereFuncionario(Connection conn, FuncionarioVO funcionario) throws SQLException;
	
	public void alteraFuncionario(Connection conn, FuncionarioVO funcionario) throws SQLException;
	
	public void deletaFuncionario(Connection conn, FuncionarioVO funcionario) throws SQLException;
	
	public List<FuncionarioVO> selecionaFuncionarios(Connection conn) throws SQLException;
}
