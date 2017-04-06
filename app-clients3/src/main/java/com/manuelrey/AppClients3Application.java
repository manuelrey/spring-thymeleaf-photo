package com.manuelrey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.manuelrey.dao.ClientRepository;
import com.manuelrey.entities.Client;

@SpringBootApplication
public class AppClients3Application {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(AppClients3Application.class, args);
		ClientRepository clientRepository= ctx.getBean(ClientRepository.class);
		
		clientRepository.save(new Client("manuel", "rey", "email", "photo.jpa"));
		
		Page<Client> cli=clientRepository.findAll(new PageRequest(0, 5));
		cli.forEach(c->System.out.println(c.getFirst_name()));
		
	}
}
