package com.bancoBMLC.springboot.app.models.dao;

import java.util.List;

import com.bancoBMLC.springboot.app.models.entity.Cuenta;

public interface CuentaDAO {
	public List<Cuenta> findAll();
	
	public void save(Cuenta cuenta);

	public Cuenta findOne(Long id);

	public void delete(Long id);

	
}

// <a th:href="@{/tarjetas-lista}">Volver...</a>
//<td>ID de cuenta</td>
//<td>
//	<select th:field="*{cuenta}">
//		<option th:each="cuenta: ${listaCuentas}"
//				th:value="${cuenta.getId()}"
//				th:text="${{cuenta.getId()+ 'Nombre: '+cuenta.getNombre()}"></option>
//	</select>
//</td>