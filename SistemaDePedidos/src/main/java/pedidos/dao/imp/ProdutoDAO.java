package pedidos.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pedidos.dao.IProduto;
import pedidos.vo.ProdutoVO;

public class ProdutoDAO implements IProduto{

	@Override
	public void inserirProduto(Connection conn, ProdutoVO produto) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("INSERT INTO PRODUTO (NOME, VALOR, ID_FORNECEDOR) VALUES (?,?,?)");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			stmt.setString(++index, produto.getNome());
			stmt.setBigDecimal(++index, produto.getValor());
			stmt.setLong(++index, produto.getFornecedor().getIdFornecedor());
			stmt.execute();
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
		
	}

	@Override
	public void alterarProduto(Connection conn, ProdutoVO produto) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("UPDATE PRODUTO SET NOME = ?, VALOR = ?, ID_FORNECEDOR = ? WHERE ID_PRODUTO = ?");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			stmt.setString(++index, produto.getNome());
			stmt.setBigDecimal(++index, produto.getValor());
			stmt.setLong(++index, produto.getFornecedor().getIdFornecedor());
			stmt.setLong(++index, produto.getIdProduto());
			stmt.execute();
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public void deletarProduto(Connection conn, ProdutoVO produto) throws SQLException {

		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("DELETE FROM PRODUTO WHERE ID_PRODUTO = ? AND ID_FORNECEDOR = ?");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index = 0;
			
			stmt.setLong(++index, produto.getIdProduto());
			stmt.setLong(++index, produto.getFornecedor().getIdFornecedor());
			stmt.executeUpdate();
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public List<ProdutoVO> selecionaProdutos(Connection conn) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
		
		query.append("SELECT P.ID_PRODUTO, P.NOME, P.VALOR, P.ID_FORNECEDOR, F.NOME_EMPRESA  FROM PRODUTO P ");
		query.append(" INNER JOIN FORNECEDOR F ON (P.ID_FORNECEDOR = F.ID_FORNECEDOR)");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				ProdutoVO produto = new ProdutoVO();
				
				if(rs.getLong("ID_PRODUTO") != 0L){
					produto.setIdProduto(rs.getLong("ID_PRODUTO"));
				}else{
					produto.setIdProduto(null);
				}
				
				if(rs.getString("NOME") == null || "".equals(rs.getString("NOME"))){
					produto.setNome(null);
				}else{
					produto.setNome(rs.getString("NOME"));
				}
				
				if(rs.getBigDecimal("VALOR") == null){
					produto.setValor(null);
				}else{
					produto.setValor(rs.getBigDecimal("VALOR"));
				}
				
				if(rs.getString("NOME_EMPRESA") == null || "".equals(rs.getString("NOME_EMPRESA"))){
					produto.getFornecedor().setNomeEmpresa(null);
				}else{
					produto.getFornecedor().setNomeEmpresa(rs.getString("NOME_EMPRESA"));
				}
				
				if(rs.getLong("ID_FORNECEDOR") != 0L){
					produto.getFornecedor().setIdFornecedor(rs.getLong("ID_FORNECEDOR"));
				}else{
					produto.getFornecedor().setIdFornecedor(null);
				}
				
				produtos.add(produto);
			}
			
			return produtos;
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
		
	}

	@Override
	public List<ProdutoVO> selecionaProdutosPorTipo(Connection conn, Long idTipo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
