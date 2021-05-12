/**
 * Lab 06 Computacao Concorrente
 * Miguel Lima Tavares DRE:119161571
 * 
 */


//classe que estende Thread e implementa a tarefa de cada thread do programa 
class T extends Thread {
	
	private int id;
	public int[] vetor;
	public int nThreads;

	//construtor
	public T(int tid, int[] vetor, int nThreads) {
		this.id = tid;
		this.vetor = vetor;
		this.nThreads = nThreads;
	}

	//metodo main da thread
	public void run() {
		//System.out.println("Thread " + this.id + " iniciou!");
		for (int i=this.id; i<this.vetor.length; i+=nThreads) {
			this.vetor[i]++;
		}
	}
}

//main da aplicação
class Main {
	
	static int nThreads = 8; //numero de Threads
	static int tamVetor = 30; // tamVetoranho o vetor a ser somado
	
	public static void main (String[] args) {
		
		//espaco para criacao das Threads
		Thread[] Threads = new Thread[nThreads];
		

		//criacao de um vetor com numeros aleatorios entre 0 e 8
		int[] vetor = new int[tamVetor];
		
		
		for (int i = 0; i< tamVetor; i++ ) {
			vetor[i] = (int)(Math.random() * ((8 - 0) + 1)) + 0;
			System.out.printf("%d ",vetor[i]);
		}
		System.out.printf("\n\n");
		
		//cria as Threads e as inicializa
		for (int i=0; i<Threads.length; i++) {
		   Threads[i] = new T(i, vetor, nThreads);
		}

		for (int i=0; i<Threads.length; i++) {
		   Threads[i].start();
		}

		//espera pelo termino das Threads
		for (int i=0; i<Threads.length; i++) {
		   try { Threads[i].join(); } catch (InterruptedException e) { return; }
		}
		for (int i = 0; i< tamVetor; i++) {
			System.out.printf("%d ",vetor[i]);
		}
		System.out.printf("\n");
	}
}
