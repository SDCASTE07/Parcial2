package com.example.ParcialSC.Repository;

import com.example.ParcialSC.Model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {

    // Consulta nativa para obtener entrenadores por equipo
    @Query(value = "SELECT * FROM entrenador WHERE id_equipo = :idEquipo",
            nativeQuery = true)
    List<Entrenador> findByEquipoId(@Param("idEquipo") Integer idEquipo);

    // Consulta nativa para filtrar por especialidad (ejemplo adicional)
    @Query(value = "SELECT * FROM entrenador WHERE especialidad ILIKE %:especialidad%",
            nativeQuery = true)
    List<Entrenador> findByEspecialidad(@Param("especialidad") String especialidad);
}