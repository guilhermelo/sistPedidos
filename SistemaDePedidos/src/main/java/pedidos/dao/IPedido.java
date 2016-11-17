package pedidos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pedidos.vo.ItemPedidoVO;
import pedidos.vo.PedidoVO;

public interface IPedido {
	
	/**
	 * Método responsável por inserir pedido
	 * @param conn
	 * @param pedido
	 * @throws SQLException
	 */
	public void inserePedido(Connection conn, PedidoVO pedido) throws SQLException;
	
	/**
	 * Método responsável por selecionar id do pedido
	 * @param conn
	 * @param pedido
	 * @return
	 * @throws SQLException
	 */
	public Long selecionaIdPedido(Connection conn, PedidoVO pedido) throws SQLException;
	
	/**
	 * Seleciona pedidos realizados na mesa
	 * @param conn
	 * @param idMesa
	 * @return
	 * @throws SQLException
	 */
	public List<ItemPedidoVO> selecionaPedidosDaMesaOcupada(Connection conn, Long idMesa) throws SQLException;

	/**
	 * Atualiza status do pedido após ser finalizado
	 * @param conn
	 * @throws SQLException
	 */
	public void atualizaStatusPedido(Connection conn, Long idPedido) throws SQLException;
}
