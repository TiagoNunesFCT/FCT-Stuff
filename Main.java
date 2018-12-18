import java.util.Scanner;
import java.util.Locale;

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
	
	//mensagens
	private static final String UNREGISTERED = "Deslocacao nao registada.";
	private static final String REGISTERED = "Deslocacao registada. Obrigado ";
	private static final String INVALID_DATA = "Dados invalidos.";
	private static final String DUPLICATE =  "ja tem uma deslocacao registada nesta data.";
	private static final String INVALID_DATE = "Data invalida.";
	private static final String REGISTERED_RIDE = "Boleia registada.";
	private static final String NO_USER = "Utilizador inexistente.";
	private static final String NO_RIDE = "Deslocacao nao existe.";
	private static final String FREE_SEATS = "Lugares vagos: ";
	private static final String REMOVED = "Deslocacao removida.";
	private static final String CANNOT_REMOVE = " ja nao pode eliminar esta deslocacao.";
	private static final String NO_USER2 = "Utilizador nao existente.";
	private static final String NAME = "nome (maximo 50 caracteres): ";
	private static final String PASSWORD1 = "password (entre 3 e 5 caracteres - digitos e letras): ";
	private static final String INCORRECT = "Password incorrecta.";
	private static final String NO_REGISTER = "Registo nao efetuado.";
	private static final String REGISTER1 = "Registo efectuado.";
	private static final String DUP_USER = "Utilizador ja existente.";
	private static final String AJUDA = "ajuda - Mostra os comandos existentes";
	private static final String REGISTA = "regista - Regista um novo utilizador no programa";
	private static final String TERMINA = "termina - Termina a execucao do programa";
	private static final String ENTRADA = "entrada - Permite a entrada (\"login\") dum utilizador no programa";
	private static final String SAI = "sai - Termina a sessao deste utilizador no programa";
	private static final String NOVA = "nova - Regista uma nova deslocacao";
	private static final String LISTA = "lista - Lista todas ou algumas deslocacoes registadas";
	private static final String BOLEIA = "boleia - Regista uma boleia para uma dada deslocacao";
	private static final String CONSULTA = "consulta - Lista a informacao de uma dada deslocacao";
	private static final String REMOVE2 =  "remove - Retira uma dada deslocacao";
	private static final String REGISTERED_RIDES = "Boleias registadas: ";
	private static final String NO_RIDES = " nao existem deslocacoes registadas para ";
	private static final String NO_SEAT = " nao existe lugar. Boleia nao registada.";
	private static final String SELF_RIDE = " nao pode dar boleia a si propria. Boleia nao registada.";
	
	private static String readMenuOption(Scanner input) {
		return input.next().toUpperCase();
	}

	private static void executeMenuOption(Scanner input, String option, CurrentUser a, UserData userData) {
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
				processRemove(input, a);
				break;
			default:
				processComandoInexistente(input);
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
				processComandoInexistente(input);
				break;
			}
	}

	private static void processLogout(CurrentUser a) {
		a.logout();
		System.out.println(ENDMESSAGE);
	}

	private static void processNewRide(Scanner input, UserData userData, CurrentUser a) {
		input.nextLine();
		String origin = input.nextLine();
		String destination = input.nextLine();
		String date = input.next();
		BasicDate basicDate = new BasicDate(date);
		int time = input.nextInt();
		double duration = input.nextDouble();
		int seats = input.nextInt();
		if (!a.getCurrentUser().getRideData().hasRide(basicDate)) {
			if ((time >= 0 && time <= 24) && duration > 0 && basicDate.isValid() && duration >= 0 && seats >= 0) {
				Ride ride = new Ride(origin, destination, basicDate, time, duration, seats);
				a.getCurrentUser().addRide(ride);
				System.out.println(REGISTERED + a.getCurrentUser().getName() + ".");
			} else {
				System.out.println(INVALID_DATA);
				System.out.println(UNREGISTERED);
			}
		} else {
			System.out.println(a.getCurrentUser().getName() + DUPLICATE);
			System.out.println(UNREGISTERED);
		}
	}

	private static void processUserRideList(Scanner input, CurrentUser a, UserData userData) {// WIP
		String date = input.nextLine();
		if (date.equals("")) {
			printVoidList(a, userData);
		} else {
			BasicDate basicDate = new BasicDate(date);
			if (basicDate.isValid()) {
				printDateList(a, basicDate, userData);
			} else {
				System.out.println(INVALID_DATE);
			}
		}
	}

	private static void processRide(Scanner input, UserData userData, CurrentUser a) {
		String email = input.next();
		String date = input.next();
		input.nextLine();
		BasicDate basicDate = new BasicDate(date);
		if (validation(userData, basicDate, a, email)) {
			userData.getUser(email).getRideData().getRide(basicDate).seatsDec();
			System.out.println(REGISTERED_RIDE);
		}
	}

	private static void processCheck(Scanner input, UserData userData) {
		String email = input.next();
		String date = input.next();
		BasicDate basicDate = new BasicDate(date);
		input.nextLine();
		if (!userData.hasUser(email)) {
			System.out.println(NO_USER);
		} else {
			if (!basicDate.isValid()) {
				System.out.println(INVALID_DATE);
			} else if (userData.getUser(email).getRideData().getRide(basicDate) == null) {
				System.out.println(NO_RIDE);
			} else {
				printRideInfo(userData, email, basicDate);
				System.out.println(FREE_SEATS
						+ userData.getUser(email).getRideData().getRide(basicDate).getAvailableSeats());
			}
		}
	}

	private static void processRemove(Scanner input, CurrentUser a) {
		String date = input.next();
		BasicDate basicDate = new BasicDate(date);
		if (basicDate.isValid()) {
			if (a.getCurrentUser().getRideData().hasRide(basicDate)) {
				if (a.getCurrentUser().getRideData().getRide(basicDate).getSeatsTaken() == 0) {
					a.getCurrentUser().getRideData().remove(basicDate);
					a.getCurrentUser().rideNumberDec();
					System.out.println(REMOVED);
				} else {
					System.out.println(a.getCurrentUser().getName() + CANNOT_REMOVE);
				}
			} else {
				System.out.println(NO_RIDE);
			}
		} else {
			System.out.println(INVALID_DATE);
		}
	}

	private static void processLogin(Scanner input, UserData userData, CurrentUser a) {
		String email = input.next();
		if (userData.hasUser(email)) {
			boolean right = false;
			int i = 0;
			do {
				System.out.print("password: ");
				String password = input.next();
				input.nextLine();
				if (password.equals(userData.getPassword(email))) {
					right = true;
					a.setCurrentUser(userData.getUser(email));
				} else {
					System.out.println(WRONGPASSWORD);
				}
				i++;
			} while (!right && i < 3);
		} else {
			System.out.println(NO_USER2);
		}
	}

	private static void processComandoInexistente(Scanner input) {
		System.out.println(ERROR);
		input.nextLine();
	}

	private static void processEnd() {
		System.out.println(ENDMESSAGE);
	}

	private static void processRegister(Scanner input, UserData userData) {
		String email = input.next();
		input.nextLine();
		if (!userData.hasUser(email)) {
			System.out.print(NAME);
			String name = input.nextLine();
			int i = 0;
			boolean right;
			String password;
			do {
				right = true;
				System.out.print(PASSWORD1);
				password = input.next();
				input.nextLine();
				if (invalidPassword(password)) {
					i++;
					right = false;
					System.out.println(INCORRECT);
					if (i == 3) {
						System.out.println(NO_REGISTER);
					}
				} else {
					User user = new User(email, name, password);
					userData.addUser(user);
					System.out.println(REGISTER1);
				}
			} while (i < 3 && !right);
		} else {
			System.out.println(DUP_USER);
			System.out.println(NO_REGISTER);
		}
	}

	private static void processHelp(CurrentUser a) {
		if (a.getCurrentUser() == null) {
			System.out.println(AJUDA);
			System.out.println(TERMINA);
			System.out.println(REGISTA);
			System.out.println(ENTRADA);
		} else {
			System.out.println(AJUDA);
			System.out.println(SAI);
			System.out.println(NOVA);
			System.out.println(LISTA);
			System.out.println(BOLEIA);
			System.out.println(CONSULTA);
			System.out.println(REMOVE2);
		}
	}

	private static void printDateList(CurrentUser a, BasicDate basicDate, UserData userData) {
		boolean shown = false;
		if (basicDate.isValid()) {
			Iterator it = userData.iterator();
			while (it.hasNext("user")) {
				User temp = it.nextUser();
				if (temp.getRideData().hasRide(basicDate)) {
					System.out.println(temp.getEmail());
					System.out.println(temp.getRideData().getRide(basicDate).getOrigin());
					System.out.println(temp.getRideData().getRide(basicDate).getDestination());
					System.out.print(temp.getRideData().getRide(basicDate).getDate().dateToString() + " "
							+ temp.getRideData().getRide(basicDate).getTime() + " ");
					System.out.print( temp.getRideData().getRide(basicDate).getDuration());
					System.out.println(" " + temp.getRideData().getRide(basicDate).getSeats());
					System.out.println(REGISTERED_RIDES + temp.getRideData().getRide(basicDate).getSeatsTaken());
					System.out.println();
					shown = true;
				}
			}
			if (!shown) {
				System.out.println(a.getCurrentUser().getName() + NO_RIDES
						+ basicDate.dateToString() + ".");
			}
		} else {
			System.out.println(INVALID_DATE);
		}
	}

	private static void printVoidList(CurrentUser a, UserData userData) {
		if (a.getCurrentUser().getRideNumber() != 0) {
			Iterator it = a.getCurrentUser().getRideData().iterator();
			while (it.hasNext("ride")) {
				Ride temp = it.nextRide();
				printRideInfo(userData, a.getCurrentUser().getEmail(), temp.getDate());
				System.out.println(REGISTERED_RIDES + temp.getSeatsTaken());
				System.out.println();
			}
		} else {
			System.out.println(a.getCurrentUser().getName() + NO_RIDES);
		}
	}

	private static boolean validation(UserData userData, BasicDate basicDate, CurrentUser a, String email) {
		boolean valid = true;
		if (userData.hasUser(email)) {
			if (basicDate.isValid()) {
				if (userData.getUser(email).getRideData().hasRide(basicDate)) {
					if (!email.equals(a.getCurrentUser().getEmail())) {
						if (userData.getUser(email).getRideData().getRide(basicDate).getAvailableSeats() > 0) {
						} else {
							System.out
									.println(a.getCurrentUser().getName() + NO_SEAT);
							valid = false;
						}
					} else {
						System.out.println(a.getCurrentUser().getName()
								+ SELF_RIDE);
						valid = false;
					}
				} else {
					System.out.println(NO_RIDE);
					valid = false;
				}
			} else {
				System.out.println(INVALID_DATE);
				valid = false;
			}
		} else {
			System.out.println(NO_USER);
			valid = false;
		}
		return valid;
	}

	private static boolean invalidPassword(String password) {
		int n = 0;
		boolean invalid = false;
		int count = 0;
		while (n < password.length()) {
			if (((password.charAt(n) > 122) || ((password.charAt(n) > 90) && (password.charAt(n) < 97))
					|| (password.charAt(n) < 48))) {
				count++;
			}
			n++;
		}
		if ((count != 0) || (password.length() < 3 || password.length() > 5)) {
			invalid = true;
		}
		return invalid;
	}

	private static void printRideInfo(UserData userData, String email, BasicDate basicDate) {
		System.out.println(userData.getUser(email).getRideData().getRide(basicDate).getOrigin());
		System.out.println(userData.getUser(email).getRideData().getRide(basicDate).getDestination());
		System.out.print(userData.getUser(email).getRideData().getRide(basicDate).getDate().dateToString() + " "
				+ userData.getUser(email).getRideData().getRide(basicDate).getTime() + " ");
		System.out.print(userData.getUser(email).getRideData().getRide(basicDate).getDuration());
		System.out.println(" " + userData.getUser(email).getRideData().getRide(basicDate).getSeats());
	}

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		Scanner input = new Scanner(System.in);
		CurrentUser a = new CurrentUser();
		UserData userData = new UserData();
		String option = "";
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