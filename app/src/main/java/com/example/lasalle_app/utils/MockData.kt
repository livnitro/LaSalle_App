package com.example.lasalle_app.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.lasalle_app.models.Community
import com.example.lasalle_app.models.Major
import com.example.lasalle_app.models.News
import com.example.lasalle_app.models.Payment
import com.example.lasalle_app.models.Subject
import com.example.lasalle_app.models.User
import java.time.LocalDate

val newsList = listOf(
    News(
        id = 1,
        title = "Elección del Consejo Estudiantil",
        description = "El Comité Electoral de La Universidad La Salle Bajío invita a los estudiantes inscritos a Licenciatura a participar en la elección del Consejo General Estudiantil",
        image = "https://www.lasallebajio.edu.mx/comunidad/images/popup/popup_consejos_gral_almns_24.jpg"
    ),
    News(
        id = 2,
        title = "Recibe MIM Visita",
        description = "La Universidad La Salle Bajío recibió la visita de la Red Universitaria de las Arte",
        image = "https://www.lasallebajio.edu.mx/noticias/images/4761_1.jpg"
    ),
    News(
        id = 3,
        title = "DJs Chinos Dan Un Gran Show",
        description = "Desde China hasta la Universidad La Salle Bajío llegó un espectáculo de música electrónica que puso a bailar a la Comunidad Universitaria.",
        image = "https://www.lasallebajio.edu.mx/noticias/images/4759_1.jpg"
    )
)

val communities = listOf(
    Community(
        1,
        "https://www.lasallebajio.edu.mx/comunidad/images/tile_documentos_inspiradores.jpg"
    ),
    Community(2, "https://www.lasallebajio.edu.mx/comunidad/images/tile_boletin.jpg"),
    Community(3, "https://www.lasallebajio.edu.mx/comunidad/images/tile_cat_souv_22.jpg  "),
    Community(4, "https://www.lasallebajio.edu.mx/comunidad/images/tile_tramites.jpg"),
    Community(5, "https://www.lasallebajio.edu.mx/comunidad/images/tile_blog.jpg"),
)

val subjects = listOf(
    Subject(1,"Taller de Desarrollo Móvil para Plataforma Android",10.0,0.0,0.0),
    Subject(2,"Conmutacion de Redes en Área Local",10.0,0.0,0.0),
    Subject(3,"Administración de Bases de Datos",10.0,0.0,0.0),
    Subject(4,"Cálculo Integral",10.0,0.0,0.0),
    Subject(5,"Desarrollo Multiplataforma en Windows",10.0,0.0,0.0),
    Subject(6,"Posthumanismo",10.0,0.0,0.0),
)

val majors = listOf(
    Major("Ingeniería en Software y Sistemas Computacionales", subjects)
)

val payments = listOf(
    Payment("Agosto",10000.00, true),
    Payment("Septiembre",10000.00, true),
    Payment("Octubre",10000.00, true),
    Payment("Noviembre",10000.00, false)
)

@RequiresApi(Build.VERSION_CODES.O)
val users = listOf(
    User(76907,"Livvy","Livers","Liv", LocalDate.of(2004, 2, 11), "baldnitrome2@gmail.com","Danganronpa", "https://cdn.discordapp.com/attachments/1123450350255480943/1298538564492988447/20240421_232620.jpg?ex=6719edc7&is=67189c47&hm=d9cb800b346457cf283b45dcd1741a4695aaf25eb5521907f4bd8d4c128c0fcb&", majors[0], 5, payments = payments)
)