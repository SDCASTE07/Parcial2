package com.example.ParcialSC.Repository;

import com.example.ParcialSC.Model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    // Consulta nativa: Equipos por ciudad
    @Query(value = "SELECT * FROM equipo WHERE ciudad ILIKE %:ciudad%",
            nativeQuery = true)
    List<Equipo> findByCiudad(@Param("ciudad") String ciudad);

    // Consulta nativa: Total de goles históricos (local + visita)
    @Query(value = "SELECT (COALESCE(SUM(goles_local), 0) + COALESCE(SUM(goles_visita), 0)) " +
            "FROM partido " +
            "WHERE equipo_local = :idEquipo OR equipo_visita = :idEquipo",
            nativeQuery = true)
    Integer getTotalGolesHistoricos(@Param("idEquipo") Integer idEquipo);

    // Consulta nativa: Partidos jugados por un equipo (como local o visitante)
    @Query(value = "SELECT * FROM partido " +
            "WHERE equipo_local = :idEquipo OR equipo_visita = :idEquipo",
            nativeQuery = true)
    List<Object[]> getPartidosByEquipoId(@Param("idEquipo") Integer idEquipo);
}