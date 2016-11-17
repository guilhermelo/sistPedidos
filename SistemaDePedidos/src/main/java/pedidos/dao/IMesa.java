package pedidos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pedidos.vo.MesaVO;

public interface IMesa {
	/**
	 * Método responsável por inserir mesa
	 * @param mesa
	 * @throws SQLException
	 */
	public void insereMesa(Connection conn, MesaVO mesa) throws SQLException;
	
	/**
	 * Método responsável por deletar mesa
	 * @param mesa
	 * @throws SQLException
	 */
	public void deletaMesa(Connection conn, MesaVO mesa) throws SQLException;

	/**
	 * Método reponsável por selecionar todas mesas cadastradas do local
	 * @param conn
	 * @throws SQLException
	 */
	public List<MesaVO> selecionaMesas(Connection conn) throws SQLException;
	
	/**
	 * Método para atualizar status da mesa ao fazer pedido e ao fechar conta da mesa
	 * @throws SQLException
	 */
	public void atualizaStatusMesa(Connection conn, Long idMesa) throws SQLException;
}
