package br.com.sinn.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.sinn.domain.Enterprise;
import br.com.sinn.domain.dto.EnterpriseDTO;
import br.com.sinn.repository.RepositoryEnterprise;
import br.com.sinn.services.exception.ObjectNotFoundException;

@Service
public class ServiceEnterprise {

	@Autowired
	RepositoryEnterprise repo;
	
	public List<Enterprise> findAll() {
		List<Enterprise> obj = repo.findAll();
		return obj;
	}

	public Enterprise findById(Integer id) {
		Enterprise obj = repo.findOne(id);
		if(obj == null)
			throw new ObjectNotFoundException("Enterprise id "+ id + " not found! " + Enterprise.class.getName());
		
		return obj;
	}

	@Transactional
	public Enterprise insert(Enterprise enterprise) {
		enterprise.setId(null);		
		enterprise = repo.save(enterprise); 
		return enterprise;
	}

	public Enterprise update(Enterprise enterprise, Integer id) {
		Enterprise newEnterprise = findById(id);
		updateData(newEnterprise, enterprise);
		newEnterprise.setId(id);
		newEnterprise = repo.save(newEnterprise);
		return newEnterprise;
	}
	
	public void delete(Enterprise enterprise) {
		findById(enterprise.getId());
		try {
			repo.delete(enterprise);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById(Integer id) {
		findById(id);
		try {
			repo.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public Page<Enterprise> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Enterprise fromDTO(EnterpriseDTO obj) {
		return new Enterprise(obj.getId(), obj.getName(), null, obj.getBusinessOwner());
	}
	
	private void updateData(Enterprise newEnterprise, Enterprise enterprise) {
		newEnterprise.setName(enterprise.getName());
		newEnterprise.setBusinessOwner(enterprise.getBusinessOwner());
	}
}
