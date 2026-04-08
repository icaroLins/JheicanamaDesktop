package com.br.jheicanama.freelancer.desktop;

import com.br.jheicanama.freelancer.desktop.config.SpringContext;
import com.br.jheicanama.freelancer.desktop.view.login.LoginView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DesktopApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		ApplicationContext context = SpringApplication.run(DesktopApplication.class, args);
		SpringContext.setContext(context);

		new LoginView();
	}

}
