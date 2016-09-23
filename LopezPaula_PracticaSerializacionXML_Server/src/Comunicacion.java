import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class Comunicacion extends Thread {

	private ArrayList<Clic> ref;
	private DatagramSocket ds;

	public Comunicacion(ArrayList<Clic> clics) {
		ref = clics;
		try {
			ds = new DatagramSocket(5000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				recibir();
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void recibir() {
		try {
			byte[] buf = new byte[80];
			DatagramPacket paq = new DatagramPacket(buf, buf.length);
			ds.receive(paq);
			ByteArrayInputStream bais = new ByteArrayInputStream(paq.getData());
			ObjectInputStream ois = new ObjectInputStream(bais);
			Clic temp = (Clic) ois.readObject();
			ref.add(temp); // el arraylist qu contiene todos los clics
							// recibidos....
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
