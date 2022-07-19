package br.com.luisfelipeas5.watchlist.data.datasources.remote

import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie
import kotlinx.coroutines.delay
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    suspend fun getWatchlist(pageIndex: Int): List<Movie> {
        delay(3000)
        return when (pageIndex) {
            0 -> getFirstPageMovies()
            1 -> getSecondPageMovies()
            2 -> getThirdPageMovies()
            else -> listOf()
        }
    }

    private fun getFirstPageMovies(): List<Movie> {
        return mutableListOf(
            Movie(
                title = "A Chegada",
                whoRecommended = "Vitor Capobianco",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/hNCqkXbWd40eftqSdjq8TmV7Mqr.jpg",
            ),
            Movie(
                title = "Corra!",
                whoRecommended = "Beatriz Rocha",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/vZ7EVk7FaNEWYqlVWbFLHP8Z0LU.jpg",
            ),
            Movie(
                title = "Drive",
                whoRecommended = "Alan Kenji",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/9ly9pxGwiB6g5lIZXou2HKXl7ua.jpg",
            ),
            Movie(
                title = "Creed III",
                whoRecommended = "Galdino",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/hZqx2JcZVjHSY2lMEMDC0XlObiw.jpg",
            ),
            Movie(
                title = "Hereditário",
                whoRecommended = "Gabriel Lorandi",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/4DUoPZOHdPuROP4nyEIsPaMIiQl.jpg",
            ),
            Movie(
                title = "Garota Exemplar",
                whoRecommended = "Beatriz",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/x9ezMgOtDPqHatHDvxEG0ILb6Ie.jpg",
            ),
            Movie(
                title = "Zodíaco",
                whoRecommended = "Não lemrbo",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/otQHyCjlkcFfvUDPMdJdBMN7Rg5.jpg",
            ),
            Movie(
                title = "Intocáveis",
                whoRecommended = "Ivanete de Almeida",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/iNAMRPUEEsNIAiZL0RmVmIGjiv1.jpg",
            ),
            Movie(
                title = "A Prova de Morte",
                whoRecommended = "André Castrofo",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/vtu6H4NWnQVqEp3aanUq3hNeeot.jpg",
            ),
            Movie(
                title = "Cães de Aluguel",
                whoRecommended = "André Castrofo",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/g6R1OT7ETBLGLeUJOE0pOiAFHcI.jpg",
            ),
            Movie(
                title = "Kill Bill",
                whoRecommended = "André Castrofo",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/lVy5Zqcty2NfemqKYbVJfdg44rK.jpg",
            ),
            Movie(
                title = "Django",
                whoRecommended = "André Castrofo",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/6MCVqErhPC0p6RkzZsLuIb6LZ1L.jpg",
            ),
            Movie(
                title = "Jackie Brown",
                whoRecommended = "André Castrofo",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/u04CYmXVpppyPjl7JzThYmV8Kwg.jpg",
            ),
            Movie(
                title = "Pulp Fiction",
                whoRecommended = "André Castrofo",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/suaEOtk1N1sgg2MTM7oZd2cfVp3.jpg",
            ),
        ).apply {
            addAll(getThirdPageMovies())
        }
    }

    private fun getSecondPageMovies(): List<Movie> {
        return listOf(
            Movie(
                title = "O Protetor",
                whoRecommended = "Luis Carlos",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/hPZo3k9iDpAGajh7IDuyquThQd3.jpg",
            ),
            Movie(
                title = "Antes do Amanhecer",
                whoRecommended = "Gabriel Lorandi",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/wih0esrlvzqX5lU8BMvHeJMkhT7.jpg",
            ),
            Movie(
                title = "O Esquadrão Suicida",
                whoRecommended = "Antonio",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/jlGmlFOcfo8n5tURmhC7YVd4Iyy.jpg",
            ),
            Movie(
                title = "Seven - 7 Pecados Mortais",
                whoRecommended = "Maria do Carmo",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/dYjZ27hDw2QFaEIfzbNGwW0IkV9.jpg",
            ),
        )
    }

    private fun getThirdPageMovies(): List<Movie> {
        return listOf(
            Movie(
                title = "O Que Ficou para Trás",
                whoRecommended = "Otavio Uga",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/6aVB2B2GDc4EuNinHgoBgtkuHQz.jpg",
            ),
            Movie(
                title = "X: A Marca da Morte",
                whoRecommended = "Fãs da A24",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/2oXQpm0wfOkIL0jBJABbL5AfMs6.jpg",
            ),
            Movie(
                title = "Twin Peaks (Os Últimos Dias de Laura Palmer)",
                whoRecommended = "Gabriel Lorandi",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/g5G19q0xgkzWEvfcGo1KcL8nQOk.jpg",
            ),
            Movie(
                title = "O Que Fazemos na Sombra",
                whoRecommended = "Lucas Duarte",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/k4AVaOboquMcd3zbFSSgCEbzabz.jpg",
            ),
            Movie(
                title = "Crimes do Futuro",
                whoRecommended = "Dalenogare",
                watched = false,
                cover = "https://image.tmdb.org/t/p/w780/crzp8X5oRstwcEENdpvJsLUfqRS.jpg",
            ),
        )
    }

}