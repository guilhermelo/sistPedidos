package pedidos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConexao() throws Exception{
		try{
			DriverManager.registerDriver(new org.postgresql.Driver());
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/pedidos", "postgres", "guilherme");
			
		}catch(SQLException e){
			throw new SQLException("Erro ao tentar conectar: " + e.getMessage());
		}
	}
}
