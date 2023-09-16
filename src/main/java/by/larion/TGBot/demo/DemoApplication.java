package by.larion.TGBot.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) throws TelegramApiException, IOException {
		var tg = new TelegramBotsApi(DefaultBotSession.class);
		var config = new Properties();
		try (var app = DemoApplication.class.getClassLoader().getResourceAsStream("application.properties")) {
			config.load(app);
		}
		var actions = Map.of(
				"/start", new InfoAction(
						List.of(
								"/start - Команды бота",
								"/echo - Ввод данных для команды",
								"/new - Регистрация пользователя",
								"/go - Обратиться к онлайн-участковому")
				),
				"/echo", new EchoAction("/echo"),
				"/new", new RegAction("/new"),
				"/go", new AppealAction("/go")
		);
		tg.registerBot(new BotMenu(actions, config.getProperty("tg.username"), config.getProperty("tg.token")));
	}
}
