package com.product.microservices.proceedtobuy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	public static Date convertToDate(String date)
	{
		Date resultDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try 
		{
			resultDate = simpleDateFormat.parse(date);
		} catch (Exception e) {}
		
		return resultDate;
	}
}
