public class Numero {

	private static int permutaciones;

	public static boolean esPrimo(int numero) {
		for (int i = 2; i < numero; i++) {
			if (numero % i == 0)
				return false;
		}
		return true;
	}

	public static boolean esPrimoCirular(int valor) {
		int coincidencias = 0;
		if (esPrimo(valor)) {
			permutaciones = Integer.toString(valor).length() - 1;
			for (int i = 0; i < permutaciones; i++) {
				Character digito = Integer.toString(valor).charAt(0);
				String cadena = Integer.toString(valor).substring(1) + digito;
				Integer numeroPermutado = Integer.valueOf(cadena);
				if (esPrimo(numeroPermutado))
					coincidencias++;
			}
			if (coincidencias == permutaciones) {
				return true;
			}
		}
		return false;
	}

}
