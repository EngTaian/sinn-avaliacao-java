package br.com.sinn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinn.domain.Enterprise;
import br.com.sinn.services.ServiceEnterprise;

@RestController
@RequestMapping(value="/enterprises")
public class EnterpriseController {
	
	@Autowired
	ServiceEnterprise service;
	
	@RequestMapping(method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Enterprise>> getAll(){
		List<Enterprise> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	@RequestMapping(value="/page", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Page<Enterprise>> getPageable(
			@RequestParam(value="page", required=false, defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", required=false, defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy", required=false, defaultValue="name")String orderBy,
			@RequestParam(value="direction", required=false, defaultValue="ASC")String direction){
		Page<Enterprise> obj = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Enterprise> getById(@PathVariable Integer id){
		Enterprise obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Enterprise> insert(@RequestBody Enterprise enterprise){
		Enterprise obj = service.insert(enterprise);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = "application/json")
	@ResponseBody	
	public ResponseEntity<Enterprise> put(@RequestBody Enterprise enterprise, @PathVariable Integer id){
		Enterprise obj = service.update(enterprise, id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Void> delete(@RequestBody Enterprise enterprise){
		service.delete(enterprise);
		return ResponseEntity.noContent().build();
	}
	
}
