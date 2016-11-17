package pedidos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pedidos.vo.TipoCardapioVO;

public interface ITipoCardapio {
	
	/**
	 * Método responsável por inserir tipo
	 * @param conn
	 * @param tipo
	 * @throws SQLException
	 */
	public void insereTipo(Connection conn, TipoCardapioVO tipo) throws SQLException;
	
	/**
	 * Método responsável por alterar tipo
	 * @param conn
	 * @param tipo
	 * @throws SQLException
	 */
	public void alteraTipo(Connection conn, TipoCardapioVO tipo) throws SQLException; 
	
	/**
	 * Método responsável por deletar tipo
	 * @param conn
	 * @param tipo
	 * @throws SQLException
	 */
	public void deletaTipo(Connection conn, TipoCardapioVO tipo) throws SQLException; 
	
	/**
	 * Método responsável por selecionar tipos
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public List<TipoCardapioVO> selecionaTipos(Connection conn) throws SQLException;
	
	/**
	 * Método para selecionar tipo de cardápio
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public TipoCardapioVO selecionaTipo(Connection conn, Long idTipo) throws SQLException;
	
}
