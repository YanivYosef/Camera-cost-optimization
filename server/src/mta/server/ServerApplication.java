package mta.server;
import mta.engine.Engine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class ServerApplication {


	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	@CrossOrigin
	@GetMapping("/home")
	public String hello(@RequestParam(value = "name", defaultValue = "Yaniv") String name) {
		return String.format("Hello %s!", name);
	}
	@CrossOrigin
	@PostMapping("/camerasMap")
	public String camerasMap(@RequestBody String payload){
		System.out.println(payload);

		return new Engine().produceBestCover(payload);

	}


}

