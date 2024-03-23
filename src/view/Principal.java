package view;

import java.util.concurrent.Semaphore;

import controller.ThreadController;

public class Principal {

	public static void main(String[] args) {
		int permissoes = 5;
		Semaphore semaforo = new Semaphore(permissoes);

		for (int idThread = 0; idThread < 25; idThread++) {
			Thread tCont = new ThreadController(idThread, semaforo);
			tCont.start();
		}
	}
}