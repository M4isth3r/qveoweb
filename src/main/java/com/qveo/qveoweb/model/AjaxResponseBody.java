package com.qveo.qveoweb.model;

import java.util.List;

public class AjaxResponseBody {
	
	String msg;
	Usuario result;
	List<Usuario> usuarios;
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Usuario getResult() {
		return result;
	}
	public void setResult(Usuario result) {
		this.result = result;
	}
	
	

}
