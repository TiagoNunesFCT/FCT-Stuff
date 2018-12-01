import java.util.Scanner;

public class Main {
	private static final String HELP = "AJUDA";
	// Fora de sessao
	private static final String END = "TERMINA";
	private static final String REGISTER = "REGISTA";
	private static final String LOGIN = "ENTRADA";
	// dentro de sessao
	private static final String LOGOUT = "SAI";
	private static final String NEWRIDE = "NOVA";
	private static final String USERRIDELIST = "LISTA";
	private static final String RIDE = "BOLEIA";
	private static final String CHECK = "CONSULTA";
	private static final String REMOVE = "REMOVE";
	// prompt
	private static final String PROMPTOUT = "> ";
	// erro
	private static final String ERROR = "Comando inexistente.";
	private static final String ENDMESSAGE = "Obrigado. Ate a proxima.";
	private static final String WRONGPASSWORD = "Password incorrecta.";

	private static String readMenuOption(Scanner input) {
		return input.next().toUpperCase();
	}

	private static void executeMenuOption(Scanner input, String option, FctBoleia a, UserData data) {
		if (a.getUser() != null) {
			switch (option) {
			case HELP:
				processHelp(a);
				break;
			case LOGOUT:
				processLogout();
				break;
			case NEWRIDE:
				processNewRide();
				break;
			case USERRIDELIST:
				processUserRideList();
				break;
			case RIDE:
				processRide();
				break;
			case CHECK:
				processCheck();
				break;
			case REMOVE:
				processRemove();
				break;
			default:
				processComandoInexistente();
				break;
			}
		} else
			switch (option) {
			case HELP:
				processHelp(a);
				break;
			case END:
				processEnd();
				break;
			case REGISTER:
				processRegister(input, data);
				break;
			case LOGIN:
				processLogin(input, data, a);
				break;
			default:
				processComandoInexistente();
				break;
			}
	}

	private static void processLogout() {//TODO
		System.out.println("logout");
	}

	private static void processNewRide() {//TODO
		System.out.println("new ride");
	}

	private static void processUserRideList() {//TODO
		System.out.println("user ride list");
	}

	private static void processRide() {//TODO
		System.out.println("ride");
	}

	private static void processCheck() {//TODO
		System.out.println("check");
	}

	private static void processRemove() {//TODO
		System.out.println("remove");
	}

	private static void processLogin(Scanner input, UserData data, FctBoleia a) {
		String email = input.next();
		if (data.hasUser(email)) {
			boolean right = false;
			int i = 0;
			do {
				System.out.print("password: ");
				String password = input.next();
				if (password.equals(data.getPassword(email))) {
					right = true;
					a.setCurrentUser(data.getUser(email));
				} else {
					System.out.println(WRONGPASSWORD);
				}
				i++;
			} while (!right && i < 3);
		}else {
			System.out.println("Utilizador nao existente");
		}
	}

	private static void processComandoInexistente() {
		System.out.println(ERROR);
	}

	private static void processEnd() {
		System.out.println(ENDMESSAGE);
	}

	private static void processRegister(Scanner input, UserData data) {
		String email = input.next();
		if(!data.hasUser(email)) {
			System.out.print("nome (maximo 50 caracteres): ");
			String name = input.next();
			int i = 0;
			boolean right;
			String password;
			do {
				right = true;
				System.out.print("password (entre 3 e 5 caracteres - digitos e letras): ");
				password = input.next();
				if(password.length()<3||password.length()>5) {
					i++;
					right = false;
					System.out.println("Password incorrecta.");
				}
			}while(i<3&&!right);
			User user = new User(email, name, password);
			data.addUser(user);
		}else {
			System.out.println("Utilizador ja existente.");
			System.out.println("Registo nao efetuado.");
		}
	}

	private static void processHelp(FctBoleia a) {
		if (a.getUser() == null) {
			System.out.println("ajuda - Mostra os comandos existentes");
			System.out.println("termina - Termina a execucao do programa");
			System.out.println("regista - Regista um novo utilizador no programa");
			System.out.println("entrada - Permite a entrada (\"login\") dum utilizador no programa");
		} else {
			System.out.println("ajuda - Mostra os comandos existentes");
			System.out.println("sai - Termina a sessao deste utilizador no programa");
			System.out.println("nova - Regista uma nova deslocacao");
			System.out.println("lista - Lista todas ou algumas deslocacoes registadas");
			System.out.println("boleia - Regista uma boleia para uma dada deslocacao");
			System.out.println("consulta - Lista a informacao de uma dada deslocacao");
			System.out.println("remove - Retira uma dada deslocacao");
		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		FctBoleia a = new FctBoleia();
		UserData data = new UserData();
		String option = "";
		do {
			if (a.getUser() != null) {
				System.out.println(a.getCurrentUser().getEmail() + " > ");//prompt dentro de sessao
				option = readMenuOption(input);
				executeMenuOption(input, option, a, data);

			} else {
				System.out.print(PROMPTOUT);
				option = readMenuOption(input);
				executeMenuOption(input, option, a, data);
			}
		} while (!option.equals(END) || a.getUser() != null);
		input.close();
	}
}
