package com.bancoBMLC.springboot.app.controller;

import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bancoBMLC.springboot.app.errors.DataBaseBancoException;
import com.bancoBMLC.springboot.app.models.dao.CuentaDAO;
import com.bancoBMLC.springboot.app.models.dao.TarjetaDAO;
import com.bancoBMLC.springboot.app.models.entity.Cuenta;
import com.bancoBMLC.springboot.app.models.entity.Tarjeta;

@Controller
@SessionAttributes("tarjeta")
public class TarjetaController {
	
	@Autowired
	private TarjetaDAO tarjetaDao;
	
	@Autowired
	private CuentaDAO cuentaDao;
	
	@RequestMapping(value = "/tarjetas-lista", method = RequestMethod.GET)
	public String listar(Model model){
		model.addAttribute("titulo", "Lista de tarjetas");
		model.addAttribute("tarjetas", tarjetaDao.findAll());
		return "tarjetas-lista";
	}
		
	@RequestMapping(value = "/form-tarjeta")
	public String crear(Map<String, Object> model, Model modelList) {
		Tarjeta tarjeta = new Tarjeta();
		List<Cuenta> listaCuentas = cuentaDao.findAll();
		model.put("tarjeta", tarjeta);
		modelList.addAttribute("listaCuentas", listaCuentas);
		model.put("titulo", "Llenar los datos de una tarjeta");
		return "form-tarjeta";
	}
	
	@RequestMapping(value = "/form-tarjeta/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Tarjeta tarjeta = null;
		if(id>0){
			tarjeta = tarjetaDao.findOne(id);
		} else {
			return "redirect:/index";
		}
		model.put("tarjeta", tarjeta);
		model.put("titulo", tarjeta);
		
		return "form-tarjeta";
	}
	
	@RequestMapping(value = "/formtarjeta", method = RequestMethod.POST)
	public String guardar(@Valid Tarjeta tarjeta, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash){
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Llene correctamente los campos");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar los datos, por favor escriba correctamente sus datos: ");
			return "form-tarjeta";
		} else {
			model.addAttribute("result", false);
		}
		
		model.addAttribute("titulo", "Formulario de tarjeta");
		model.addAttribute("mensaje", "Se envio la informacion correctamente");
		try {
			tarjetaDao.save(tarjeta);
		} catch(DataBaseBancoException e) {
			e.printStackTrace();
			flash.addFlashAttribute("mensaje", e.getMessage());
		}
		status.setComplete();
		
		return "redirect:form-tarjeta";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id>0) {
			tarjetaDao.delete(id);
		}
		return "redirect:index";
	}
}
