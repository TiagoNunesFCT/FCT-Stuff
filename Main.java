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
				processUserRideList(input, a, userData);
				break;
			case RIDE:
				processRide(input, userData, a);
				break;
			case CHECK:
				processCheck(input, userData);
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

	private static void processNewRide(Scanner input, UserData userData, FctBoleia a) {
		String origin = input.next();
		input.nextLine();
		String destination = input.next();
		input.nextLine();
		String date = input.next();
		BasicDate basicDate = new BasicDate(date);
		int time = input.nextInt();
		double duration = input.nextDouble();
		int seats = input.nextInt();
		if ((time >= 0 && time <= 24) && duration > 0 && basicDate.isValid()) {
			Ride ride = new Ride(origin, destination, date, time, duration, seats);
			a.getCurrentUser().addRide(ride);
		}
		System.out.println("Deslocacao registada. Obrigado " + a.getCurrentUser().getName() + ".");

	}

	private static void processUserRideList(Scanner input, FctBoleia a, UserData userData) {// WIP
		String date = input.nextLine();
		System.out.println(date.equals(""));
		if (date.equals("")) {
			printVoidList(a, userData);
		} else {
			printDateList(date);
		}
	}

	private static void processRide(Scanner input, UserData userData, FctBoleia a) {
		String email = input.next();
		String date = input.next();
		input.nextLine();
		BasicDate basicDate = new BasicDate(date);
		if (validation(userData, basicDate, a, date, email)) {
			userData.getUser(email).getRideData().getRide(date).seatsDec();
			System.out.println("Boleia registada.");
		}
	}

	private static void processCheck(Scanner input, UserData userData) {
		String email = input.next();
		String date = input.next();
		BasicDate basicDate = new BasicDate(date);
		input.nextLine();
		if (!userData.hasUser(email)) {// o que acontece se o utilizador nao existir e a data nao for valida?
			System.out.println("Utilizador inexistente.");
		} else {
			if (!basicDate.isValid()) {
				System.out.println("Data invalida.");
			} else if (userData.getUser(email).getRideData().getRide(date) == null) {
				System.out.println("Deslocacao nao existe.");
			} else {
				printRideInfo(userData, email, date);
				System.out.println(
						"Lugares vagos: " + userData.getUser(email).getRideData().getRide(date).getAvailableSeats());
			}
		}
	}
	
	private static void printRideInfo(UserData userData, String email, String date) {
		System.out.println(userData.getUser(email).getRideData().getRide(date).getOrigin());
		System.out.println(userData.getUser(email).getRideData().getRide(date).getDestination());
		System.out.println(userData.getUser(email).getRideData().getRide(date).getDate() + " "
				+ userData.getUser(email).getRideData().getRide(date).getTime() + " "
				+ userData.getUser(email).getRideData().getRide(date).getDuration() + " "
				+ userData.getUser(email).getRideData().getRide(date).getSeats());
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

	private static void processRegister(Scanner input, UserData userData) {// dividir em metodos auxiliares
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
				password = input.next();// verificar que tem so digitos e letras
				input.nextLine();
				if (invalidPassword(password)) {
					i++;
					right = false;
					System.out.println("Password incorrecta.");
					if (i == 3) {
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

	private static void printDateList(String date) {
		System.out.println("dateList");
	}

	private static void printVoidList(FctBoleia a, UserData userData) {
		System.out.println("entrou");
		if (a.getCurrentUser().getRideNumber() != 0) {
			Iterator it = a.getCurrentUser().getRideData().iterator();
			System.out.println("entrou");
			System.out.println(it.hasNext());
			while (it.hasNext()) {
				Ride r = it.next();
				printRideInfo(userData, a.getCurrentUser().getEmail(), r.getDate());
				System.out.println("Boleias registadas: " + r.getSeatsTaken());
			}
		} else {
			System.out.println(a.getCurrentUser().getName() + " nao tem deslocacoes registadas.");
		}
	}

	private static boolean validation(UserData userData, BasicDate basicDate, FctBoleia a, String date, String email) {
		boolean valid = true;
		if (userData.hasUser(email)) {
			if (basicDate.isValid()) {
				if (userData.getUser(email).getRideData().hasRide(date)) {
					if (!email.equals(a.getCurrentUser().getEmail())) {
						if (userData.getUser(email).getRideData().getRide(date).getAvailableSeats() > 0) {
							userData.getUser(email).getRideData().getRide(date).seatsDec();
						} else {
							System.out
									.println(a.getCurrentUser().getName() + " nao existe lugar. Boleia nao registada.");
							valid = false;
						}
					} else {
						System.out.println(a.getCurrentUser().getName()
								+ " nao pode dar boleia a si propria. Boleia nao registada.");
						valid = false;
					}
				} else {
					System.out.println("Deslocacao nao existe.");
					valid = false;
				}
			} else {
				System.out.println("Data invalida.");
				valid = false;
			}
		} else {
			System.out.println("Utilizador inexistente.");
			valid = false;
		}
		return valid;
	}

	private static boolean invalidPassword(String pass) {
		int n = 0;
		boolean inValid = false;
		int count = 0;
		while (n < pass.length()) {
			if (((pass.charAt(n) > 122) || ((pass.charAt(n) > 90) && (pass.charAt(n) < 97)) || (pass.charAt(n) < 48))) {
				count++;
			}
			n++;
		}
		if ((count != 0) || (pass.length() < 3 || pass.length() > 5)) {
			inValid = true;
		}
		return inValid;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		FctBoleia a = new FctBoleia();
		UserData userData = new UserData();
		int n = 0;
		String option = "";
		/*
		 * Ride r1 = new Ride("origin", "destination", "01-01-2019", 1, 1, 2); Ride r2 =
		 * new Ride("origin", "destination", "01-01-2019", 1, 1, 2); Ride[] temp = new
		 * Ride[2]; temp[0] = r1; temp[1] = r2; IteratorSorted i = new
		 * IteratorSorted(temp, 2); while (n < temp.length) {
		 * System.out.println(temp[n].getDate());n++; }
		 */
		do {
			if (a.getCurrentUser() != null) {
				System.out.print(a.getCurrentUser().getEmail() + " > ");// prompt dentro de sessao
				option = readMenuOption(input);
				executeMenuOption(input, option, a, userData);

			} else {
				System.out.print(PROMPTOUT);// prompt fora de sessao
				option = readMenuOption(input);
				executeMenuOption(input, option, a, userData);
			}
		} while (!option.equals(END) || a.getCurrentUser() != null);
		input.close();
	}
}