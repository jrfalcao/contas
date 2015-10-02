package com.junior.contas.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.junior.contas.modelo.Usuario;
import com.junior.contas.web.dao.UsuarioDAO;

@Controller
public class UsuarioController {
	
	private UsuarioDAO usuarioDAO;

	@Autowired
	public UsuarioController(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@RequestMapping("/loginForm")
	public String loginForm() {
		
		
		return "usuario/login";
	}
	
	@RequestMapping(value="/efetuaLogin", method=RequestMethod.POST)
	public String logaUsuario(Usuario usuario, HttpSession session) {
		if(usuarioDAO.existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "menu";
		}
		
		return "redirect:loginForm";
	}
}
