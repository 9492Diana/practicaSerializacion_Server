import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.XML;

public class EjecutableServer extends PApplet {

	Comunicacion com;
	ArrayList<Clic> clics;
	XML bodega;
	XML[] children;

	@Override
	public void setup() {		
		
		String inicial = "<clics></clics>";
		
		clics = new ArrayList<Clic>();
		com = new Comunicacion(clics);
		com.start();
		try{
			bodega = loadXML("../data/bodega.xml");
			children = bodega.getChildren("clic");
			for (int i = 0; i < children.length; i++) {	
				int x = children[i].getInt("x");
				int y = children[i].getInt("y");
				String id = children[i].getString("id");
				clics.add( new Clic(x, y, id));
			}
			System.out.println("ya");
			}
		
		catch(NullPointerException e){
			bodega = parseXML(inicial);
			
		}
		}	
	
	

	@Override
	public void draw() {
		background(255);
		//System.out.println("tam clics: " + clics.size());
		for (int i = 0; i < clics.size(); i++) {
			clics.get(i).pintar(this);
		}
	}

	@Override
	public void exit() {
		System.out.println("me fui");
		
		for (int i = 0; i < clics.size(); i++) {
			String hijo = "<clic x=\"" + clics.get(i).getX() +"\" y=\""+clics.get(i).getY()+"\" id=\""+ clics.get(i).getId() +"\" />";
			XML hijoTemp = parseXML(hijo); 
			bodega.addChild(hijoTemp);
		}		
		System.out.println(bodega.toString());		
		saveXML(bodega, "../data/bodega.xml");
		super.exit();
	}
	
	public void cargar(){
		
	}
}
