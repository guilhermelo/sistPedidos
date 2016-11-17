package pedidos.bean;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pedidos.dao.Conexao;
import pedidos.dao.ICardapio;
import pedidos.dao.ICliente;
import pedidos.dao.IConta;
import pedidos.dao.IMesa;
import pedidos.dao.IPedido;
import pedidos.dao.ItensPedido;
import pedidos.dao.imp.CardapioDAO;
import pedidos.dao.imp.ClienteDAO;
import pedidos.dao.imp.ContaDAO;
import pedidos.dao.imp.ItemPedidoDAO;
import pedidos.dao.imp.MesaDAO;
import pedidos.dao.imp.PedidoDAO;
import pedidos.util.PedidosUtil;
import pedidos.vo.CardapioVO;
import pedidos.vo.ClienteVO;
import pedidos.vo.ContaVO;
import pedidos.vo.ItemAdapter;
import pedidos.vo.ItemPedidoVO;
import pedidos.vo.MesaVO;
import pedidos.vo.PedidoVO;

@ManagedBean
@ViewScoped
public class DashboardBean {

	private MesaVO mesa = new MesaVO();
	private PedidoVO pedido = new PedidoVO();
	private ClienteVO cliente = new ClienteVO();
	private ContaVO conta = new ContaVO();
	private List<CardapioVO> cardapios = new ArrayList<CardapioVO>();
	private List<ItemAdapter> pedidos = new ArrayList<ItemAdapter>();
	private Long idCliente;
	private Long idFuncionario;
	private Long idMesa;	
	private String color;
	private BigDecimal valorConta;

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

	public String verificaStatus(MesaVO mesa) {
		String color = "";

		if (mesa.getMesaStatus()) {
			color = "background-color: #86CB92;";
		} else {
			color = "background-color: #FE5F55;";
		}

		return color;
	}

	public void esvaziaCliente() {
		this.cliente = new ClienteVO();
	}
	
	public void capturaId(MesaVO mesa){
		this.idMesa = mesa.getIdMesa();
	}
	
	public void insereClienteRapido() throws Exception{
		ICliente dao = new ClienteDAO();
		
		try{
			Connection conn = Conexao.getConexao();
			
			dao.inserirCliente(conn, cliente);
			
		}catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public void defineValor() throws Exception{
		
		try{
			
			valorConta = BigDecimal.ZERO;
			
			if(this.pedidos.size() > 0){
				for(ItemAdapter item : this.pedidos){
					valorConta = valorConta.add(item.getValor());
				}
				
				conta.setValorConta(valorConta);
			}else{
				conta.setValorConta(BigDecimal.ZERO);
			}
			 
			
		}catch (ArithmeticException e) {
			throw new Exception("Erro: " + e.getMessage());
		}
	}
	
	
	public List<CardapioVO> selecionaCardapios() throws Exception{
		
		ICardapio dao = new CardapioDAO();
		List<CardapioVO> cardapios = new ArrayList<CardapioVO>();

		try {
			Connection conn = Conexao.getConexao();
			
			cardapios = dao.selecionaRefeicoes(conn);
			
			return cardapios;
		} catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}

	}
	
	public void inserePedido() throws Exception {

		IPedido dao = new PedidoDAO();
		ItensPedido daoItens = new ItemPedidoDAO();
		IMesa daoMesa = new MesaDAO();

		try {
			Connection conn = Conexao.getConexao();
			
			this.pedido.getFuncionario().setIdFuncionario(this.idFuncionario);
			this.pedido.getCliente().setIdCliente(this.idCliente);
			this.pedido.getMesa().setIdMesa(idMesa);
			this.pedido.setDtHrPedido(new Date());
			dao.inserePedido(conn, pedido);
			
			List<ItemPedidoVO> itensDoPedido = new ArrayList<ItemPedidoVO>();
			
			pedido.setIdPedido(dao.selecionaIdPedido(conn, pedido));
			
			for(CardapioVO cardapio : cardapios){
				itensDoPedido.add(new ItemPedidoVO(pedido, cardapio, 1L));
			}
			
			daoItens.insereItensPedido(conn, itensDoPedido);
			
			daoMesa.atualizaStatusMesa(conn, idMesa);

			PedidosUtil.addMessage("success", "Sucesso!", "Pedido realizado com sucesso!");
			
			this.pedido = new PedidoVO();
			this.cardapios = new ArrayList<CardapioVO>();

		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public void fechaConta() throws Exception{
		
		IConta dao = new ContaDAO();
		IPedido daoPedido = new PedidoDAO();
		List<ContaVO> contas = new ArrayList<ContaVO>();
		
		try{
			Connection conn = Conexao.getConexao();
			
			Long id = dao.capturaIdConta(conn);
			
			for(ItemAdapter item : pedidos){
				ContaVO c = new ContaVO();
				c.setIdConta(id);
				c.getPedido().setIdPedido(item.getIdPedido());
				c.setValorConta(valorConta);
				contas.add(c);
				
				daoPedido.atualizaStatusPedido(conn, item.getIdPedido());
			}
			
			dao.insereConta(conn, contas);
			
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public List<ItemAdapter> selecionaPedidosDaMesa() throws Exception{
		
		IPedido dao = new PedidoDAO();
		
		if(idMesa != null){
			try{
				Connection conn = Conexao.getConexao();
				
				List<ItemPedidoVO> itens = dao.selecionaPedidosDaMesaOcupada(conn, idMesa);
				List<ItemAdapter> adapters = new ArrayList<ItemAdapter>();
				
				for(ItemPedidoVO item : itens){
					adapters.add(new ItemAdapter(item));
				}
				
				return adapters;
				
			} catch (Exception e) {
				throw new SQLException(e.getMessage());
			}
		}else{
			return null;
		}
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

	public PedidoVO getPedido() {
		if(this.pedido == null){
			this.pedido = new PedidoVO();
		}
		return pedido;
	}

	public List<CardapioVO> getCardapios() {
		return cardapios;
	}
	
	public ClienteVO getCliente() {
		return cliente;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Long getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(Long idMesa) {
		this.idMesa = idMesa;
	}

	public void setCardapios(List<CardapioVO> cardapios) {
		this.cardapios = cardapios;
	}

	public void setPedido(PedidoVO pedido) {
		this.pedido = pedido;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public ContaVO getConta() {
		return conta;
	}

	public void setConta(ContaVO conta) {
		this.conta = conta;
	}

	public List<ItemAdapter> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<ItemAdapter> pedidos) {
		this.pedidos = pedidos;
	}

}
