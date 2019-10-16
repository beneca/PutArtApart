package semantic.technologies.putartapart.configurer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import semantic.technologies.putartapart.business.AbstractBusinessLogic;
import semantic.technologies.putartapart.dao.AbstractDao;

@Configuration
@ComponentScan(basePackageClasses = { AbstractDao.class, AbstractBusinessLogic.class })
public abstract class CommonConfig {

}
