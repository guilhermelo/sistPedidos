package pedidos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pedidos.vo.ContaVO;
import pedidos.vo.FornecedorVO;

public interface IConta {
	/**
	 * Método responsável por inserir conta
	 * @param conta
	 * @throws SQLException
	 */
	public void insereConta(Connection conn, List<ContaVO> contas) throws SQLException;
	
	/**
	 * Método responsável por alterar conta
	 * @param conta
	 * @throws SQLException
	 */
	public void alteraconta(Connection conn, ContaVO conta) throws SQLException;
	
	/**
	 * Método responsável por capturar novo id da sequence "id_conta"
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public Long capturaIdConta(Connection conn) throws SQLException;
	
	/**
	 * Método responsável por selecionar contas abertas
	 * @return
	 * @throws SQLException
	 */
	public List<ContaVO> selecionaContas() throws SQLException;
}
