package miProyectoNew.miProyectoNew.config;

import com.isb.phoenix.logger.AppLoggerFactory;
import com.isb.phoenix.logger.Logger;
import com.isb.phoenix.web.AbstractWebApplicationInitializer;

/**
 * Clase de inicializacion de la aplicacion web
 */
public class WebAppInitializer extends AbstractWebApplicationInitializer {

    private static final Logger LOGGER = AppLoggerFactory.getLogger(WebAppInitializer.class);

    /**
     * Contructor de una instancia de inicializacion web, 
     * incluimos el logger de arranque
     */
    public WebAppInitializer() {
        LOGGER.info("Starting.... {}", this.getClass().getName());
    }

    /**
     * Devuelve la lista de componentes de aplicacion que deben cargarse en el
     * contexto Root de spring.
     * 
     * @return la lista de componentes de aplicacion que deben cargarse en el
     *         contexto Root de spring.
     */
    @Override
    protected Class<?>[] preRootContextClassConfiguration() {
        return new Class[] {};
    }

    /**
     * Devuelve la lista de componentes de aplicacion que deben cargarse en el
     * contexto Web de spring.
     * 
     * @return la lista de componentes de aplicacion que deben cargarse en el
     *         contexto Web de spring.
     */
    @Override
    protected Class<?>[] preServletConfigClasses() {
        return new Class[] { WebMvcConfiguration.class };
    }
}
