package pedidos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pedidos.vo.CardapioProdutoVO;

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
}
