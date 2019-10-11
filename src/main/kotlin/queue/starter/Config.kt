package queue.starter

import io.micronaut.context.annotation.*
import org.flowable.engine.ProcessEngine
import org.flowable.engine.ProcessEngineConfiguration
import org.flowable.engine.RepositoryService
import org.flowable.engine.RuntimeService
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration
import org.flowable.engine.repository.Deployment
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@ConfigurationProperties("processEngineConfiguration")
class EngineConfig {
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String
    lateinit var driver: String
}

val LOG = LoggerFactory.getLogger(Config::class.java)

@Factory
class Config(val engineConfig: EngineConfig) {

    @Bean
    @Singleton
    fun processEngine(
    ): ProcessEngine {
        println("Config ${engineConfig.url} ${engineConfig.username} ${engineConfig.password} ${engineConfig.driver}")
        // Initialize process engine
        val cfg = StandaloneProcessEngineConfiguration()
                .setJdbcUrl(engineConfig.url)
                .setJdbcUsername(engineConfig.username)
                .setJdbcPassword(engineConfig.password)
                .setJdbcDriver(engineConfig.driver)
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
        return cfg.buildProcessEngine()
    }

    @Bean
    @Singleton
    fun repositoryService(processEngine: ProcessEngine): RepositoryService = processEngine.repositoryService

    @Bean
    @Singleton
    fun runtimeService(processEngine: ProcessEngine): RuntimeService = processEngine.runtimeService

    @Bean
    @Singleton
    fun deployment(repositoryService: RepositoryService): Deployment {
        return repositoryService.createDeployment()
//                .addClasspathResource("canary.bpmn")
//                .addClasspathResource("canary.dmn")
                .deploy()
    }
}
