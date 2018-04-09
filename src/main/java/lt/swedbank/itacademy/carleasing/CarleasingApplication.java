package lt.swedbank.itacademy.carleasing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarleasingApplication{

//	@Autowired
//	private EmailService emailService;
//
//	@Override
//	public void run(ApplicationArguments applicationArguments) throws Exception {
//		Mail mail = new Mail();
//		mail.setFrom("majestic.car.leasing@gmail.com");
//		mail.setTo("jarektkaciuk@gmail.com");
//		mail.setSubject("Sending Simple Email with JavaMailSender Example");
//		mail.setContent("This tutorial demonstrates how to send a simple email using Spring Framework.");
//
//		emailService.sendSimpleMessage(mail);
//	}

	public static void main(String[] args) {
		SpringApplication.run(CarleasingApplication.class, args);

	}


}
