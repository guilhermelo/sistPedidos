package pedidos.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pedidos.dao.IPedido;
import pedidos.vo.ItemPedidoVO;
import pedidos.vo.PedidoVO;

public class PedidoDAO implements IPedido{

	@Override
	public void inserePedido(Connection conn, PedidoVO pedido) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
	
		query.append(" INSERT INTO PEDIDO(ID_FUNCIONARIO, ID_MESA, ID_CLIENTE, DTHR_PEDIDO, OBSERVACAO, STATUS) ");
		query.append(" VALUES (?, ?, ?, ?, ?, ?) ");
		
		try{	
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		
			int index = 0;
			stmt.setLong(++index, pedido.getFuncionario().getIdFuncionario());
			stmt.setLong(++index, pedido.getMesa().getIdMesa());
			stmt.setLong(++index, pedido.getCliente().getIdCliente());
			stmt.setTimestamp(++index, new Timestamp(pedido.getDtHrPedido().getTime()));
			stmt.setString(++index, pedido.getObservacao());
			stmt.setString(++index, "A");
			
			stmt.execute();
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public Long selecionaIdPedido(Connection conn, PedidoVO pedido) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Long idPedido = null;
	
		query.append(" SELECT ID_PEDIDO FROM PEDIDO WHERE ID_CLIENTE = ? AND ID_MESA = ? AND ID_FUNCIONARIO = ? AND DTHR_PEDIDO = ?");
		
		try{	
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		
			int index = 0;
			stmt.setLong(++index, pedido.getCliente().getIdCliente());
			stmt.setLong(++index, pedido.getMesa().getIdMesa());
			stmt.setLong(++index, pedido.getFuncionario().getIdFuncionario());
			stmt.setTimestamp(++index, new Timestamp(pedido.getDtHrPedido().getTime()));
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				idPedido = rs.getLong("ID_PEDIDO");
			}
			
			return idPedido;
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public List<ItemPedidoVO> selecionaPedidosDaMesaOcupada(Connection conn, Long idMesa) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ItemPedidoVO> pedidos = new ArrayList<ItemPedidoVO>();
		ItemPedidoVO itemPedido = null;
	
		query.append(" SELECT P.ID_PEDIDO, C.ID_CARDAPIO, C.NOME, C.VALOR, I.QUANTIDADE  FROM ITENS_PEDIDO I ");
		query.append(" INNER JOIN CARDAPIO C ON(I.ID_CARDAPIO = C.ID_CARDAPIO) "); 
		query.append(" INNER JOIN PEDIDO P ON(I.ID_PEDIDO = P.ID_PEDIDO) "); 
		query.append(" INNER JOIN MESA M ON(P.ID_MESA = M.ID_MESA) WHERE P.ID_MESA = ? AND M.MESA_STATUS = 'O' AND P.STATUS = 'A' ");
		
		try{	
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		
			int index = 0;
			stmt.setLong(++index, idMesa);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				itemPedido = new ItemPedidoVO();
				
				if(rs.getLong("ID_CARDAPIO") != 0L){
					itemPedido.getCardapio().setIdCardapio(rs.getLong("ID_CARDAPIO"));
				}else{
					itemPedido.getCardapio().setIdCardapio(null);
				}
				
				if(rs.getString("NOME") != null && !"".equals(rs.getString("NOME"))){
					itemPedido.getCardapio().setNome(rs.getString("NOME"));
				}else{
					itemPedido.getCardapio().setNome(null);
				}
				
				if(rs.getBigDecimal("VALOR") != null && !"".equals(rs.getBigDecimal("VALOR"))){
					itemPedido.getCardapio().setValor(rs.getBigDecimal("VALOR"));
				}else{
					itemPedido.getCardapio().setValor(null);
				}
				
				if(rs.getLong("ID_PEDIDO") != 0L){
					itemPedido.getPedido().setIdPedido(rs.getLong("ID_PEDIDO"));
				}else{
					itemPedido.getPedido().setIdPedido(null);
				}
				
				if(rs.getLong("QUANTIDADE") != 0L){
					itemPedido.setQuantidade(rs.getLong("QUANTIDADE"));
				}else{
					itemPedido.setQuantidade(0L);
				}
				
				pedidos.add(itemPedido);
			}
			
			return pedidos;
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}

	@Override
	public void atualizaStatusPedido(Connection conn, Long idPedido) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		
		query.append("UPDATE PEDIDO SET STATUS = 'F' WHERE ID_PEDIDO = ? ");
		
		try{
			
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			stmt.setLong(1, idPedido);
			
			stmt.executeUpdate();
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}	
	}
}
