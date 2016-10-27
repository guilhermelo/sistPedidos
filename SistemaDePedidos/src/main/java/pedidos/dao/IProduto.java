package pedidos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pedidos.vo.ProdutoVO;

public interface IProduto {
	
	public void inserirProduto(Connection conn, ProdutoVO produto) throws SQLException;
	
	public void alterarProduto(Connection conn, ProdutoVO produto) throws SQLException;
	
	public void deletarProduto(Connection conn, ProdutoVO produto) throws SQLException;
	
	public List<ProdutoVO> selecionaProdutos(Connection conn) throws SQLException;
	
	/**
	 * Método responsável por selecionar produtos por tipo
	 * @param idTipo Código do tipo
	 * @return
	 * @throws SQLException
	 */
	public List<ProdutoVO> selecionaProdutosPorTipo(Connection conn, Long idTipo) throws SQLException;
}
