package com.bxtel;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/*
 * plugin --install mobz/elasticsearch-head
 * http://localhost:9200/_plugin/head/
 * 
 * plugin -install royrusso
 * /elasticsearch-HQ
 * http://www.elastichq.org/support_plugin.html
 * http://localhost:9200/_plugin/HQ/
 * 
 */
//@ImportResource(value="classpath:applicationContext-bean.xml")
//TomcatEmbeddedServletContainer
//@SpringBootApplication
@Configuration
@ComponentScan
//@EnableAutoConfiguration
@EnableAutoConfiguration(
		exclude = {
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
		org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration.class
//		//,org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.class
		}
)
@EnableScheduling
@ServletComponentScan
@EnableAspectJAutoProxy
@EnableCaching
//@EnableWebMvc
//@EnableTransactionManagement
//@EnableRedisHttpSession
//@EnableWebMvcSecurity
//@EnableElasticsearchRepositories(basePackages = "com/bxtel/search")
//@EnableJpaRepositories(basePackages = "com/bxtel/dao")
//@EnableJpaRepositories(basePackages = "com/bxtel",includeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Repository.class)})
public class HellobootApplication extends SpringBootServletInitializer{
	//org.springframework.security.config.http.SessionCreationPolicy
	//org.springframework.data.web.config.EnableSpringDataWebSupport
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HellobootApplication.class);
	}
    public static void main(String[] args) {
        SpringApplication.run(HellobootApplication.class, args);//.addApplicationListener(s);;
    }
}


//
//
//17:07:10.223 [main] DEBUG o.s.b.a.l.AutoConfigurationReportLoggingInitializer - 
//
//
//=========================
//AUTO-CONFIGURATION REPORT
//=========================
//
//
//Positive matches:
//-----------------
//
//   AopAutoConfiguration matched
//      - @ConditionalOnClass classes found: org.springframework.context.annotation.EnableAspectJAutoProxy,org.aspectj.lang.annotation.Aspect,org.aspectj.lang.reflect.Advice (OnClassCondition)
//      - matched (OnPropertyCondition)
//
//   AopAutoConfiguration.JdkDynamicAutoProxyConfiguration matched
//      - matched (OnPropertyCondition)
//
//   AuthenticationManagerConfiguration matched
//      - @ConditionalOnBean (types: org.springframework.security.config.annotation.ObjectPostProcessor; SearchStrategy: all) found the following [objectPostProcessor] @ConditionalOnMissingBean (types: org.springframework.security.authentication.AuthenticationManager; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   BootGlobalAuthenticationConfiguration matched
//      - @ConditionalOnClass classes found: org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter (OnClassCondition)
//
//   CacheAutoConfiguration matched
//      - @ConditionalOnClass classes found: org.springframework.cache.CacheManager (OnClassCondition)
//      - @ConditionalOnBean (types: org.springframework.cache.interceptor.CacheAspectSupport; SearchStrategy: all) found the following [cacheInterceptor] @ConditionalOnMissingBean (types: org.springframework.cache.CacheManager,org.springframework.cache.interceptor.CacheResolver; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   DispatcherServletAutoConfiguration matched
//      - @ConditionalOnClass classes found: org.springframework.web.servlet.DispatcherServlet (OnClassCondition)
//      - found web application StandardServletEnvironment (OnWebApplicationCondition)
//
//   DispatcherServletAutoConfiguration.DispatcherServletConfiguration matched
//      - @ConditionalOnClass classes found: javax.servlet.ServletRegistration (OnClassCondition)
//      - no ServletRegistrationBean found (DispatcherServletAutoConfiguration.DefaultDispatcherServletCondition)
//
//   EmbeddedServletContainerAutoConfiguration matched
//      - found web application StandardServletEnvironment (OnWebApplicationCondition)
//
//   EmbeddedServletContainerAutoConfiguration.EmbeddedTomcat matched
//      - @ConditionalOnClass classes found: javax.servlet.Servlet,org.apache.catalina.startup.Tomcat (OnClassCondition)
//      - @ConditionalOnMissingBean (types: org.springframework.boot.context.embedded.EmbeddedServletContainerFactory; SearchStrategy: current) found no beans (OnBeanCondition)
//
//   ErrorMvcAutoConfiguration matched
//      - @ConditionalOnClass classes found: javax.servlet.Servlet,org.springframework.web.servlet.DispatcherServlet (OnClassCondition)
//      - found web application StandardServletEnvironment (OnWebApplicationCondition)
//
//   ErrorMvcAutoConfiguration#basicErrorController matched
//      - @ConditionalOnMissingBean (types: org.springframework.boot.autoconfigure.web.ErrorController; SearchStrategy: current) found no beans (OnBeanCondition)
//
//   ErrorMvcAutoConfiguration#errorAttributes matched
//      - @ConditionalOnMissingBean (types: org.springframework.boot.autoconfigure.web.ErrorAttributes; SearchStrategy: current) found no beans (OnBeanCondition)
//
//   ErrorMvcAutoConfiguration.WhitelabelErrorViewConfiguration matched
//      - No error template view detected (ErrorMvcAutoConfiguration.ErrorTemplateMissingCondition)
//      - matched (OnPropertyCondition)
//
//   ErrorMvcAutoConfiguration.WhitelabelErrorViewConfiguration#beanNameViewResolver matched
//      - @ConditionalOnMissingBean (types: org.springframework.web.servlet.view.BeanNameViewResolver; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   ErrorMvcAutoConfiguration.WhitelabelErrorViewConfiguration#defaultErrorView matched
//      - @ConditionalOnMissingBean (names: error; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   HttpEncodingAutoConfiguration matched
//      - @ConditionalOnClass classes found: org.springframework.web.filter.CharacterEncodingFilter (OnClassCondition)
//      - matched (OnPropertyCondition)
//
//   HttpEncodingAutoConfiguration#characterEncodingFilter matched
//      - @ConditionalOnMissingBean (types: org.springframework.web.filter.CharacterEncodingFilter; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   HttpMessageConvertersAutoConfiguration matched
//      - @ConditionalOnClass classes found: org.springframework.http.converter.HttpMessageConverter (OnClassCondition)
//
//   HttpMessageConvertersAutoConfiguration#messageConverters matched
//      - @ConditionalOnMissingBean (types: org.springframework.boot.autoconfigure.web.HttpMessageConverters; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   HttpMessageConvertersAutoConfiguration.StringHttpMessageConverterConfiguration matched
//      - @ConditionalOnClass classes found: org.springframework.http.converter.StringHttpMessageConverter (OnClassCondition)
//
//   HttpMessageConvertersAutoConfiguration.StringHttpMessageConverterConfiguration#stringHttpMessageConverter matched
//      - @ConditionalOnMissingBean (types: org.springframework.http.converter.StringHttpMessageConverter; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   JacksonAutoConfiguration matched
//      - @ConditionalOnClass classes found: com.fasterxml.jackson.databind.ObjectMapper (OnClassCondition)
//
//   JacksonAutoConfiguration.JacksonObjectMapperBuilderConfiguration matched
//      - @ConditionalOnClass classes found: com.fasterxml.jackson.databind.ObjectMapper,org.springframework.http.converter.json.Jackson2ObjectMapperBuilder (OnClassCondition)
//
//   JacksonAutoConfiguration.JacksonObjectMapperBuilderConfiguration#jacksonObjectMapperBuilder matched
//      - @ConditionalOnMissingBean (types: org.springframework.http.converter.json.Jackson2ObjectMapperBuilder; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   JacksonAutoConfiguration.JacksonObjectMapperConfiguration matched
//      - @ConditionalOnClass classes found: com.fasterxml.jackson.databind.ObjectMapper,org.springframework.http.converter.json.Jackson2ObjectMapperBuilder (OnClassCondition)
//
//   JacksonAutoConfiguration.JacksonObjectMapperConfiguration#jacksonObjectMapper matched
//      - @ConditionalOnMissingBean (types: com.fasterxml.jackson.databind.ObjectMapper; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   JacksonHttpMessageConvertersConfiguration.MappingJackson2HttpMessageConverterConfiguration matched
//      - @ConditionalOnClass classes found: com.fasterxml.jackson.databind.ObjectMapper (OnClassCondition)
//      - matched (OnPropertyCondition)
//      - @ConditionalOnBean (types: com.fasterxml.jackson.databind.ObjectMapper; SearchStrategy: all) found the following [jacksonObjectMapper] (OnBeanCondition)
//
//   JacksonHttpMessageConvertersConfiguration.MappingJackson2HttpMessageConverterConfiguration#mappingJackson2HttpMessageConverter matched
//      - @ConditionalOnMissingBean (types: org.springframework.http.converter.json.MappingJackson2HttpMessageConverter; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   JmxAutoConfiguration matched
//      - @ConditionalOnClass classes found: org.springframework.jmx.export.MBeanExporter (OnClassCondition)
//      - matched (OnPropertyCondition)
//
//   JmxAutoConfiguration#mbeanExporter matched
//      - @ConditionalOnMissingBean (types: org.springframework.jmx.export.MBeanExporter; SearchStrategy: current) found no beans (OnBeanCondition)
//
//   JmxAutoConfiguration#mbeanServer matched
//      - @ConditionalOnMissingBean (types: javax.management.MBeanServer; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   JmxAutoConfiguration#objectNamingStrategy matched
//      - @ConditionalOnMissingBean (types: org.springframework.jmx.export.naming.ObjectNamingStrategy; SearchStrategy: current) found no beans (OnBeanCondition)
//
//   MultipartAutoConfiguration matched
//      - @ConditionalOnClass classes found: javax.servlet.Servlet,org.springframework.web.multipart.support.StandardServletMultipartResolver,javax.servlet.MultipartConfigElement (OnClassCondition)
//      - matched (OnPropertyCondition)
//
//   MultipartAutoConfiguration#multipartConfigElement matched
//      - @ConditionalOnMissingBean (types: javax.servlet.MultipartConfigElement; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   MultipartAutoConfiguration#multipartResolver matched
//      - @ConditionalOnMissingBean (types: org.springframework.web.multipart.MultipartResolver; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   PropertyPlaceholderAutoConfiguration#propertySourcesPlaceholderConfigurer matched
//      - @ConditionalOnMissingBean (types: org.springframework.context.support.PropertySourcesPlaceholderConfigurer; SearchStrategy: current) found no beans (OnBeanCondition)
//
//   SecurityAutoConfiguration matched
//      - @ConditionalOnClass classes found: org.springframework.security.authentication.AuthenticationManager,org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter (OnClassCondition)
//
//   SecurityAutoConfiguration#authenticationEventPublisher matched
//      - @ConditionalOnMissingBean (types: org.springframework.security.authentication.AuthenticationEventPublisher; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   SecurityAutoConfiguration#securityProperties matched
//      - @ConditionalOnMissingBean (types: org.springframework.boot.autoconfigure.security.SecurityProperties; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   SecurityFilterAutoConfiguration matched
//      - @ConditionalOnClass classes found: org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer (OnClassCondition)
//      - found web application StandardServletEnvironment (OnWebApplicationCondition)
//
//   SecurityFilterAutoConfiguration#securityFilterChainRegistration matched
//      - @ConditionalOnBean (names: springSecurityFilterChain; SearchStrategy: all) found the following [springSecurityFilterChain] (OnBeanCondition)
//
//   ServerPropertiesAutoConfiguration matched
//      - found web application StandardServletEnvironment (OnWebApplicationCondition)
//
//   ServerPropertiesAutoConfiguration#serverProperties matched
//      - @ConditionalOnMissingBean (types: org.springframework.boot.autoconfigure.web.ServerProperties; SearchStrategy: current) found no beans (OnBeanCondition)
//
//   SimpleCacheConfiguration matched
//      - Automatic cache type (CacheCondition)
//      - @ConditionalOnMissingBean (types: org.springframework.cache.CacheManager; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   SpringBootWebSecurityConfiguration matched
//      - @ConditionalOnClass classes found: org.springframework.security.config.annotation.web.configuration.EnableWebSecurity,org.springframework.security.web.AuthenticationEntryPoint (OnClassCondition)
//      - found web application StandardServletEnvironment (OnWebApplicationCondition)
//      - @ConditionalOnMissingBean (types: org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   SpringBootWebSecurityConfiguration#ignoredPathsWebSecurityConfigurerAdapter matched
//      - @ConditionalOnMissingBean (types: org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration$IgnoredPathsWebSecurityConfigurerAdapter; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   SpringBootWebSecurityConfiguration.ApplicationNoWebSecurityConfigurerAdapter matched
//      - matched (OnPropertyCondition)
//
//   WebSocketAutoConfiguration matched
//      - @ConditionalOnClass classes found: javax.servlet.Servlet,javax.websocket.server.ServerContainer (OnClassCondition)
//      - found web application StandardServletEnvironment (OnWebApplicationCondition)
//
//   WebSocketAutoConfiguration.TomcatWebSocketConfiguration matched
//      - @ConditionalOnClass classes found: org.apache.catalina.startup.Tomcat,org.apache.tomcat.websocket.server.WsSci (OnClassCondition)
//
//   WebSocketAutoConfiguration.TomcatWebSocketConfiguration#websocketContainerCustomizer matched
//      - @ConditionalOnMissingBean (names: websocketContainerCustomizer; SearchStrategy: all) found no beans (OnBeanCondition)
//
//
//Negative matches:
//-----------------
//
//   ActiveMQAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: javax.jms.ConnectionFactory,org.apache.activemq.ActiveMQConnectionFactory (OnClassCondition)
//
//   AopAutoConfiguration.CglibAutoProxyConfiguration did not match
//      - @ConditionalOnProperty missing required properties spring.aop.proxy-target-class  (OnPropertyCondition)
//
//   ArtemisAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: javax.jms.ConnectionFactory,org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory (OnClassCondition)
//
//   BatchAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.batch.core.launch.JobLauncher,org.springframework.jdbc.core.JdbcOperations (OnClassCondition)
//
//   CacheAutoConfiguration.CacheManagerJpaDependencyConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean (OnClassCondition)
//
//   CassandraAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.datastax.driver.core.Cluster (OnClassCondition)
//
//   CassandraDataAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.datastax.driver.core.Cluster,org.springframework.data.cassandra.core.CassandraAdminOperations (OnClassCondition)
//
//   CassandraRepositoriesAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.datastax.driver.core.Session,org.springframework.data.cassandra.repository.CassandraRepository (OnClassCondition)
//
//   CloudAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.cloud.config.java.CloudScanConfiguration (OnClassCondition)
//
//   DataSourceAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType (OnClassCondition)
//
//   DataSourceTransactionManagerAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.jdbc.core.JdbcTemplate,org.springframework.transaction.PlatformTransactionManager (OnClassCondition)
//
//   DeviceDelegatingViewResolverAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver (OnClassCondition)
//
//   DeviceResolverAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.mobile.device.DeviceResolverHandlerInterceptor,org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver (OnClassCondition)
//
//   DispatcherServletAutoConfiguration.DispatcherServletConfiguration#multipartResolver did not match
//      - @ConditionalOnBean (types: org.springframework.web.multipart.MultipartResolver; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   EhCacheCacheConfiguration did not match
//      - required @ConditionalOnClass classes not found: net.sf.ehcache.Cache (OnClassCondition)
//
//   ElasticsearchAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.elasticsearch.client.Client,org.springframework.data.elasticsearch.client.TransportClientFactoryBean,org.springframework.data.elasticsearch.client.NodeClientFactoryBean (OnClassCondition)
//
//   ElasticsearchDataAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.elasticsearch.client.Client,org.springframework.data.elasticsearch.core.ElasticsearchTemplate (OnClassCondition)
//
//   ElasticsearchRepositoriesAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.elasticsearch.client.Client,org.springframework.data.elasticsearch.repository.ElasticsearchRepository (OnClassCondition)
//
//   EmbeddedMongoAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.mongodb.Mongo,de.flapdoodle.embed.mongo.MongodStarter (OnClassCondition)
//
//   EmbeddedServletContainerAutoConfiguration.EmbeddedJetty did not match
//      - required @ConditionalOnClass classes not found: org.eclipse.jetty.server.Server,org.eclipse.jetty.util.Loader,org.eclipse.jetty.webapp.WebAppContext (OnClassCondition)
//
//   EmbeddedServletContainerAutoConfiguration.EmbeddedUndertow did not match
//      - required @ConditionalOnClass classes not found: io.undertow.Undertow,org.xnio.SslClientAuthMode (OnClassCondition)
//
//   FacebookAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.social.config.annotation.SocialConfigurerAdapter,org.springframework.social.facebook.connect.FacebookConnectionFactory (OnClassCondition)
//
//   FallbackWebSecurityAutoConfiguration did not match
//      - @ConditionalOnClass classes found: org.springframework.security.config.annotation.web.configuration.EnableWebSecurity (OnClassCondition)
//      - found web application StandardServletEnvironment (OnWebApplicationCondition)
//      - matched (OnPropertyCondition)
//      - @ConditionalOnMissingBean (types: org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration; SearchStrategy: all) found the following [org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration] (OnBeanCondition)
//
//   FlywayAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.flywaydb.core.Flyway (OnClassCondition)
//
//   FreeMarkerAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: freemarker.template.Configuration (OnClassCondition)
//
//   GenericCacheConfiguration did not match
//      - Automatic cache type (CacheCondition)
//      - @ConditionalOnBean (types: org.springframework.cache.Cache; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   GroovyTemplateAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: groovy.text.markup.MarkupTemplateEngine (OnClassCondition)
//
//   GsonAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.google.gson.Gson (OnClassCondition)
//
//   GsonHttpMessageConvertersConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.google.gson.Gson (OnClassCondition)
//
//   GuavaCacheConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.google.common.cache.CacheBuilder (OnClassCondition)
//
//   H2ConsoleAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.h2.server.web.WebServlet (OnClassCondition)
//
//   HazelcastAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.hazelcast.core.HazelcastInstance (OnClassCondition)
//
//   HazelcastCacheConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.hazelcast.core.HazelcastInstance,com.hazelcast.spring.cache.HazelcastCacheManager (OnClassCondition)
//
//   HazelcastJpaDependencyAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.hazelcast.core.HazelcastInstance,org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean (OnClassCondition)
//
//   HibernateJpaAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean,org.springframework.transaction.annotation.EnableTransactionManagement,javax.persistence.EntityManager (OnClassCondition)
//
//   HornetQAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: javax.jms.ConnectionFactory,org.hornetq.api.jms.HornetQJMSClient (OnClassCondition)
//
//   HypermediaAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.hateoas.Resource,org.springframework.plugin.core.Plugin (OnClassCondition)
//
//   InfinispanCacheConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.infinispan.spring.provider.SpringEmbeddedCacheManager (OnClassCondition)
//
//   IntegrationAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.integration.config.EnableIntegration (OnClassCondition)
//
//   JCacheCacheConfiguration did not match
//      - required @ConditionalOnClass classes not found: javax.cache.Caching (OnClassCondition)
//
//   JacksonAutoConfiguration.JodaDateTimeJacksonConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.joda.time.DateTime,com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer,com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat (OnClassCondition)
//
//   JacksonAutoConfiguration.ParameterNamesModuleConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.fasterxml.jackson.module.paramnames.ParameterNamesModule (OnClassCondition)
//
//   JacksonHttpMessageConvertersConfiguration.MappingJackson2XmlHttpMessageConverterConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.fasterxml.jackson.dataformat.xml.XmlMapper (OnClassCondition)
//
//   JerseyAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.glassfish.jersey.server.spring.SpringComponentProvider (OnClassCondition)
//
//   JmsAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.jms.core.JmsTemplate (OnClassCondition)
//
//   JndiConnectionFactoryAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.jms.core.JmsTemplate (OnClassCondition)
//
//   JndiDataSourceAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType (OnClassCondition)
//
//   JooqAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.jooq.DSLContext (OnClassCondition)
//
//   JpaRepositoriesAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.data.jpa.repository.JpaRepository (OnClassCondition)
//
//   JtaAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: javax.transaction.Transaction (OnClassCondition)
//
//   LinkedInAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.social.config.annotation.SocialConfigurerAdapter,org.springframework.social.linkedin.connect.LinkedInConnectionFactory (OnClassCondition)
//
//   LiquibaseAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: liquibase.integration.spring.SpringLiquibase (OnClassCondition)
//
//   MailSenderAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: javax.mail.internet.MimeMessage (OnClassCondition)
//
//   MailSenderValidatorAutoConfiguration did not match
//      - @ConditionalOnProperty missing required properties spring.mail.test-connection  (OnPropertyCondition)
//
//   MessageSourceAutoConfiguration did not match
//      - No bundle found for spring.messages.basename: messages (MessageSourceAutoConfiguration.ResourceBundleCondition)
//
//   MongoAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.mongodb.MongoClient (OnClassCondition)
//
//   MongoDataAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.mongodb.Mongo,org.springframework.data.mongodb.core.MongoTemplate (OnClassCondition)
//
//   MongoRepositoriesAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.mongodb.Mongo,org.springframework.data.mongodb.repository.MongoRepository (OnClassCondition)
//
//   MustacheAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.samskivert.mustache.Mustache (OnClassCondition)
//
//   NoOpCacheConfiguration did not match
//      - Automatic cache type (CacheCondition)
//      - @ConditionalOnMissingBean (types: org.springframework.cache.CacheManager; SearchStrategy: all) found the following [cacheManager] (OnBeanCondition)
//
//   OAuth2AutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.security.oauth2.common.OAuth2AccessToken (OnClassCondition)
//
//   PersistenceExceptionTranslationAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor (OnClassCondition)
//
//   RabbitAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.amqp.rabbit.core.RabbitTemplate,com.rabbitmq.client.Channel (OnClassCondition)
//
//   ReactorAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: reactor.spring.context.config.EnableReactor,reactor.Environment (OnClassCondition)
//
//   RedisAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.data.redis.connection.jedis.JedisConnection,org.springframework.data.redis.core.RedisOperations,redis.clients.jedis.Jedis (OnClassCondition)
//
//   RedisCacheConfiguration did not match
//      - Automatic cache type (CacheCondition)
//      - @ConditionalOnBean (types: org.springframework.data.redis.core.RedisTemplate; SearchStrategy: all) found no beans (OnBeanCondition)
//
//   RepositoryRestMvcAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration (OnClassCondition)
//
//   SecurityDataConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.security.data.repository.query.SecurityEvaluationContextExtension,org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport (OnClassCondition)
//
//   SecurityFilterAutoConfiguration#securityProperties did not match
//      - @ConditionalOnMissingBean (types: org.springframework.boot.autoconfigure.security.SecurityProperties; SearchStrategy: all) found the following [securityProperties] (OnBeanCondition)
//
//   SendGridAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: com.sendgrid.SendGrid (OnClassCondition)
//
//   SessionAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.session.Session (OnClassCondition)
//
//   SitePreferenceAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor,org.springframework.mobile.device.site.SitePreferenceHandlerMethodArgumentResolver (OnClassCondition)
//
//   SocialWebAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.social.connect.web.ConnectController,org.springframework.social.config.annotation.SocialConfigurerAdapter (OnClassCondition)
//
//   SolrAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.apache.solr.client.solrj.impl.HttpSolrServer,org.apache.solr.client.solrj.impl.CloudSolrServer,org.apache.solr.common.cloud.HashPartitioner (OnClassCondition)
//
//   SolrRepositoriesAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.apache.solr.client.solrj.SolrServer,org.springframework.data.solr.repository.SolrRepository (OnClassCondition)
//
//   SpringApplicationAdminJmxAutoConfiguration did not match
//      - @ConditionalOnProperty missing required properties spring.application.admin.enabled  (OnPropertyCondition)
//
//   SpringBootWebSecurityConfiguration.ApplicationWebSecurityConfigurerAdapter did not match
//      - @ConditionalOnProperty expected '!false' for properties security.basic.enabled (OnPropertyCondition)
//
//   SpringDataWebAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.data.web.PageableHandlerMethodArgumentResolver (OnClassCondition)
//
//   ThymeleafAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.thymeleaf.spring4.SpringTemplateEngine (OnClassCondition)
//
//   TransactionAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.transaction.support.TransactionTemplate,org.springframework.transaction.PlatformTransactionManager (OnClassCondition)
//
//   TwitterAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.social.config.annotation.SocialConfigurerAdapter,org.springframework.social.twitter.connect.TwitterConnectionFactory (OnClassCondition)
//
//   VelocityAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.apache.velocity.app.VelocityEngine (OnClassCondition)
//
//   WebMvcAutoConfiguration did not match
//      - @ConditionalOnClass classes found: javax.servlet.Servlet,org.springframework.web.servlet.DispatcherServlet,org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter (OnClassCondition)
//      - found web application StandardServletEnvironment (OnWebApplicationCondition)
//      - @ConditionalOnMissingBean (types: org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport; SearchStrategy: all) found the following [org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration] (OnBeanCondition)
//
//   WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.FaviconConfiguration did not match
//      - matched (OnPropertyCondition)
//      - Ancestor 'org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration' did not match (ConditionEvaluationReport.AncestorsMatchedCondition)
//
//   WebSocketAutoConfiguration.JettyWebSocketConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer (OnClassCondition)
//
//   WebSocketAutoConfiguration.UndertowWebSocketConfiguration did not match
//      - required @ConditionalOnClass classes not found: io.undertow.websockets.jsr.Bootstrap (OnClassCondition)
//
//   WebSocketMessagingAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer (OnClassCondition)
//
//   XADataSourceAutoConfiguration did not match
//      - required @ConditionalOnClass classes not found: javax.transaction.TransactionManager,org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType (OnClassCondition)
//
//
//Exclusions:
//-----------
//
//    None
//
//
//Unconditional classes:
//----------------------
//
//   org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration
//
//   org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration
//
//
