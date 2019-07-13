package br.com.sinn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinn.domain.Employee;
import br.com.sinn.domain.Enterprise;
import br.com.sinn.domain.enums.JobRole;

@Service
public class DBService {
	
	@Autowired
	ServiceAdvance serviceAdvance;
	
	@Autowired
	ServiceEmployee serviceEmployee;
	
	@Autowired
	ServiceEnterprise serviceEnterprise;
	
	@Autowired
	ServiceInstallmentPlan serviceInstallmentPlan;
	
	public void instantiateDB() {
		
		Enterprise enterprise = new Enterprise(null, "Teste", "12345678901234", "Teste");		
		serviceEnterprise.insert(enterprise);
		
		Employee employee = new Employee(null, "Teste", "12345678900", JobRole.INTERN, 10000.00, enterprise);
		serviceEmployee.insert(employee);
		
		
	}
	
	
}
