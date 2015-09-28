import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimosPal extends Thread {

	//Numero el cual seran evaluados
	private static int NUM = 100000;

	private static List<Integer> lista_primosCirculares = new ArrayList<Integer>();

	private static final int HILOS = 3;

	private static final int BLOQUE = 200;

	private final List<Integer> inicioNums = new ArrayList<>();

	public void addInicioNums(int startNum) {
		this.inicioNums.add(startNum);
	}

	private static void mostrar_lista_elementos(List<Integer> elementos) {
		for (int j = 0; j < elementos.size(); j++) {
			System.out.println(elementos.get(j));

		}
	}

	private synchronized void verPrimosCirculares() {
		// TODO Auto-generated method stub
		for (Integer inicioNum : inicioNums) {
			inicioNum = inicioNum > 2 ? inicioNum : 2;
			for (int i = inicioNum; i <= inicioNum + BLOQUE; i++) {
				
				if (Integer.toString(i).length() < 2 && Numero.esPrimo(i)) {
					lista_primosCirculares.add(i);
				} else {
					if (Numero.esPrimoCirular(i))
						lista_primosCirculares.add(i);
				}				
			}
		}
	}

	public void run() {
		verPrimosCirculares();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrimosPal[] primosPal = new PrimosPal[HILOS];
		int prioridad = 5;
		for (int i = 0; i < primosPal.length; i++) {
			primosPal[i] = new PrimosPal();
			primosPal[i].setPriority(prioridad);
			prioridad--;
		}

		int j = 0;
		for (int i = 0; i < NUM; i += BLOQUE) {
			if (j >= HILOS)
				j = 0;
			primosPal[j++].addInicioNums(i);
		}

		for (int i = 0; i < primosPal.length; i++) {
			primosPal[i].start();
			System.out.println("El hilo: "+i+" empezo a trabajar");
		}

		for (int i = 0; i < primosPal.length; i++) {
			try {
				primosPal[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Collections.sort(lista_primosCirculares);
		mostrar_lista_elementos(lista_primosCirculares);

	}

}
