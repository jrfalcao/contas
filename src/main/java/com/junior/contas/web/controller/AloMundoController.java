package com.junior.contas.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AloMundoController {
	
	@RequestMapping("/aloMundo")
	public String execute(){
		System.out.println("Acessando controller no String!");
		return "ok";
	}

}
