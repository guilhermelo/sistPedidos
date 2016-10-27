package pedidos.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pedidos.dao.Conexao;
import pedidos.dao.IFornecedor;
import pedidos.dao.imp.FornecedorDAO;
import pedidos.util.PedidosUtil;
import pedidos.vo.FornecedorVO;
import pedidos.vo.ProdutoVO;

@ManagedBean
@ViewScoped
public class FornecedorBean {

	private FornecedorVO fornecedor = new FornecedorVO();
	private FornecedorVO selected = new FornecedorVO();
	
	public void insereFornecedor() throws Exception{
		
		IFornecedor dao = new FornecedorDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.insereFornecedor(conn, fornecedor);
			
			PedidosUtil.addMessage("success", "Sucesso", "Fornecedor inserido com sucesso!");
			
			fornecedor = new FornecedorVO();
		}catch(SQLException e){
			System.out.println("Erro FornecedorBean.insereFornecedor");
		}
		
	}
	
	public List<FornecedorVO> selecionaFornecedores() throws Exception{
		
		IFornecedor dao = new FornecedorDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			return dao.selecionaFornecedores(conn);
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
	}
	
	public void editaFornecedor(FornecedorVO fornecedor) throws Exception{
		
		IFornecedor dao = new FornecedorDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.alteraFornecedor(conn, fornecedor);
			
			PedidosUtil.addMessage("success", "Sucesso", "Fornecedor editado com sucesso!");
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
		
	}
	
	public void excluiFornecedor(FornecedorVO fornecedor) throws Exception{
		
		IFornecedor dao = new FornecedorDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.deletarFornecedor(conn, fornecedor);
			
			PedidosUtil.addMessage("success", "Sucesso", "Fornecedor deletado com sucesso!");
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
		
	}

	public FornecedorVO getFornecedor() {
		if(fornecedor == null){
			fornecedor = new FornecedorVO();
		}
		return fornecedor;
	}

	public void setFornecedor(FornecedorVO fornecedor) {
		this.fornecedor = fornecedor;
	}

	public FornecedorVO getSelected() {
		return selected;
	}

	public void setSelected(FornecedorVO selected) {
		this.selected = selected;
	}
	
	
}

