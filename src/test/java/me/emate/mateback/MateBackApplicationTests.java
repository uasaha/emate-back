package me.emate.mateback;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
@Slf4j
class MateBackApplicationTests {

	@Test
	void contextLoads() {
		log.info("contextLoaded");
	}
}
