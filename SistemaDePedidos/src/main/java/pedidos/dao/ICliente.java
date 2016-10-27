package pedidos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pedidos.vo.ClienteVO;

public interface ICliente {
	
	/**
	 * Método responsável por inserir cliente
	 * @param conn Conexão com o Banco de Dados
	 * @param cliente
	 * @throws SQLException
	 */
	public void inserirCliente(Connection conn, ClienteVO cliente) throws SQLException;
	
	/**
	 * Método responsável por alterar cliente
	 * @param conn Conexão com o Banco de Dados
	 * @param cliente
	 * @throws SQLException
	 */
	public void alterarCliente(Connection conn, ClienteVO cliente) throws SQLException;
	
	/**
	 * Método responsável por deletar cliente
	 * @param conn Conexão com o Banco de Dados
	 * @param cliente
	 * @throws SQLException
	 */
	public void deletarCliente(Connection conn, ClienteVO cliente) throws SQLException;
	
	/**
	 * Método responsável por selecionar clientes cadastrados
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public List<ClienteVO> selecionaClientes(Connection conn) throws SQLException;
}
