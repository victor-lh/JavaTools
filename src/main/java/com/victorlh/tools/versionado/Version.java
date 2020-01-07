package com.victorlh.tools.versionado;

import com.victorlh.tools.ToolsValidacion;
import com.victorlh.tools.Transform;

/**
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 8 nov. 2019
 *
 */
public class Version {

	private int mayor;
	private int menor;
	private int micro;
	private String sufijo;

	public Version(int mayor, int menor, int micro) {
		this.mayor = mayor;
		this.menor = menor;
		this.micro = micro;
	}

	public Version(int mayor, int menor, int micro, String sufijo) {
		this(mayor, menor, micro);
		this.sufijo = sufijo;
	}

	public static Version getVersionNull() {
		return new Version(0, 0, 0);
	}

	public static Version getVersion(String strVersion) {
		if (ToolsValidacion.isCadenaVacia(strVersion)) {
			return Version.getVersionNull();
		}
		String[] split = strVersion.split("\\.");
		int[] version = new int[3];
		String sufijo = "";

		for (int i = 0; i < split.length && i < 3; i++) {
			String cadena = split[i];
			if (i == 2) {
				int indexOf = cadena.indexOf("-");
				if (indexOf != -1) {
					String aux = cadena.substring(0, indexOf);
					if (indexOf < cadena.length()) {
						sufijo = cadena.substring(indexOf + 1);
					}
					cadena = aux;
				}

			}
			try {
				version[i] = "X".equalsIgnoreCase(cadena) ? -1 : Transform.toInt(cadena);
			} catch (NumberFormatException e) {
				version[i] = 0;
			}
		}

		return new Version(version[0], version[1], version[2], sufijo);
	}

	public int getMayor() {
		return mayor;
	}

	public int getMenor() {
		return menor;
	}

	public int getMicro() {
		return micro;
	}

	public String getSufijo() {
		return sufijo;
	}

	public boolean isMenor(Version version) {
		if (getMayor() < version.getMayor()) {
			return true;
		}
		if (getMayor() > version.getMayor()) {
			return false;
		}

		if (getMenor() < version.getMenor()) {
			return true;
		}
		if (getMenor() > version.getMenor()) {
			return false;
		}

		return getMicro() < version.getMicro();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		if (obj instanceof Version) {
			Version aux = (Version) obj;
			if (mayor == -1 || aux.getMayor() == -1) {
				return true;
			}
			if (menor == -1 || aux.getMenor() == -1) {
				return true;
			}
			if (micro == -1 || aux.getMicro() == -1) {
				return true;
			}

			return mayor == aux.getMayor() && menor == aux.getMenor() && micro == aux.getMicro();
		}

		return false;
	}

	public String getVersionString() {
		StringBuilder sb = new StringBuilder();
		sb.append(mayor == -1 ? "X" : mayor).append(".").append(menor == -1 ? "X" : menor).append(".").append(micro == -1 ? "X" : micro);
		if (ToolsValidacion.isCadenaNoVacia(sufijo)) {
			sb.append("-").append(sufijo);
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return getVersionString();
	}
}
