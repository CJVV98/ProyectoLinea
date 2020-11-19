package com.udec.schedulle;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Automatico {
	
	@Scheduled(fixedRate = 205000)
	public void ejecutar() {
		System.out.println("Hola mundo");
	}
}
