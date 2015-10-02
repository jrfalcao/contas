/**
 * 
 */
package com.junior.contas.web.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.junior.contas.modelo.Conta;
import com.junior.contas.web.dao.ContaDAO;

/**
 * @author Junior Falcão / juniorfalcao.jc@gmail.com
 * 
 */
@Controller
public class ContaController {
	
	private final ContaDAO contaDao;
	
	@Autowired
	public ContaController(ContaDAO contaDao) {
		this.contaDao = contaDao;
	}

	@RequestMapping("/form")
	public String formulario() {
		return "conta/formulario";
	}
	
	@RequestMapping("/pagaConta")
	public String pagaConta(Conta conta, HttpServletResponse response) {
		
		response.setStatus(HttpStatus.OK.ordinal());
		//contaDao.paga(conta.getId());
		return "redirect:listaContas";
	}

	@RequestMapping(value = "/adicionaConta")
	public String adicionaConta(@Valid Conta conta, BindingResult result) throws SQLException {
		System.out.println("Conta adicionada e: " + conta.getDescricao()
				+ " com tipo " + conta.getTipo());
		if(result.hasErrors()) {
			return "conta/formulario";
		}
		contaDao.adiciona(conta);
		
		return "conta/conta-adicionada";
	}

	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {
		return "redirect:listaContas";
	}

	@RequestMapping("/mostraConta")
	public String mostraConta(Long id, Model model) {
		model.addAttribute("conta", contaDao.buscaPorId(id));

		return "conta/mostra";
	}

	@RequestMapping("removeConta")
	public String remove(Conta conta) {
		contaDao.remove(conta);
		System.out.println("Conta deletada");
		return "redirect:listaContas";
	}

	@RequestMapping("/listaContas")
	public String lista(Model mv) throws SQLException {
		mv.addAttribute("contas", contaDao.lista());
		return "conta/lista";
	}

}
