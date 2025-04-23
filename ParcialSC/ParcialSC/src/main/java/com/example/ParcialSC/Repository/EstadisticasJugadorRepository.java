package com.example.ParcialSC.Repository;

import com.example.ParcialSC.Model.EstadisticasJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstadisticasJugadorRepository extends JpaRepository<EstadisticasJugador, Integer> {

    // 1. Estadísticas por jugador
    @Query(value = "SELECT * FROM estadisticas_jugador WHERE id_jugador = :idJugador", nativeQuery = true)
    List<EstadisticasJugador> findByJugadorId(@Param("idJugador") Integer idJugador);

    // 2. Estadísticas por partido
    @Query(value = "SELECT * FROM estadisticas_jugador WHERE id_partido = :idPartido", nativeQuery = true)
    List<EstadisticasJugador> findByPartidoId(@Param("idPartido") Integer idPartido);

    // 3. Suma total de goles de un jugador
    @Query(value = "SELECT COALESCE(SUM(goles), 0) FROM estadisticas_jugador WHERE id_jugador = :idJugador", nativeQuery = true)
    Integer sumGolesByJugador(@Param("idJugador") Integer idJugador);

    // 4. Top N jugadores por goles
    @Query(value = "SELECT j.*, SUM(ej.goles) AS total_goles " +
            "FROM estadisticas_jugador ej " +
            "INNER JOIN jugador j ON ej.id_jugador = j.id_jugador " +
            "GROUP BY j.id_jugador " +
            "ORDER BY total_goles DESC " +
            "LIMIT :limit",
            nativeQuery = true)
    List<Object[]> findTopJugadoresByGoles(@Param("limit") Integer limit);
}