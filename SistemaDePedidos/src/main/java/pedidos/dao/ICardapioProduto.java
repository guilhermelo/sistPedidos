package pedidos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pedidos.vo.CardapioProdutoVO;
import pedidos.vo.ProdutoVO;

public interface ICardapioProduto {
	
	/**
	 * método responsável por inserir produto utilizado no cardápio
	 * @param cProduto
	 * @throws SQLException
	 */
	public void insereProdutoCardapio(Connection conn, CardapioProdutoVO cProduto) throws SQLException;
	
	/**
	 * Método responsável por selecionar produtosDoCardápio
	 * @param idProduto Código do produto
	 * @return 
	 * @throws SQLException
	 */
	public List<CardapioProdutoVO> selecionaProdutosDoCardapio(Connection conn, Long idCardapio) throws SQLException;
	
	/**
	 * Método responsável por editar produto do cardápio
	 * @param conn
	 * @param cProduto
	 * @throws SQLException
	 */
	public void editaProdutoCardapio(Connection conn, CardapioProdutoVO cProduto) throws SQLException;
	
	/**
	 * Método responsável em verificar se existe produto naquele cardápio através do código do cardápio e produto
	 * @param conn
	 * @param idCardapio
	 * @param idProduto
	 * @return
	 * @throws SQLException
	 */
	public Boolean existeCardapioProduto(Connection conn, Long idCardapio, Long idProduto) throws SQLException;
}
