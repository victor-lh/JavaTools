package com.victorlh.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class ToolsFichero {
	
	public static String getExtensionFileName(String fileName){
		fileName = fileName.trim();
		int index = fileName.lastIndexOf(".");
		return fileName.substring(index+1);
	}

	public static String getContenidoString(File fichero) {
		try(InputStream is = getContenidoIS(fichero)) {
			return Transform.toString(is);
		} catch (IOException e) {
			return "";
		}
		
	}
	
	public static InputStream getContenidoIS(File fichero) {
		try {
			return new FileInputStream(fichero);
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	public static void addToFile(File fichero, String... lineas) throws IOException {
		try(FileWriter fw = new FileWriter(fichero, true)) {
			try(BufferedWriter bw = new BufferedWriter(fw)) {
				for (String linea : lineas) {
					bw.write(linea);
				}
			}
		}
	}
	
	public static File getFicheroHijo(File padre, String hijo) {
		File[] listFiles = padre.listFiles();
		String[] nombreFiles = padre.list();
		
		if(hijo.endsWith("/") || hijo.endsWith("\\")){
			hijo = hijo.substring(0, hijo.length()-1);
		}
		
		int indexInArray = Tools.indexInArray(hijo, nombreFiles);
		
		if(indexInArray == -1) {
			return null;
		}
		
		return listFiles[indexInArray];
	}
	
	public static void escribirFichero(File fichero, InputStream is) throws IOException {
		try(FileWriter fw = new FileWriter(fichero)){
			int c;
			while ((c = is.read()) != -1) {
				fw.write(c);
			}
		}
	}
	
	public static void addFileToDirectorio(String pathDirectorio, String archivo, InputStream is) throws IOException {
		if(!pathDirectorio.endsWith(File.separatorChar+"")) {
			pathDirectorio = pathDirectorio.concat(File.separatorChar+"");
		}
		
		String pathArchivo = pathDirectorio.concat(archivo);
		
		File file = new File(pathArchivo);
		if(!file.exists()) {
			file.createNewFile();
		}
		
		escribirFichero(file, is);
	}
}
