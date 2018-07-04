package co.projet.filrouge;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.projet.filrouge.repository.FoodsRepository;
import co.projet.filrouge.repository.GroupRepository;
import co.projet.filrouge.z.loader.LoadFoods;

@SpringBootApplication(scanBasePackages= "co.projet.filrouge")
public class FilRougeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilRougeApplication.class, args);
	}
}
