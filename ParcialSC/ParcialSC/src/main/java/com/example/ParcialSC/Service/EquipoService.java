package com.example.ParcialSC.Service;

import com.example.ParcialSC.Model.Equipo;
import com.example.ParcialSC.Model.Jugador;
import com.example.ParcialSC.Repository.EquipoRepository;
import com.example.ParcialSC.Repository.JugadorRepository;
import com.example.ParcialSC.Repository.PartidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    // CRUD Básico
    @Transactional
    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    @Transactional
    public Equipo getEquipoById(Integer id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + id));
    }

    @Transactional
    public Equipo createEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Transactional
    public Equipo updateEquipo(Integer id, Equipo equipoDetails) {
        Equipo equipo = getEquipoById(id);
        equipo.setNombre(equipoDetails.getNombre());
        equipo.setCiudad(equipoDetails.getCiudad());
        equipo.setFundacion(equipoDetails.getFundacion());
        return equipoRepository.save(equipo);
    }

    @Transactional
    public void deleteEquipo(Integer id) {
        Equipo equipo = getEquipoById(id);
        equipoRepository.delete(equipo);
    }

    // Consultas Nativas del Parcial
    @Transactional
    public List<Jugador> getJugadoresByEquipoId(Integer idEquipo) {
        return jugadorRepository.findJugadoresByEquipo(idEquipo);
    }

    @Transactional
    public Integer getTotalGolesEquipo(Integer idEquipo) {
        return partidoRepository.getTotalGolesEquipo(idEquipo);
    }

    @Transactional
    public List<Object[]> getResultadosPartidos() {
        return partidoRepository.getResultadosPartidosConNombres();
    }
}