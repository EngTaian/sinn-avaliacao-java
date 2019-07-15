package br.com.sinn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinn.domain.Advance;
import br.com.sinn.services.ServiceAdvance;

@RestController
@RequestMapping(value="/advances")
public class AdvanceController {
	
	@Autowired
	ServiceAdvance service;
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<List<Advance>> findAll(){
		List<Advance> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<Advance> findById(@PathVariable Integer id){
		Advance obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Page<Advance>> findPage(
			@RequestParam(value="page", required=false, defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", required=false, defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy", required=false, defaultValue="nome") String orderBy,
			@RequestParam(value="direction", required=false, defaultValue="ASC") String direction){
		Page<Advance> obj = service.findPage(page, linesPerPage, direction, orderBy);
		return ResponseEntity.ok().body(obj);
	}	
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Advance> insert(Advance advance){
		Advance obj = service.insert(advance);
		return ResponseEntity.ok().body(obj);
	}
	
}
