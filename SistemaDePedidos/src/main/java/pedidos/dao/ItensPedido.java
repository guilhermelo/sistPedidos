package pedidos.dao;

import java.util.List;

import pedidos.vo.ItemPedidoVO;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItensPedido {
	
	/**
	 * Método responsável por inserir itens de um pedido
	 * @param conn
	 * @param itens
	 * @throws SQLException
	 */
	public void insereItensPedido(Connection conn, List<ItemPedidoVO> itens) throws SQLException;
}
