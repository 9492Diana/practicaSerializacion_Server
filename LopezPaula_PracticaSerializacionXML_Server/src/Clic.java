import java.io.Serializable;
import processing.core.PApplet;


public class Clic implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int x, y;
	private String id;
	//private transient PApplet app;
	
	public Clic(int x, int y, String id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	/*public void setApp(PApplet app) {
		this.app = app;
	}*/
	
	public void pintar(PApplet app) {
		app.fill(255,255,0);
		app.ellipse(x, y, 20, 20);
		app.fill(0);
		app.text(id, x+2, y-10);
	}
	
	public String getId() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
