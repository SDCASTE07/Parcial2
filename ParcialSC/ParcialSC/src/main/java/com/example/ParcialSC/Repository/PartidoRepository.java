package com.example.ParcialSC.Repository;

import com.example.ParcialSC.Model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

    // 3. Obtener resultados de partidos con nombres de equipos
    @Query(value = "SELECT e.nombre AS local, ev.nombre AS visitante, p.goles_local, p.goles_visita " +
            "FROM partido p " +
            "INNER JOIN equipo e ON p.equipo_local = e.id_equipo " +
            "INNER JOIN equipo ev ON p.equipo_visita = ev.id_equipo",
            nativeQuery = true)
    List<Object[]> getResultadosPartidosConNombres();

    // 4. Obtener total de goles de un equipo en todos sus partidos
    @Query(value = "SELECT SUM(goles) FROM (" +
            "  SELECT goles_local AS goles FROM partido WHERE equipo_local = :idEquipo " +
            "  UNION ALL " +
            "  SELECT goles_visita AS goles FROM partido WHERE equipo_visita = :idEquipo" +
            ") AS total_goles",
            nativeQuery = true)
    Integer getTotalGolesEquipo(@Param("idEquipo") Integer idEquipo);
}