import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	protected Connection conexao;
	private String tableName = "\"public\".\"Computers\"";
	
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
	
	public boolean insert(Computer computer) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO " + tableName + " "
						 + "VALUES (" + computer.getId() + ", '" + computer.getCpu() +"', '" + computer.getGpu() + "', "
						 + computer.getRam() + ", " + computer.getHdd() + ");";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public List<Computer> listComputers() {
		List<Computer>computers = new ArrayList<>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "Select * from " +  tableName + ";";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				computers.add(new Computer( rs.getInt("id"), 
							rs.getString("CPU"),
							rs.getString("GPU"),
							rs.getInt("RAM"),
							rs.getInt("HDD")));
			}
			st.close();
			
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return computers;
	}
	
	public boolean delete(String cpuData) {
		
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			String sql = "delete from " + tableName + " where \"id\"='" + cpuData + "';";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException i) {
			throw new RuntimeException(i);
		}
		
		return status;
	}
	
	public boolean deleteAll() {
		
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			String sql = "delete from " + tableName + ";";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException i) {
			throw new RuntimeException(i);
		}
		
		return status;
	}
	
	public boolean update(Computer data) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			String sql = "update " + tableName + " set \"CPU\"='" + data.getCpu()
						 + "', \"GPU\"='" + data.getGpu() + "', \"RAM\"=" + data.getRam()
						 + ", \"HDD\"=" + data.getHdd() + " where \"id\"=" + data.getId() + ";";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
}