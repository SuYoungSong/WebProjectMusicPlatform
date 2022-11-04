package webApplication.musicPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan  // 서블릿 자동으로 찾아서 등록 (WebServlet)
@SpringBootApplication
public class MusicPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicPlatformApplication.class, args);
	}

}
