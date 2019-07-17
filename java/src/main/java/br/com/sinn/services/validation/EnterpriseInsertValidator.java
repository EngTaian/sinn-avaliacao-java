package br.com.sinn.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sinn.domain.Enterprise;
import br.com.sinn.repository.RepositoryEnterprise;
import br.com.sinn.services.exception.FieldMessage;
import br.com.sinn.services.utils.BR;

public class EnterpriseInsertValidator implements ConstraintValidator<EnterpriseInsert, Enterprise>{
	
	@Autowired
	private RepositoryEnterprise repo;
	
	@Override
	public void initialize(EnterpriseInsert constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Enterprise obj , ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(!BR.isValidTin(obj.getCnpj())) {
			list.add(new FieldMessage("cnpj_empresa", "CNPJ not valid"));
		}
		
		Enterprise aux = repo.findByCnpj(obj.getCnpj());
		if(aux != null) {
			list.add(new FieldMessage("cnpj_empresa", "CNPJ exists!"));
		}
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
	
	
}
