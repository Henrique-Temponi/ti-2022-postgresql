package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ComputerParts;

public class DAO {
	protected Connection conexao;
	private String tableName = "\"public\".\"Parts\"";
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "test";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "dev";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean insert(ComputerParts produto) {
		boolean status = false;
		try {
			String sql = "INSERT INTO " + tableName + " (descricao, preco, quantidade) "
		               + "VALUES ('" + produto.getDescricao() + "', "
		               + produto.getPreco() + ", " + produto.getQuantidade() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public ComputerParts get(int id) {
		ComputerParts produto = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM " + tableName + "  WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 produto = new ComputerParts(rs.getInt("id"), rs.getString("descricao"), (float)rs.getDouble("preco"), 
	                				   rs.getInt("quantidade"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produto;
	}
	
	
	public List<ComputerParts> get() {
		return get("");
	}

	
	public List<ComputerParts> getOrderByID() {
		return get("id");		
	}
	
	
	public List<ComputerParts> getOrderByDescricao() {
		return get("descricao");		
	}
	
	
	public List<ComputerParts> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<ComputerParts> get(String orderBy) {
		List<ComputerParts> produtos = new ArrayList<ComputerParts>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM " + tableName + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	ComputerParts p = new ComputerParts(rs.getInt("id"), rs.getString("descricao"), (float)rs.getDouble("preco"), 
	        			                rs.getInt("quantidade"));
	            produtos.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produtos;
	}
	
	
	public boolean update(ComputerParts produto) {
		boolean status = false;
		try {  
			String sql = "UPDATE " + tableName + " SET descricao = '" + produto.getDescricao() + "', "
					   + "preco = " + produto.getPreco() + ", " 
					   + "quantidade = " + produto.getQuantidade() 
					   + "WHERE id = " + produto.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM " + tableName + " WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
}