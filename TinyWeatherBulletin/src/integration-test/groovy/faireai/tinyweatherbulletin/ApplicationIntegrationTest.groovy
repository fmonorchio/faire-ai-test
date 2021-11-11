package faireai.tinyweatherbulletin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
@ActiveProfiles('test')
class ApplicationIntegrationTest extends Specification {

	@Subject
	@Autowired
	ApplicationContext context

	def 'Context loaded correctly'() {
		expect: context
	}

}
