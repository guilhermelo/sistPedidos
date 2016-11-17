package pedidos.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import pedidos.dao.ItensPedido;
import pedidos.vo.ItemPedidoVO;

public class ItemPedidoDAO implements ItensPedido{

	@Override
	public void insereItensPedido(Connection conn, List<ItemPedidoVO> itens) throws SQLException {

		StringBuilder query = new StringBuilder();
		PreparedStatement stmt = null;
		Date dataAtual = new Date();
		
		query.append("INSERT INTO ITENS_PEDIDO (ID_CARDAPIO, ID_PEDIDO, HORA_PEDIDO, QUANTIDADE) ");
		query.append(" VALUES (?, ?, ?, ?)");
		
		try{
			stmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int index;
			for(ItemPedidoVO item : itens){
				index = 0;
				stmt.setLong(++index, item.getCardapio().getIdCardapio());
				stmt.setLong(++index, item.getPedido().getIdPedido());
				stmt.setTimestamp(++index, new Timestamp(dataAtual.getTime()));
				stmt.setLong(++index, item.getQuantidade());
				
				stmt.addBatch();
			}
			
			stmt.executeBatch();
			
		}catch(RuntimeException e){
			throw new SQLException("Erro de runtime: " + e.getMessage());
		}catch(Exception e){
			throw new SQLException("Erro: " + e.getMessage());
		}
	}
	
}
