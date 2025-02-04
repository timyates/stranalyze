package wood.mike.clients

import io.micronaut.core.annotation.Nullable
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import wood.mike.domain.Activity
import wood.mike.domain.Athlete

import javax.validation.constraints.NotNull

import static io.micronaut.http.HttpHeaders.ACCEPT

@Client('http://localhost:10050/stravaFetch')
@Header(name = ACCEPT, value = MediaType.APPLICATION_JSON_STREAM )
interface StravaFetchClient {

    @Get('/athlete')
    Mono<Athlete> fetchAthlete()

    @Get('/activity/{activityId}')
    Flux<Activity> fetchActivity(@PathVariable String activityId)

    @Get('/activities')
    Flux<Activity> fetchActivities( @QueryValue @Nullable Integer page, @QueryValue(value = 'per_page') @Nullable Integer perPage )

    @Get( uri = "/activitiesAfter", produces = MediaType.APPLICATION_JSON_STREAM )
    Flux<Activity> activitiesAfter( @QueryValue @NotNull  Long after )
}
