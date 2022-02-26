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

import com.bancoBMLC.springboot.app.models.dao.CuentaDAO;
import com.bancoBMLC.springboot.app.models.entity.Cuenta;
import com.bancoBMLC.springboot.app.validator.CuentaValidator;

@Controller
@SessionAttributes("cuenta")
public class CuentaController {
	
	@Autowired
	private CuentaDAO cuentaDao;

	@Autowired
	private CuentaValidator cuentaValidator;

	
	@RequestMapping(value="/cuentas-lista", method = RequestMethod.GET)
	public String cuentaLista(Model model) {
		model.addAttribute("titulo", "Lista de cuentas");
		model.addAttribute("cuentas", cuentaDao.findAll());
		return "cuentas-lista";
	}
	
	@RequestMapping(value = "form-cuenta")
	public String crear(Map<String, Object> model) {
		Cuenta cuenta = new Cuenta();
		model.put("cuenta", cuenta);
		model.put("titulo", "Nueva Cuenta");
		return "form-cuenta";
	}
	
	@RequestMapping(value = "/form-cuenta/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Cuenta cuenta = null;
		
		if(id>0){
			cuenta = cuentaDao.findOne(id);
		} else {
			return "redirect:/cuentas-lista";
		}
		model.put("cuenta", cuenta);
		model.put("titulo", "Edite la cuenta");
		return "form-cuenta";
	}
	
	@RequestMapping(value = "form-cuenta", method = RequestMethod.POST)
	public String guardar(@Valid Cuenta cuenta, BindingResult result, Model model, SessionStatus status) {
		
		this.cuentaValidator.validate(cuenta, result);
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de la cuenta");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al registrar la cuenta");
			model.addAttribute("errList", result.getFieldError());
			return "form-cuenta";
		}
		cuentaDao.save(cuenta);
		status.setComplete();
		return "redirect:/form-cuenta";
	}
	
	@RequestMapping(value = "/eliminarcuenta/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id>0) {
			cuentaDao.delete(id);
		}
		return "redirect:/cuentas-lista";
	}
	
}
