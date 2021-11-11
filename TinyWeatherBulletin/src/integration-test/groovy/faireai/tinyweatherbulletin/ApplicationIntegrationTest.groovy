package faireai.tinyweatherbulletin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class ApplicationIntegrationTest extends Specification {

	@Subject
	@Autowired
	@AutoCleanup
	ApplicationContext context

	def 'Context loaded correctly'() {
		expect: context
	}

}
