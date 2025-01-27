package chat;

import java.net.Socket;

public class ComunHilos {
	int CONEXIONES, ACTUALES, MAXIMO;
	Socket tabla[] = new Socket[MAXIMO];
	String mensajes;
	
	public ComunHilos(int cONEXIONES, int aCTUALES, int mAXIMO, Socket[] tabla) {
		super();
		CONEXIONES = cONEXIONES;
		ACTUALES = aCTUALES;
		MAXIMO = mAXIMO;
		this.tabla = tabla;
	}
	
	public ComunHilos() {
		super();
	}
	public int getCONEXIONES() {
		return CONEXIONES;
	}
	public synchronized void setCONEXIONES(int cONEXIONES) {
		CONEXIONES = cONEXIONES;
	}
	public int getACTUALES() {
		return ACTUALES;
	}
	public synchronized void setACTUALES(int aCTUALES) {
		ACTUALES = aCTUALES;
	}
	public int getMAXIMO() {
		return MAXIMO;
	}
	public void setMAXIMO(int mAXIMO) {
		MAXIMO = mAXIMO;
	}
	public Socket[] getTabla() {
		return tabla;
	}
	public void setTabla(Socket[] tabla) {
		this.tabla = tabla;
	}
	public String getMensajes() {
		return mensajes;
	}
	public synchronized void setMensajes(String mensajes) {
		this.mensajes = mensajes;
	}
	
	public synchronized void addTabla(Socket s, int i) {
		tabla[i] = s;
	}
	
	public Socket getElementoTabla(int i) {
		return tabla[i];
	}
	
}
