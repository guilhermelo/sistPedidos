package pedidos.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pedidos.dao.Conexao;
import pedidos.dao.IFuncionario;
import pedidos.dao.imp.FuncionarioDAO;
import pedidos.util.PedidosUtil;
import pedidos.vo.FuncionarioVO;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

	private FuncionarioVO funcionario = new FuncionarioVO();
	private FuncionarioVO selected = new FuncionarioVO();

	public FuncionarioVO getFuncionario() {
		return funcionario;
	}

	public void insereFuncionario() throws Exception{
		IFuncionario dao = new FuncionarioDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.insereFuncionario(conn, funcionario);
			
			funcionario = new FuncionarioVO();
					
			PedidosUtil.addMessage("success", "Sucesso!", "Funcionário inserido com sucesso!");			
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
	}
	
	public List<FuncionarioVO> selecionaFuncionarios() throws Exception{
		
		IFuncionario dao = new FuncionarioDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			return dao.selecionaFuncionarios(conn);
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
	}
	
	public void editaFuncionario(FuncionarioVO funcionario) throws Exception{
		
		IFuncionario dao = new FuncionarioDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.alteraFuncionario(conn, funcionario);;
			
			PedidosUtil.addMessage("success", "Sucesso", "Funcionário editado com sucesso!");
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
	}
	
	public void excluirFuncionario(FuncionarioVO funcionario) throws Exception{
		
		IFuncionario dao = new FuncionarioDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.deletaFuncionario(conn, funcionario);
			
			PedidosUtil.addMessage("success", "Sucesso", "Funcionário deletado com sucesso!");
		}catch(Exception e){
			throw new SQLException("Erro FuncionarioBean.excluirFuncionario() " + e.getMessage());
		}
		
	}

	public FuncionarioVO getSelected() {
		return selected;
	}

	public void setSelected(FuncionarioVO selected) {
		this.selected = selected;
	}

}
