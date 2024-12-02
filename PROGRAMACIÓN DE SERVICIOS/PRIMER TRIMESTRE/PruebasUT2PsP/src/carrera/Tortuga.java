package carrera;

public class Tortuga implements Runnable {
	
	public Tortuga(int num) {
		
	}

	@Override
	public void run() {

		int i = 0;
		System.out.println("Empieza la tortuga");
		while(i<5) {
			try {
				Thread.sleep(5000);
				System.out.println(" T ");
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			i++;
			
		}
		System.out.println(" La tortuga llega a la meta");
	}

}
