package com.victorlh.tools.ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ToolsFichero {

	/**
	 * Devuelve la posición del '.' que indica la extensión del nombre del fichero
	 * indicado
	 * 
	 * @param fileName - Nombre del fichero del que buscar la extensión
	 * @return posicion de la extensión
	 */
	public static int getPosicionDExtension(String fileName) {
		fileName = fileName.trim();
		return fileName.lastIndexOf(".");
	}

	/**
	 * Devuelve la extension del nombre del fichero indicado
	 * 
	 * @param fileName - Nombre del fichero del que sacar la extensión
	 * @return La extensión del fichero
	 */
	public static String getExtension(String fileName) {
		int index = getPosicionDExtension(fileName);
		return fileName.substring(index + 1);
	}

	/**
	 * Devuelve el nombre de fichero indicado sin extensión
	 * 
	 * @param fileName - Nombre del fichero con extensión
	 * @return Nombre del fichero sin extensión
	 */
	public static String getNombreSinExtension(String fileName) {
		int index = getPosicionDExtension(fileName);
		return fileName.substring(0, index);
	}

	/**
	 * Copia los ficheros
	 * 
	 * @param origen  - {@link File} del que copiar el contenido
	 * @param destino - {@link File} al que copiar el contenido
	 * @throws IOException - Si se produce algún error al copiar
	 */
	public static void copiarFicheros(File origen, File destino) throws IOException {
		FileWrapper destinoWrapper = new FileWrapper(destino);

		destinoWrapper.eliminarFichero();
		destinoWrapper.crearFile();

		try (FileInputStream inStream = new FileInputStream(origen);
				FileChannel inChannel = inStream.getChannel();
				FileOutputStream outStream = new FileOutputStream(destino);
				FileChannel outChannel = outStream.getChannel();) {
			inChannel.transferTo(0, inChannel.size(), outChannel);
		}
	}

	public static void moverFicheros(File origen, File destino) throws IOException {
		copiarFicheros(origen, destino);
		FileWrapper wrapper = new FileWrapper(origen);
		wrapper.eliminarFichero();
	}

	public static String getNombreFicheroNoRepetido(final String nombre, File directorioDestino) {
		final String nombreSinExtension = getNombreSinExtension(nombre);
		String extension = getExtension(nombre);

		int aux = 0;
		String nombreSalida = nombre;
		File destino = new File(directorioDestino, nombre);
		while (destino.exists()) {
			FilenameFilter ff = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith(nombreSinExtension);
				}
			};
			String[] list = directorioDestino.list(ff);
			int existentes = list == null ? 0 : list.length;
			if (existentes == 0) {
				return nombreSalida;
			}

			if (aux == 0) {
				aux = existentes;
			} else {
				aux++;
			}

			nombreSalida = nombreSinExtension + " (" + (aux) + ")." + extension;
			destino = new File(directorioDestino, nombreSalida);
		}
		return nombreSalida;
	}

}
