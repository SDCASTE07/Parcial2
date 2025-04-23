package com.example.ParcialSC.Repository;

import com.example.ParcialSC.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {

    // 1. Obtener todos los jugadores de un equipo específico
    @Query(value = "SELECT * FROM jugador WHERE id_equipo = :idEquipo", nativeQuery = true)
    List<Jugador> findJugadoresByEquipo(@Param("idEquipo") Integer idEquipo);

    // 2. Obtener jugadores con más de X goles
    @Query(value = "SELECT j.* FROM jugador j " +
            "INNER JOIN estadisticas_jugador ej ON j.id_jugador = ej.id_jugador " +
            "WHERE ej.goles > :minGoles", nativeQuery = true)
    List<Jugador> findJugadoresConMasGolesQue(@Param("minGoles") Integer minGoles);
}