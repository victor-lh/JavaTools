package com.victorlh.tools.reflexion;

public class ErrorReflexion extends RuntimeException {
	private static final long serialVersionUID = -6122508780548900122L;

	private String clase;
	
	public ErrorReflexion(Throwable e, String clase) {
		super(e.getLocalizedMessage(), e);
		setStackTrace(e.getStackTrace());
		this.clase = clase;
	}
	
	public ErrorReflexion(Throwable e, Class<?> clase) {
		this(e, clase.getName());
	}
	
	public ErrorReflexion(String clase) {
		super();
		this.clase = clase;
	}
	
	public ErrorReflexion(Class<?> clase) {
		this(clase.getName());
	}
	
	public String getClase() {
		return clase;
	}
}
