package otus.homework.coroutines


sealed class Result {
    data class Success(val catData: Cat) : Result()
    data class Error(val throwable: Throwable) : Result()
}