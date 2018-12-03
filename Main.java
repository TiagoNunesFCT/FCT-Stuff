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

	private static void executeMenuOption(Scanner input, String option, FctBoleia a, UserData userData) {
		if (a.getCurrentUser() != null) {
			switch (option) {
			case HELP:
				processHelp(a);
				break;
			case LOGOUT:
				processLogout(a);
				break;
			case NEWRIDE:
				processNewRide(input, userData, a);
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
				processRegister(input, userData);
				break;
			case LOGIN:
				processLogin(input, userData, a);
				break;
			default:
				processComandoInexistente();
				break;
			}
	}

	private static void processLogout(FctBoleia a) {
		a.logout();
		System.out.println(ENDMESSAGE);
	}

	private static void processNewRide(Scanner input, UserData userData, FctBoleia a) {// WIP
		String origin = input.next();
		input.nextLine();
		String destination = input.next();
		input.nextLine();
		String date = input.next();
		BasicDate basicDate = new BasicDate(date);
		int time = input.nextInt();
		float duration = input.nextFloat();
		int seats = input.nextInt();
		/*if ((time >= 0 && time <= 24) && duration > 0 && basicDate.isValid()) {
			Ride r = new Ride(origin, destination, basicDate, time, duration, seats);
			a.getCurrentUser().addRide(r);
		}*/
		System.out.println("Deslocacao registada. Obrigado " + a.getCurrentUser().getName() + ".");

	}

	private static void processUserRideList() {// TODO
		System.out.println("user ride list");
	}

	private static void processRide() {// TODO
		System.out.println("ride");
	}

	private static void processCheck() {// TODO
		System.out.println("check");
	}

	private static void processRemove() {// TODO
		System.out.println("remove");
	}

	private static void processLogin(Scanner input, UserData userData, FctBoleia a) {
		String email = input.next();
		if (userData.hasUser(email)) {
			boolean right = false;
			int i = 0;
			do {
				System.out.print("password: ");
				String password = input.next();
				if (password.equals(userData.getPassword(email))) {
					right = true;
					a.setCurrentUser(userData.getUser(email));
				} else {
					System.out.println(WRONGPASSWORD);
				}
				i++;
			} while (!right && i < 3);
		} else {
			System.out.println("Utilizador nao existente.");
		}
	}

	private static void processComandoInexistente() {
		System.out.println(ERROR);
	}

	private static void processEnd() {
		System.out.println(ENDMESSAGE);
	}

	private static void processRegister(Scanner input, UserData userData) {
		String email = input.next();
		input.nextLine();
		if (!userData.hasUser(email)) {
			System.out.print("nome (maximo 50 caracteres): ");
			String name = input.nextLine();
			int i = 0;
			boolean right;
			String password;
			do {
				right = true;
				System.out.print("password (entre 3 e 5 caracteres - digitos e letras): ");
				password = input.next();//verificar que tem so digitos e letras
				input.nextLine();
				if (password.length() < 3 || password.length() > 5) {
					i++;
					right = false;
					System.out.println("Password incorrecta.");
					if(i==3) {
						System.out.println("Registo nao efetuado.");
					}
				}
			} while (i < 3 && !right);
			User user = new User(email, name, password);
			userData.addUser(user);
			System.out.println("Registo efetuado.");
		} else {
			System.out.println("Utilizador ja existente.");
			System.out.println("Registo nao efetuado.");
		}
	}

	private static void processHelp(FctBoleia a) {
		if (a.getCurrentUser() == null) {
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
		UserData userData = new UserData();
		String option = "";
		do {
			if (a.getCurrentUser() != null) {
				System.out.print(a.getCurrentUser().getEmail() + " > ");// prompt dentro de sessao
				option = readMenuOption(input);
				executeMenuOption(input, option, a, userData);

			} else {
				System.out.print(PROMPTOUT);
				option = readMenuOption(input);
				executeMenuOption(input, option, a, userData);
			}
		} while (!option.equals(END) || a.getCurrentUser() != null);
		input.close();
	}
}