package pedidos.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pedidos.dao.Conexao;
import pedidos.dao.IMesa;
import pedidos.dao.imp.MesaDAO;
import pedidos.util.PedidosUtil;
import pedidos.vo.MesaVO;

@ManagedBean
@ViewScoped
public class MesaBean {
	
	private MesaVO mesa = new MesaVO();
	private MesaVO selected = new MesaVO();
	
	public void insereMesa() throws Exception{
		
		IMesa dao = new MesaDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.insereMesa(conn, mesa);
			
			PedidosUtil.addMessage("success", "Sucesso", "Mesa inserida com sucesso!");
			
			mesa = new MesaVO();
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
	}
	
	
	public void deletaMesa(MesaVO mesa) throws Exception{
		
		IMesa dao = new MesaDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.deletaMesa(conn, mesa);
			
			PedidosUtil.addMessage("success", "Sucesso", "Mesa deletada com sucesso!");
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
	}
	
	public List<MesaVO> selecionaMesas() throws Exception{
		
		IMesa dao = new MesaDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			return dao.selecionaMesas(conn);
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
	}


	public MesaVO getMesa() {
		return mesa;
	}


	public void setMesa(MesaVO mesa) {
		this.mesa = mesa;
	}


	public MesaVO getSelected() {
		return selected;
	}


	public void setSelected(MesaVO selected) {
		this.selected = selected;
	}
	
}
