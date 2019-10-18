package cefriel.semantictechnologies.putartapart.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class TestConfigurer extends CommonConfig {
	
	@Autowired
	protected Environment env;
	
}