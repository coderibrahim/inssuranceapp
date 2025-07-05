package com.iakbas.nevitechinsuranceapp;

import com.iakbas.nevitechinsuranceapp.model.Campaign;
import com.iakbas.nevitechinsuranceapp.model.Category;
import com.iakbas.nevitechinsuranceapp.service.CampaignService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NevitechInsuranceAppApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(NevitechInsuranceAppApplication.class, args);
	}
}

