
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import simulator.*;

public class ArmSimulator {

	// TODO write javadoc comment
	/**
	 * 
	 */
	private final Program program;
	private final Interpretor interpretor;
	private final GUI gui;
	private final Cpu cpu;
	// TODO write javadoc comment
	/**
	 * 
	 */
	public ArmSimulator() {
		this.gui = new GUI();
		this.cpu = new Cpu();
		this.program = new Program();
		this.interpretor = new Interpretor(this.cpu, this.program);
	}
	
	public void start() {
		new Thread() {
			public void run() {
				ArmSimulator.this.gui.startIHM();
			}
		}.start();
		
		Scanner in = new Scanner(System.in);
		String test = "";
		while (in.hasNext()) {
			test = test + in.nextLine() + System.lineSeparator();
		}
		
		in.close();

		this.program.setNewProgram(test);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			this.interpretor.parseProgram();
			this.cpu.execute();
		} catch (AssemblyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
