package controller;

import java.util.concurrent.Semaphore;

public class ThreadsTransacoes3 extends Thread {
	
	private int idThread;
	private Semaphore semaforo;
	
	public ThreadsTransacoes3(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo; 
	}
	
	@Override
	public void run(){
		calculo();
		try {
			semaforo.acquire();
			transacao();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			calculo();
			try {
				semaforo.acquire();
				transacao();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
				calculo();
				try {
					semaforo.acquire();
					transacao();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}
			}
		}

	private void calculo() {
		int calculoTotal = (int)((Math.random()*1001)+1000);
		int tempoCalculo = 0;
		int tempo = 100;
		while (tempoCalculo < calculoTotal) {
			tempoCalculo += tempo;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			double tempoEmMs = Double.valueOf(tempoCalculo);
			tempoEmMs /= 1000;
			System.out.println("Thread #" + idThread + " calculou por " + tempoEmMs + "s");
		}
		System.out.println("Thread #" + idThread + " terminou o Cálculo");
		
	}

	private void transacao() {
		System.out.println("Thread #" + idThread + " está realizando transação");
		try {
			sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread #" + idThread + " terminou a transação");
	}
}
