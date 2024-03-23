package controller;

import java.util.concurrent.Semaphore;

public class ThreadController extends Thread {
	private int idThread;
	private Semaphore semaforo;
	private Semaphore semaforo1 = new Semaphore(1);
	private Semaphore semaforo2 = new Semaphore(1);
	private static int[] vet = new int[25];
	private static int i = 0;
	private int corrida = 0;
	private int pontuacao = 0;
	private int ciclismo = 0;
	private int posicao = 250;

	public ThreadController(int idThread, Semaphore semaforo) {
		super();
		this.semaforo = semaforo;
		this.idThread = idThread;
	}

	@Override
	public void run() {
		Corrida();
		try {
			semaforo.acquire();
			Tiro();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		Ciclismo();
		try {
			semaforo1.acquire();
			Pontuacao();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo1.release();
		}
		if (i == 25) {
			try {
				semaforo2.acquire();
				Rank();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo2.release();
			}
		}
	}

	public static void Rank() {
		int aux;
    	for (int k = 0; k < i; k++) {
			for (int j = 0; j < i - 1; j++) {
				if (vet[j] < vet[j + 1]) {
					aux = vet[j];
					vet[j] = vet[j + 1];
					vet[j + 1] = aux;
				}
			}
    	}
    	for (int i = 0; i < 25; i++) {
			System.out.println("O " + (i + 1) + "° lugar ficou com " + vet[i] + " pontos");
		}
	}

	public void Pontuacao() {
	    if (i < vet.length) {
	        vet[i] = pontuacao + posicao;
	        i++;
	        posicao = posicao - 10;
	    }
	}

	public void Ciclismo() {
		while (ciclismo < 4999) {
			ciclismo += (Math.random() * 11) + 30;
			System.out.println("O atleta " + idThread + " percorreu " + ciclismo + " metros no ciclismo\n");
			try {
				sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("O atleta " + idThread + " terminou.");
	}

	public void Tiro() {
		System.out.println("O atleta " + idThread + " pegou uma arma para a prova\n");
		for (int i = 1; i < 4; i++) {
			pontuacao += (int) (Math.random() * 11) + 0;
			System.out.println("O atleta " + idThread + " deu o " + i + "° tiro e fez " + pontuacao);
			try {
				sleep((long) ((Math.random() * 2501) + 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("O atleta " + idThread + " terminou a prova de tiro");
	}

	public void Corrida() {
		while (corrida < 2999) {
			corrida += (int) (Math.random() * 6) + 20;
			System.out.println("O atleta " + idThread + " correu " + corrida + " metros\n");
			try {
				sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("O atleta " + idThread + " terminou a corrida\n");
	}
}