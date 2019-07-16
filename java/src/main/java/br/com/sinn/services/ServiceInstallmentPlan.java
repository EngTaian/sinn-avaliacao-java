package br.com.sinn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.sinn.domain.InstallmentPlan;
import br.com.sinn.domain.Installments;
import br.com.sinn.repository.RepositoryInstallmentPlan;
import br.com.sinn.repository.RepositoryInstallments;
import br.com.sinn.services.exception.ObjectNotFoundException;

@Service
public class ServiceInstallmentPlan {

	@Autowired
	RepositoryInstallmentPlan repo;

	@Autowired
	RepositoryInstallments repositoryInstallments;

	public List<InstallmentPlan> findAll() {
		List<InstallmentPlan> obj = repo.findAll();
		return obj;
	}

	public InstallmentPlan findById(Integer id) {
		InstallmentPlan installmentPlan = repo.findOne(id);
		if (installmentPlan == null)
			throw new ObjectNotFoundException("InstallmentPlan id " + id + " not found! " + InstallmentPlan.class.getName());
		return installmentPlan;
	}

	public Page<InstallmentPlan> findPage(Integer page, Integer linesPerPage, String direction, String order) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), order);
		return repo.findAll(pageRequest);
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

	public InstallmentPlan update(InstallmentPlan installmentPlan, Integer id) {
		InstallmentPlan newInstallmentPlan = findById(id);
		updateData(newInstallmentPlan, installmentPlan);
		newInstallmentPlan.setId(id);
		newInstallmentPlan = repo.save(newInstallmentPlan);
		return newInstallmentPlan;
	}

	public void delete(InstallmentPlan installmentPlan) {
		findById(installmentPlan.getId());
		try {
			repo.delete(installmentPlan);
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

	private void updateData(InstallmentPlan newInstallmentPlan, InstallmentPlan installmentPlan) {
		newInstallmentPlan.setAdvance(installmentPlan.getAdvance());
		newInstallmentPlan.setInstallments(installmentPlan.getInstallments());
		newInstallmentPlan.setNumberOfInstallments(installmentPlan.getNumberOfInstallments());
		newInstallmentPlan.setValueToBeReturned(installmentPlan.getValueToBeReturned());
	}

	private void installmentReturn(InstallmentPlan installmentPlan) {
		List<Installments> list = new ArrayList<Installments>();
		Integer numInstallment = 0;
		if (installmentPlan.getInstallments() != null) {
			for (Installments x : installmentPlan.getInstallments()) {
				x.setId(null);
				x.setNumberInstallment(numInstallment++);
				x.setPaymentDate(x.getPaymentDate());
				x.setValueInstallment(x.getValueInstallment());
				x.setInstallmentPlan(installmentPlan);
				list.add(x);
			}
		}
		repositoryInstallments.save(list);
	}

}
