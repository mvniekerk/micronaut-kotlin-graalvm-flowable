package queue.starter

import io.micronaut.core.annotation.TypeHint
import io.micronaut.runtime.Micronaut

@TypeHint(
        typeNames = [
            "org.hibernate.dialect.PostgreSQL10Dialect",
            "org.postgresql.Driver"
//            ,
//            "org.flowable.common.engine.impl.cfg.BeansConfigurationHelper",
//            "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl",
//            "com.sun.org.apache.xerces.internal.impl.dv.xs.SchemaDVFactoryImpl",
//            "sun.security.jca.JCAUtil",
//            "sun.security.ec.ECDSASignature",
//            "org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader",
//            "org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration"
        ],
        accessType = [TypeHint.AccessType.ALL_DECLARED_CONSTRUCTORS, TypeHint.AccessType.ALL_PUBLIC, TypeHint.AccessType.ALL_DECLARED_METHODS]
)
object Application {

    @JvmStatic
    fun main(args: Array<String>) {

        Micronaut.build()
                .packages("queue.starter", "queue.starter.controllers")
                .mainClass(Application.javaClass)
                .start()
    }
}
