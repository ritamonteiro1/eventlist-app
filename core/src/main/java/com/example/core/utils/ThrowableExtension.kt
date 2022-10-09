import com.example.core.R
import com.example.core.model.NetworkErrorException
import com.example.core.model.ServerErrorException

fun Throwable.getErrorMessage(): Int {
    if (this is NetworkErrorException) {
        return R.string.network_error_exception
    }
    if (this is ServerErrorException) {
        return R.string.server_error_exception
    }
    return R.string.generic_error_exception
}