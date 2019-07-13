package br.com.sinn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinn.domain.InstallmentPlan;
import br.com.sinn.domain.Installments;
import br.com.sinn.repository.RepositoryInstallmentPlan;
import br.com.sinn.repository.RepositoryInstallments;

@Service
public class ServiceInstallmentPlan {
	
	@Autowired
	RepositoryInstallmentPlan repo;
	
	@Autowired 
	RepositoryInstallments repositoryInstallments;
	
	public List<InstallmentPlan> findAll(){
		List<InstallmentPlan> obj = repo.findAll();
		return obj;
	}
	
	public InstallmentPlan findById(Integer id) {
		InstallmentPlan installmentPlan = repo.findOne(id);
		return installmentPlan;
	}
	
	public InstallmentPlan insert(InstallmentPlan installmentPlan) {
		installmentPlan.setId(null);
		installmentReturn(installmentPlan);		
		installmentPlan.setNumberOfInstallments(installmentPlan.getNumberOfInstallments());
		installmentPlan.setValueToBeReturned(installmentPlan.getValueToBeReturned());
		installmentPlan.setAdvance(installmentPlan.getAdvance());
		installmentPlan = repo.save(installmentPlan);
		return installmentPlan;		
	}
	
	public InstallmentPlan update(InstallmentPlan installmentPlan) {
		InstallmentPlan newInstallmentPlan = findById(installmentPlan.getId());
		updateData(newInstallmentPlan, installmentPlan);
		newInstallmentPlan = repo.save(newInstallmentPlan);
		return newInstallmentPlan;
	}
	
	public void delete(InstallmentPlan installmentPlan) {
		findById(installmentPlan.getId());
		try {
			repo.delete(installmentPlan);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateData(InstallmentPlan newInstallmentPlan, InstallmentPlan installmentPlan) {
		newInstallmentPlan.setAdvance(installmentPlan.getAdvance());
		newInstallmentPlan.setInstallments(installmentPlan.getInstallments());
		newInstallmentPlan.setNumberOfInstallments(installmentPlan.getNumberOfInstallments());
		newInstallmentPlan.setValueToBeReturned(installmentPlan.getValueToBeReturned());
	}
	
	private void installmentReturn(InstallmentPlan installmentPlan) {
		List<Installments> list = new ArrayList<Installments>();
		Integer numInstallment = 0;
		for(Installments x : installmentPlan.getInstallments()) {
			x.setId(null);
			x.setNumberInstallment(numInstallment++);
			x.setPaymentDate(x.getPaymentDate());
			x.setValueInstallment(x.getValueInstallment());
			x.setInstallmentPlan(installmentPlan);
			list.add(x);
		}	
		repositoryInstallments.save(list);
	}
	
}
