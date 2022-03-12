package com.product.microservices.ecommerce.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	public static Date convertToDate(String date)
	{
		Date resultDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try 
		{
			resultDate = simpleDateFormat.parse(date);
		} catch (Exception e) {}
		
		return resultDate;
	}
}
