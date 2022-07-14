package br.com.luisfelipeas5.watchlist.data.datasources.remote

import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie
import kotlinx.coroutines.delay
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    suspend fun getWatchlist(pageIndex: Int): List<Movie> {
        delay(2000)
        return when (pageIndex) {
            0 -> getFirstPageMovies()
            1 -> getSecondPageMovies()
            2 -> getThirdPageMovies()
            else -> listOf()
        }
    }

    private fun getFirstPageMovies(): List<Movie> {
        return listOf(
            Movie(
                title = "A Chegada",
                whoRecommended = "Vitor Capobianco",
                watched = false,
                cover = "/hNCqkXbWd40eftqSdjq8TmV7Mqr.jpg",
            ),
            Movie(
                title = "Corra!",
                whoRecommended = "Beatriz Rocha",
                watched = false,
                cover = "/vZ7EVk7FaNEWYqlVWbFLHP8Z0LU.jpg",
            ),
            Movie(
                title = "Drive",
                whoRecommended = "Alan Kenji",
                watched = false,
                cover = "/9ly9pxGwiB6g5lIZXou2HKXl7ua.jpg",
            ),
            Movie(
                title = "Creed III",
                whoRecommended = "Galdino",
                watched = false,
                cover = "/hZqx2JcZVjHSY2lMEMDC0XlObiw.jpg",
            ),
            Movie(
                title = "Hereditário",
                whoRecommended = "Gabriel Lorandi",
                watched = false,
                cover = "/4DUoPZOHdPuROP4nyEIsPaMIiQl.jpg",
            ),
            Movie(
                title = "Garota Exemplar",
                whoRecommended = "Beatriz",
                watched = false,
                cover = "/x9ezMgOtDPqHatHDvxEG0ILb6Ie.jpg",
            ),
            Movie(
                title = "Zodíaco",
                whoRecommended = "Não lemrbo",
                watched = false,
                cover = "/otQHyCjlkcFfvUDPMdJdBMN7Rg5.jpg",
            ),
            Movie(
                title = "Intocáveis",
                whoRecommended = "Ivanete de Almeida",
                watched = false,
                cover = "/iNAMRPUEEsNIAiZL0RmVmIGjiv1.jpg",
            ),
        )
    }

    private fun getSecondPageMovies(): List<Movie> {
        return listOf(
            Movie(
                title = "O Protetor",
                whoRecommended = "Luis Carlos",
                watched = false,
                cover = "/hPZo3k9iDpAGajh7IDuyquThQd3.jpg",
            ),
            Movie(
                title = "Antes do Amanhecer",
                whoRecommended = "Gabriel Lorandi",
                watched = false,
                cover = "/wih0esrlvzqX5lU8BMvHeJMkhT7.jpg",
            ),
            Movie(
                title = "O Esquadrão Suicida",
                whoRecommended = "Antonio",
                watched = false,
                cover = "/jlGmlFOcfo8n5tURmhC7YVd4Iyy.jpg",
            ),
            Movie(
                title = "Seven - 7 Pecados Mortais",
                whoRecommended = "Maria do Carmo",
                watched = false,
                cover = "/dYjZ27hDw2QFaEIfzbNGwW0IkV9.jpg",
            ),
        )
    }

    private fun getThirdPageMovies(): List<Movie> {
        return listOf(
            Movie(
                title = "O Que Ficou para Trás",
                whoRecommended = "Otavio Uga",
                watched = false,
                cover = "/6aVB2B2GDc4EuNinHgoBgtkuHQz.jpg",
            ),
            Movie(
                title = "X: A Marca da Morte",
                whoRecommended = "Fãs da A24",
                watched = false,
                cover = "/2oXQpm0wfOkIL0jBJABbL5AfMs6.jpg",
            ),
            Movie(
                title = "Twin Peaks (Os Últimos Dias de Laura Palmer)",
                whoRecommended = "Gabriel Lorandi",
                watched = false,
                cover = "/g5G19q0xgkzWEvfcGo1KcL8nQOk.jpg",
            ),
            Movie(
                title = "O Que Fazemos na Sombra",
                whoRecommended = "Lucas Duarte",
                watched = false,
                cover = "/k4AVaOboquMcd3zbFSSgCEbzabz.jpg",
            ),
            Movie(
                title = "Crimes do Futuro",
                whoRecommended = "Dalenogare",
                watched = false,
                cover = "/crzp8X5oRstwcEENdpvJsLUfqRS.jpg",
            ),
        )
    }

}