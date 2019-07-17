package br.com.sinn.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sinn.domain.Employee;
import br.com.sinn.repository.RepositoryEmployee;
import br.com.sinn.services.exception.FieldMessage;
import br.com.sinn.services.utils.BR;

public class EmployeeInsertValidator implements ConstraintValidator<EmployeeInsert, Employee>{
	
	@Autowired
	private RepositoryEmployee repo;
	
	@Override
	public void initialize(EmployeeInsert constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Employee obj , ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(!BR.isValidSsn(obj.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF not valid"));
		}
		
		Employee aux = repo.findByCpf(obj.getCpf());
		if(aux != null) {
			list.add(new FieldMessage("cpf", "Employee existent!"));
		}
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
	
	
}
