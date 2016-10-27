package pedidos.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pedidos.dao.Conexao;
import pedidos.dao.ICliente;
import pedidos.dao.imp.ClienteDAO;
import pedidos.util.PedidosUtil;
import pedidos.vo.ClienteVO;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ClienteVO cliente = new ClienteVO();
	private ClienteVO selected;	
	List<ClienteVO> clientes = new ArrayList<>();
	
	public ClienteVO getCliente() {
		return cliente;
	}


	public void insereCliente() throws Exception{
		ICliente dao = new ClienteDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.inserirCliente(conn, cliente);
			
			cliente = new ClienteVO();
			
			PedidosUtil.addMessage("success", "Sucesso", "Cliente inserido com sucesso!");
			
		}catch(SQLException e){
			System.out.println("Erro ClienteBean.insereCliente:" + e.getMessage());
		}
	}
	
 	public List<ClienteVO> selecionaClientes() throws Exception{
 		
 		ICliente dao = new ClienteDAO();
 		
 		try{
 			Connection conn = Conexao.getConexao();
 			
 			clientes = dao.selecionaClientes(conn);
 		}catch(SQLException e){
 			System.out.println("Erro ClienteBean.selecionaClientes: " + e.getMessage());
 		}
 		
		return clientes;
 	}
 	
 	public void editaCliente(ClienteVO cliente) throws Exception{
 		ICliente dao = new ClienteDAO();
 		
 		try{
 			Connection conn = Conexao.getConexao();
 			
 			dao.alterarCliente(conn, cliente);
 			
 			PedidosUtil.addMessage("success", "Sucesso", "Cliente editado com sucesso!");
 		}catch (SQLException e) {
 			System.out.println("Erro ClienteBean.editaCliente: " + e.getMessage());
		}
 	}
 	
 	public void excluiCliente(ClienteVO cliente) throws Exception{
 		
 		ICliente dao = new ClienteDAO();
 		
 		try{
 			Connection conn = Conexao.getConexao();
 			
 			dao.deletarCliente(conn, cliente);
 			
 			PedidosUtil.addMessage("success", "Sucesso", "Cliente deletado com sucesso!");
 		}catch(SQLException e){
 			System.out.println("Erro ClienteBean.excluiCliente: " + e.getMessage());
 		}
 	}

	public ClienteVO getSelected() {
		return selected;
	}


	public void setSelected(ClienteVO selected) {
		this.selected = selected;
	}
}
