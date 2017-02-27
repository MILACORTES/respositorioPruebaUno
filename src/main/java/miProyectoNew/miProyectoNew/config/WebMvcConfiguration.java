package miProyectoNew.miProyectoNew.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 * Clase que configura Spring MVC en la aplicacion
 * <p/>
 * TODO: Hay que anadir para escanear los paquetes que necesite la aplicacion
 **/
@Configuration
@EnableWebMvc
@ComponentScan({ "miProyectoNew.miProyectoNew.*.web" })
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    /**
     * Configura el {@link TemplateResolver} para que busque las plantillas de
     * de Thymeleaf en el classpath bajo la carpeta "templates".
     * <p/>
     * TODO: si no se usa Thymeleaf se debe quitar este metodo. Incluir su lugar
     * uno que configure un {@link InternalResourceViewResolver} si se utilizan
     * jsps. Ver el metodo comentado al final de la clase.
     * 
     * @return el template resolver de Thymeleaf
     **/
    @Bean
    public TemplateResolver springTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }

    /**
     * Configura el motor de templates de Thymeleaf.
     * <p/>
     * TODO: si no se usa Thymeleaf se debe quitar este metodo. Incluir su lugar
     * uno que configure un {@link InternalResourceViewResolver} si se utilizan
     * jsps. Ver el metodo comentado al final de la clase.
     * 
     * @return el motor de templates de Thymeleaf
     **/
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(springTemplateResolver());
        return engine;
    }

    /**
     * Configura el view resolver de Thymeleaf.
     * <p/>
     * TODO: si no se usa Thymeleaf se debe quitar este metodo. Incluir su lugar
     * uno que configure un {@link InternalResourceViewResolver} si se utilizan
     * jsps. Ver el metodo comentado al final de la clase.
     * 
     * @return el view resolver de Thymeleaf
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        return resolver;
    }

    /**
     * Configura el gestor de recursos para que mapee las peticiones a las rutas
     * "/static/**" a los ficheros ubicados bajo la carpeta "static" en el
     * classpath.
     * 
     * TODO: quitar este metodo si la aplicacion no va a tener contenido
     * estatico
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");
    }

    /**
     * Configura el gestor de mapeos
     *
     * @return el RequestMappingHandlerMapping configurado para hacer
     *         correspondencia exacta
     */
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
        configurer.setUseTrailingSlashMatch(false);
    }

    /**
     * Configura el messages Resource
     * 
     * @return una instancia del gestor de mensajes de la aplicacion. TODO:
     *         quitar este metodo si no se va a usar un MessageSource
     */
    @Bean(name = "messageSource")
    public MessageSource configureMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        return messageSource;
    }

    // /**
    // * TODO Si se desea usar jsps se debe descomentar esto y comentar la parte
    // de Thymeleaf. Ademas
    // * se deben retocar las dependencias en el pom.xml.
    // *
    // * Configura el {@link ViewResolver} para que busque las plantillas jsp
    // * en el el directorio de jsps.
    // * @return un InternalResourceViewResolver
    // **/
    // @Bean
    // public ViewResolver viewResolver(){
    // InternalResourceViewResolver resolver = new
    // InternalResourceViewResolver();
    // resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
    // resolver.setPrefix("/WEB-INF/jsp/");
    // resolver.setSuffix(".jsp");
    // return resolver;
    // }
}
