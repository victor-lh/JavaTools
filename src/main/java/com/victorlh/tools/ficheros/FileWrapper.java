package com.victorlh.tools.ficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import com.victorlh.tools.Transform;

/**
 * Envoltorio de {@link File} con utilidades de acceso al fichero
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 8 nov. 2019
 *
 */
public class FileWrapper {

	private final File file;

	public FileWrapper(File f) {
		this.file = f;
	}

	/**
	 * @return Objeto {@link File}
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Comprueba que el fichero envuelto sea un directorio
	 * 
	 * @return <strong><code>true</code></strong> si es un directorio y
	 *         <strong><code>false</code></strong> en caso contrario
	 * 
	 * @see File#isDirectory()
	 * 
	 */
	public boolean isDirectorio() {
		return file.isDirectory();
	}

	/**
	 * Comprueba que el fichero exista
	 * 
	 * @return <strong><code>true</code></strong> si existe y
	 *         <strong><code>false</code></strong> si no existe
	 * 
	 * @see File#exists()
	 */
	public boolean existe() {
		return file.exists();
	}

	/**
	 * Crea el fichero
	 * 
	 * @throws IOException En caso de no poder crear el fichero
	 * 
	 * @see File#createNewFile()
	 */
	public void crearFile() throws IOException {
		try {
			file.createNewFile();
		} catch (Throwable e) {
			throw new IOException(e);
		}
	}

	/**
	 * Crea el directorio
	 * 
	 * @throws IOException - En caso de no poder crear el directorio
	 * 
	 * @see File#mkdirs()
	 */
	public void crearDirectorio() throws IOException {
		boolean creado = file.mkdirs();
		if (!creado) {
			throw new IOException("Error al crear el directorio");
		}
	}

	/**
	 * @return La ruta del fichero
	 * @see File#getPath()
	 */
	public String getPath() {
		return file.getPath();
	}

	/**
	 * @return {@link File} padre
	 * @see File#getParentFile()
	 */
	public File getParent() {
		return file.getParentFile();
	}

	/**
	 * @return Nombre del fichero
	 * @see File#getName()
	 */
	public String getName() {
		return file.getName();
	}

	/**
	 * @return Extensión del fichero
	 * @see ToolsFichero#getExtension(String)
	 */
	public String getExtension() {
		return ToolsFichero.getExtension(getName());
	}

	/**
	 * @return Nombre del fichero sin extensión
	 * @see ToolsFichero#getNombreSinExtension(String)
	 */
	public String getNombreSinExtension() {
		return ToolsFichero.getNombreSinExtension(getName());
	}

	/**
	 * @return Lista de {@link File} hijos
	 * @see File#listFiles()
	 */
	public File[] getFiles() {
		return file.listFiles();
	}

	/**
	 * @param filter - Filtro de la lista resultante
	 * @return Lista de {@link File} hijos
	 * @see File#listFiles(FilenameFilter)
	 */
	public File[] getFiles(FilenameFilter filter) {
		return file.listFiles(filter);
	}

	/**
	 * 
	 * @return {@link InputStream} con el contenido del fichero o
	 *         <code><b>null</b></code> si el fichero no existe
	 */
	public InputStream getContenidoInputStream() {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/**
	 * 
	 * @return {@link String} con el contenido del fichero o <code>null</code> si el
	 *         fichero no existe
	 */
	public String getContenido() {
		try (InputStream is = getContenidoInputStream()) {
			return Transform.toString(is);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 
	 * @return Array de {@link Byte} con el contenido del fichero o
	 *         <code>null</code> si el fichero no existe
	 */
	public byte[] getContenidoBytes() {
		try (InputStream is = getContenidoInputStream()) {
			byte[] buffer = new byte[is.available()];
			if (is.read(buffer) == -1) {
				throw new IOException("EOF reached while trying to read the whole file");
			}
			return buffer;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Escribe el contenido indicado en el fichero, <b>el fichero pierde el
	 * contenido antiguo</b>
	 * 
	 * @param contenido - Contenido a escribir en el fichero
	 * @throws IOException En caso de error al escribir
	 */
	public void setContenido(byte[] contenido) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(contenido);
		}
	}

	/**
	 * Escribe el contenido indicado en el fichero, <b>el fichero pierde el
	 * contenido antiguo</b>
	 * 
	 * @param contenido - Contenido a escribir en el fichero
	 * @throws IOException En caso de error al escribir
	 */
	public void setContenido(String contenido) throws IOException {
		byte[] bytes = contenido.getBytes();
		setContenido(bytes);
	}

	/**
	 * Escribe el contenido indicado en el fichero, <b>el fichero pierde el
	 * contenido antiguo</b>
	 * 
	 * @param contenido - Contenido a escribir en el fichero
	 * @throws IOException En caso de error al escribir
	 */
	public void setContenido(InputStream contenido) throws IOException {
		try (FileWriter fw = new FileWriter(file)) {
			int c;
			while ((c = contenido.read()) != -1) {
				fw.write(c);
			}
		}
	}

	/**
	 * Añade el contenido indicado al fichero
	 * 
	 * @param contenido - Contenido a escribir en el fichero
	 * @throws IOException En caso de error al escribir
	 */
	public void appendContendio(String contenido) throws IOException {
		try (FileWriter fw = new FileWriter(file, true)) {
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				bw.write(contenido);
			}
		}
	}

	/**
	 * Añade las lineas indicadas al fichero
	 * 
	 * @param lineas - Lineas a escribir en el fichero
	 * @throws IOException En caso de error al escribir
	 */
	public void appendLineas(String... lineas) throws IOException {
		try (FileWriter fw = new FileWriter(file, true)) {
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				for (String linea : lineas) {
					bw.write(linea);
					bw.newLine();
				}
			}
		}
	}

	/**
	 * Elimina el fichero
	 * 
	 * @throws IOException En caso de no poder eliminar el fichero
	 * 
	 * @see File#delete()
	 */
	public void eliminarFichero() throws IOException {
		try {
			boolean delete = file.delete();
			if (!delete) {
				throw new IOException("Error al eliminar el fichero");
			}
		} catch (Throwable e) {
			throw new IOException("Error al eliminar el fichero", e);
		}
	}

	/**
	 * Eliminar el directorio de forma recursiva
	 * 
	 * @throws IOException En caso de no poder eliminar el directorio
	 */
	public void eliminarDirectorioRecursivo() throws IOException {
		File[] files = getFiles();
		for (int i = 0; i < files.length; i++) {
			FileWrapper wrapper = new FileWrapper(files[i]);
			if (wrapper.isDirectorio()) {
				wrapper.eliminarDirectorioRecursivo();
			} else {
				wrapper.eliminarFichero();
			}
		}
		boolean delete = file.delete();
		if (!delete) {
			throw new IOException("Error al eliminar el fichero");
		}
	}
}
