package pedidos.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pedidos.dao.Conexao;
import pedidos.dao.IMesa;
import pedidos.dao.imp.MesaDAO;
import pedidos.vo.MesaVO;

@ManagedBean
@ViewScoped
public class DashboardBean {
	
	private MesaVO mesa = new MesaVO();
	private String color;

	public List<MesaVO> selecionaMesas() throws Exception {

		IMesa dao = new MesaDAO();

		try {
			Connection conn = Conexao.getConexao();
			
			List<MesaVO> mesas = dao.selecionaMesas(conn);
			 
			 return mesas;
			 
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}

	}
	
	public String verificaStatus(MesaVO mesa){
		String color = "";
		
		if(mesa.getMesaStatus()){
			 color = "background-color: #86CB92;";
		 }else{
			 color = "background-color: #FE5F55;";
		 }
		
		return color;
	}

	public MesaVO getMesa() {
		return mesa;
	}

	public void setMesa(MesaVO mesa) {
		this.mesa = mesa;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
