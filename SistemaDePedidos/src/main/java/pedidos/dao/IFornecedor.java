package pedidos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pedidos.vo.FornecedorVO;

public interface IFornecedor {
	
	/**
	 * Método responsável por inserir fornecedor
	 * @throws SQLException
	 */
	public void insereFornecedor(Connection conn, FornecedorVO fornecedor) throws SQLException;
	
	/**
	 * Método responsável por alterar dados do fornecedor
	 * @throws SQLException
	 */
	public void alteraFornecedor(Connection conn, FornecedorVO fornecedor) throws SQLException;
	
	/**
	 * Método responsável por deletar fornecedor
	 * @throws SQLException
	 */
	public void deletarFornecedor(Connection conn, FornecedorVO fornecedor) throws SQLException;
	
	/**
	 * Método responsável por selecionar fornecedores
	 * @return Lista de fornecedores
	 * @throws SQLException
	 */
	public List<FornecedorVO> selecionaFornecedores(Connection conn) throws SQLException;
	
	/**
	 * Método responsável por selecionar fornecedor por código
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public FornecedorVO selecionaFornecedor(Connection conn, Long idFornecedor) throws SQLException;
}
