package com.liangfeng.study.core.web.mvc.valid;


import com.liangfeng.study.core.helper.ValidatorHelper;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Title CommonValidator.java
 * @Description 校验各种字符窜格式验证器，只用于校验String字段
 * @version 1.0
 * @author Liangfeng
 * @date 2018/10/23 13:52
 */
public class CommonValidator implements ConstraintValidator<Validator, String>{

	private String value;
	private CheckType checkType;
	private String message;
	
	@Override
	public void initialize(Validator validator) {
		//把注解validator的属性值传递
		this.value = validator.value();
		this.checkType = validator.checkType();
		this.message = validator.message();
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		//值为空则不校验
		if(StringUtils.isBlank(value)){
			return true;
		}
		if(CheckType.EMAIL.value()==checkType.value()){
			return ValidatorHelper.isEmail(value);
		}else if(CheckType.IDCARD.value()==checkType.value()){
			return ValidatorHelper.isIdCard(value);
		}else if(CheckType.MOBILE.value()==checkType.value()){
			return ValidatorHelper.isMobile(value);
		}else if(CheckType.TELEPHONE.value()==checkType.value()){
			return ValidatorHelper.isTelePhone(value);
		}else if(CheckType.POST.value()==checkType.value()){
			return ValidatorHelper.isPost(value);
		}else if(CheckType.INT.value()==checkType.value()){
			return ValidatorHelper.isInt(value);
		}else if(CheckType.FLOAT.value()==checkType.value()){
			return ValidatorHelper.isFloat(value);
		}else if(CheckType.CHINESE.value()==checkType.value()){
			return ValidatorHelper.isChinese(value);
		}else if(CheckType.DATE.value()==checkType.value()){
			return ValidatorHelper.isDate(value);
		}else if(CheckType.URL.value()==checkType.value()){
			return ValidatorHelper.isURL(value);
		}else if(CheckType.IP.value()==checkType.value()){
			return ValidatorHelper.isIp(value);
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(CheckType.CHINESE.value());
	}
	
	
}
