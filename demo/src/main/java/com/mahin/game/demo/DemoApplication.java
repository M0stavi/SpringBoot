package com.mahin.game.demo;

import com.mahin.game.demo.game.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(DemoApplication.class, args);
//		MarioGame marioGame = new MarioGame();
//		SuperContraGame superContraGame = new SuperContraGame();
//		PacmanGame pacmanGame = new PacmanGame();
//		GamingConsole gamingConsole = new MarioGame();
//		GameRunner runner = new GameRunner(gamingConsole);
		GameRunner runner = context.getBean(GameRunner.class);
		runner.run();
	}

}
