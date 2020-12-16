package blokaly.cloudaly.clouda.graphql

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Query
import blokaly.cloudaly.clouda.LoggerDelegate
import kotlinx.coroutines.delay
import org.springframework.stereotype.Component

@Component
class ConferenceQuery : Query{
    private val logger by LoggerDelegate()

    fun conference(): Conference {
        logger.info("Querying conference...")
        return Conference(name = "GOTO Chicago", location = "virtual")
    }

    fun people(nameStartsWith: String?): List<People>{
        logger.info("Querying people...")
        return listOf(
            Speaker("Dariusz", listOf("Bootiful GraphQL")),
            Attendee("Jane", TicketType.CONFERENCE)
        ).filter { it.name.startsWith(nameStartsWith ?: "") }
    }

    fun schedule(): ScheduleDetails {
        logger.info("Querying schedule...")
        return ScheduleDetails()
    }
}

class ScheduleDetails {

    fun greeting(context: CustomContext) = if (context.language.equals("pl-pl", true)) {
            "Witajcie"
        } else {
            "Welcome to GOTO Chicago!"
        }

    suspend fun talks(): List<String> {
        delay(2000)
        return listOf("Bootiful GraphQL", "GraphQL is awesome", "Intro to GraphQL")
    }
}

interface People {
    val name: String
}

data class Speaker(override val name: String, val talks: List<String>) : People

data class Attendee(override val name: String, val ticketType: TicketType) : People

enum class TicketType {
    CONFERENCE,
    WORKSHOP,
    FULL
}

data class Conference(
        @GraphQLDescription("my super **awesome** `conference` name")
        val name: String,
        @Deprecated("not needed anymore")
        val location: String?)