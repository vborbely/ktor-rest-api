import com.anysolutions.ktor_rest_api.repositories.UsersRepository
import com.anysolutions.ktor_rest_api.services.UsersServices
import org.koin.dsl.module


val usersServicesModule = module {
    single { UsersServices() }
    single { UsersRepository() }
}
