package com.ace.others;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class VideoAnalysis extends Thread{
	private String mes;

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	
	@Override
	public void run() {
		Socket socket;
		String host = "localhost";
		int port = 9999;
		try {
			socket = new Socket(host, port);
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write(mes);
			pw.flush();
			socket.shutdownOutput();
			System.out.println("...");
			pw.close();
			os.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("Failed...");
			e.printStackTrace();
		} 
	}
}
