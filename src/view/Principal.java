package view;

import java.util.concurrent.Semaphore;

import controller.ThreadsTransacoes;
import controller.ThreadsTransacoes2;
import controller.ThreadsTransacoes3;

public class Principal {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int idThread = 1; idThread < 22; idThread++) {
			if(idThread%3 == 1){
				Thread tThread = new ThreadsTransacoes(idThread, semaforo);
				tThread.start();
			} else if (idThread%3 == 2){
				Thread tThread2 = new ThreadsTransacoes2(idThread, semaforo);
				tThread2.start();
			} else {
				Thread tThread3 = new ThreadsTransacoes3(idThread, semaforo);
				tThread3.start();
			}
			
		}

	}

}
