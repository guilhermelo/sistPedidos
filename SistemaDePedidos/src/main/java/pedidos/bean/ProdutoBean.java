package pedidos.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pedidos.dao.Conexao;
import pedidos.dao.IFornecedor;
import pedidos.dao.IProduto;
import pedidos.dao.imp.FornecedorDAO;
import pedidos.dao.imp.ProdutoDAO;
import pedidos.util.PedidosUtil;
import pedidos.vo.ProdutoVO;

@ManagedBean
@ViewScoped
public class ProdutoBean {
	
	private ProdutoVO produto = new ProdutoVO(); 
	private Long idFornecedor;
	private ProdutoVO selected;
	
	public void insereProduto() throws Exception{
		
		IProduto dao = new ProdutoDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			produto.getFornecedor().setIdFornecedor(idFornecedor);
			
			dao.inserirProduto(conn, produto);
			
			PedidosUtil.addMessage("success", "Sucesso", "Produto inserido com sucesso!");
		}catch(SQLException e){
			System.out.println("Erro ProdutoBean.insereProduto:" + e.getMessage());
		}
	}
	
	public List<ProdutoVO> selecionaProdutos() throws Exception{
		
		IProduto dao = new ProdutoDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			return dao.selecionaProdutos(conn);
			
		}catch(SQLException e){
			throw new SQLException("Erro ProdutoBean.insereProduto:" + e.getMessage());
		}
	}
	
	public void editarProduto(ProdutoVO produto) throws Exception{
		
		IProduto dao = new ProdutoDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.alterarProduto(conn, produto);
			
			PedidosUtil.addMessage("success", "Sucesso", "Produto editado com sucesso!");
		}catch(SQLException e){
			throw new SQLException("Erro ProdutoBean.insereProduto:" + e.getMessage());
		}
	}
	
	public void selecionaFornecedor(Long idFornecedor) throws Exception{
		
		IFornecedor dao = new FornecedorDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			this.selected.setFornecedor(dao.selecionaFornecedor(conn, idFornecedor));
			
		}catch(SQLException e){
			throw new SQLException("Erro ProdutoBean.insereProduto:" + e.getMessage());
		}
		
	}
	
	public void deletaProduto(ProdutoVO produto) throws Exception{
		IProduto dao = new ProdutoDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.deletarProduto(conn, produto);
			
			PedidosUtil.addMessage("success", "Sucesso", "Produto deletado com sucesso!");
			
		}catch(SQLException e){
			throw new SQLException("Erro ProdutoBean.insereProduto:" + e.getMessage());
		}
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public ProdutoVO getSelected() {
		if(selected == null){
			selected = new ProdutoVO();
		}
		return selected;
	}

	public void setSelected(ProdutoVO selected) {
		this.selected = selected;
	}
}
