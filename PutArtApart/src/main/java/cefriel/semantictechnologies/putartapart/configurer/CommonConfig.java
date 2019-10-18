package cefriel.semantictechnologies.putartapart.configurer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cefriel.semantictechnologies.putartapart.business.AbstractBusinessLogic;
import cefriel.semantictechnologies.putartapart.dao.AbstractDao;

@Configuration
@ComponentScan(basePackageClasses = { AbstractDao.class, AbstractBusinessLogic.class })
public abstract class CommonConfig {

}
