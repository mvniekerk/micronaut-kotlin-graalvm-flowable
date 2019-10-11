package queue.starter.controllers

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import java.util.HashMap
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import org.flowable.engine.ProcessEngine

@Controller("/start")
class QueueStartController(val processEngine: ProcessEngine) {

    @Post(consumes = [MediaType.TEXT_PLAIN])
    fun post(@Body id: String): String {

        val result = HashMap<String, Any>()

        val processInstance = processEngine.runtimeService
                .createProcessInstanceBuilder()
                .processDefinitionId("00001")
                .transientVariable("result", result)
                .variable("personId", id)
                .start()

        return "[Micronaut] new process instance " + processInstance.businessKey + " : " + result
    }

}
