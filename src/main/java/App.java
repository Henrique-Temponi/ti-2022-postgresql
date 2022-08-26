import java.util.List;
import java.util.Scanner;

public class App {
	
	public static int idAtual = 1;
	public static DAO test = new DAO();
	public static Scanner sc = new Scanner(System.in);
	
	public static String menu ( ) {
		String menu = "";
		
		System.out.println("=====================================================");
		System.out.println("\t\tDigite a opcao");
		System.out.println("=====================================================");
		System.out.println("1. Inserir novo registro");
		System.out.println("2. Alterar um registro");
		System.out.println("3. Deletar um registro");
		System.out.println("4. Listar registros");
		System.out.println("5. Sair");
		System.out.println("=====================================================");
		
		return menu;
	}
	
	public static int atualizarId () {
		idAtual++;
		return idAtual;
	}
	
	public static Computer criarNovoPc () {
		Computer pc = new Computer();
		
		System.out.println("Digite a CPU:");
		pc.setCpu(sc.nextLine());
		System.out.println("Digite a GPU:");
		pc.setGpu(sc.nextLine());
		System.out.println("Digite a RAM:");
		pc.setRam(sc.nextInt());
		System.out.println("Digite o HDD:");
		pc.setHdd(sc.nextInt());
		pc.setId(atualizarId());
		
		return pc;
	}
	
	public static boolean inserirPc(Computer pc) {
		boolean resp = false;
		
		System.out.println("=====================================================");
		System.out.println("Novo registro:");
		System.out.println("=====================================================");
		
		if(test.insert(pc)) {
			resp = true;
		}
		
		return resp;
	}
	
	
	public static boolean alterarPc() {
		boolean resp = false;
		
		System.out.println("=====================================================");
		System.out.println("alterar registro:");
		System.out.println("=====================================================");
		System.out.println("Digite um id");
		
		int id = sc.nextInt();
		Computer pc2 = criarNovoPc();
		pc2.setId(id);		
		
		if (test.update(pc2)) {
			resp = true;
		}
		
		return resp;
	}
	
	public static boolean deletarPc() {
		boolean resp = false;
		
		System.out.println("=====================================================");
		System.out.println("deletar registro:");
		System.out.println("=====================================================");
		System.out.println("Digite um id");
		
		int id = sc.nextInt();

		if (test.delete(""+id)) {
			resp = true;
		}
		
		return resp;
	}
	
	public static void listarPc () {
		List<Computer> computers = test.listComputers();
		for (Computer i : computers) {
			System.out.println(i.toString());
		}
	}
	
	public static void main(String[] args) {
		
		if (test.conectar()) {
			System.out.println("Iniciando.....");
		}
		
		int choiceNumber = 0;
		boolean stopLoop = false;
		
		while(!stopLoop) {
			System.out.println(menu());
			
			choiceNumber = sc.nextInt();
			
			switch (choiceNumber) {
			
				case 1:
					if (inserirPc(criarNovoPc())) {
						System.out.println("Novo pc adicionado");
					}
					break;
				case 2:
					if (alterarPc()) {
						System.out.println("pc alterado");
					}
					break;
				case 3:
					if (deletarPc()) {
						System.out.println("pc deletado");
					}
					break;
				case 4:
					listarPc();
					break;
					
				case 5:
					stopLoop = true;
					break;
				default:
					System.out.println("Commando invalido");
					break;
			}	
			
		}
		
		if(test.deleteAll()) {
			System.out.println("acabou");
		
		}
		
		
	}
}