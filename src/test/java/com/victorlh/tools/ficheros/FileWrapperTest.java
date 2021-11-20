package com.victorlh.tools.ficheros;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class FileWrapperTest {

	private static final String FILE1 = "file1.txt";
	private static final String FILE1_TEXT = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

	@Test
	public void testFichero() {
		File f = new File(FILE1);
		FileWrapper fw = new FileWrapper(f);

		assertFalse(fw.existe());

		try {
			fw.crearFile();
		} catch (IOException e) {
			fail("Error al crear el fichero");
		}

		assertTrue(fw.existe());

		String contenido = fw.getContenido();
		assertTrue(contenido == null || "".equals(contenido));
		assertArrayEquals(new byte[0], fw.getContenidoBytes());

		try {
			fw.setContenido(FILE1_TEXT);
		} catch (IOException e1) {
			fail("Error al escribir el fichero");
		}

		assertEquals(FILE1_TEXT, fw.getContenido().trim());
		assertArrayEquals(FILE1_TEXT.getBytes(), fw.getContenidoBytes());

		try {
			fw.eliminarFichero();
		} catch (IOException e) {
			fail("Error al eliminar el fichero");
		}
	}

}
