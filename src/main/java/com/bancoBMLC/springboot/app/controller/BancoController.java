package com.bancoBMLC.springboot.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bancoBMLC.springboot.app.models.dao.BancoDAO;
import com.bancoBMLC.springboot.app.models.entity.Banco;

@Controller
@SessionAttributes("banco")
public class BancoController {
	
	@Autowired
	private BancoDAO bancoDao;
	
	@RequestMapping(value="/bancos-lista", method = RequestMethod.GET)
	public String bancoLista(Model model) {
		model.addAttribute("titulo", "Lista de bancos");
		model.addAttribute("bancos", bancoDao.findAll());
		return "bancos-lista";
	}
	
	@RequestMapping(value = "form-banco")
	public String crear(Map<String, Object> model) {
		Banco banco = new Banco();
		model.put("banco", banco);
		model.put("titulo", "Nuevo Banco");
		return "form-banco";
	}
	
	@RequestMapping(value = "/form-banco/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Banco banco = null;
		
		if(id>0){
			banco = bancoDao.findOne(id);
		} else {
			return "redirect:/bancos-lista";
		}
		model.put("banco", banco);
		model.put("titulo", "Edite los datos");
		return "form-banco";
	}
	
	@RequestMapping(value = "form-banco", method = RequestMethod.POST)
	public String guardar(@Valid Banco banco, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario del Banco");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al registrar los datos del banco");
			model.addAttribute("errList", result.getFieldError());
			return "form-banco";
		}
		bancoDao.save(banco);
		status.setComplete();
		return "redirect:/form-banco";
	}
	
	@RequestMapping(value = "/eliminarbanco/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id>0) {
			bancoDao.delete(id);
		}
		return "redirect:/bancos-lista";
	}
	
}
