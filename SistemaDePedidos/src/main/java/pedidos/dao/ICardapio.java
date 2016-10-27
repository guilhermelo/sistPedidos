package pedidos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pedidos.vo.CardapioVO;

public interface ICardapio {
	
	/**
	 * Método responsável por inserir cardapio
	 * @param conn Conexão com o Banco de Dados
	 * @param refeicao
	 * @throws SQLException
	 */
	public void insereCardapio(Connection conn, CardapioVO cardapio) throws SQLException;
	
	/**
	 * Método responsável por deletar cardapio
	 * @param conn Conexão com o Banco de Dados
	 * @param cardapio
	 * @throws SQLException
	 */
	public void deletarRefeicao(Connection conn, CardapioVO cardapio) throws SQLException;
	
	/**
	 * Método responsável por alterar dados da cardapio
	 * @param conn Conexão com o Banco de Dados 
	 * @param cardapio
	 * @throws SQLException
	 */
	public void alterarRefeicao(Connection conn, CardapioVO cardapio) throws SQLException;
	
	/**
	 * Método responsável por selecionar cardapios
	 * @param conn Conexão com o Banco de Dados
	 * @return
	 * @throws SQLException
	 */
	public List<CardapioVO> selecionaRefeicoes(Connection conn) throws SQLException;
}
