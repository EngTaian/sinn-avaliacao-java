package br.com.sinn.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.sinn.domain.Advance;
import br.com.sinn.repository.RepositoryAdvance;

@Service
public class ServiceAdvance {
	
	@Autowired
	RepositoryAdvance repo;
	
	public List<Advance> findAll(){
		List<Advance> obj = repo.findAll();
		return obj;
	}
	
	public Advance findById(Integer id) {
		Advance advance = repo.findOne(id);
		return advance;
	}
	
	@Transactional
	public Advance insert(Advance advance) {
		advance.setId(null);
		advance = repo.save(advance);
		return advance;
	}
	
	public Advance update(Advance advance, Integer id) {
		Advance newAdvance = findById(id);
		updateData(newAdvance, advance);
		newAdvance.setId(id);
		newAdvance=repo.save(newAdvance);
		return newAdvance;
	}
	
	public void delete(Advance advance) {
		findById(advance.getId());
		try {
			repo.delete(advance);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById(Integer id) {
		findById(id);
		try {
			repo.delete(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Page<Advance> findPage(Integer page, Integer linesPerPage, String direction, String order){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), order);
		return repo.findAll(pageRequest);
	}
	
	private void updateData(Advance newAdvance, Advance advance) {
		newAdvance.setAdvanceDate(advance.getAdvanceDate());
		newAdvance.setEmployee(advance.getEmployee());
		newAdvance.setInstallmentPlan(advance.getInstallmentPlan());
		newAdvance.setSettled(advance.getSettled());
		newAdvance.setTotalReturnDate(advance.getTotalReturnDate());
		newAdvance.setValue(advance.getValue());
	}
}
