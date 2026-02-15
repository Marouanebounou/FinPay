package org.example.services;

import org.example.dao.StatistiqueDAO;
import org.example.model.Statistique;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class StatistiqueService {
    StatistiqueDAO statistiqueDAO = new StatistiqueDAO();

    public void createStatistiaue(Statistique statistique) throws Exception{
        statistiqueDAO.insert(statistique);
    }

    public boolean deleteStatistiaue(int id) throws Exception{
        return statistiqueDAO.deleteById(id);
    }

    public boolean updateStatistique(Statistique statistique) throws Exception{
        return statistiqueDAO.update(statistique);
    }

    public Statistique getStatistiaqueById( int id) throws Exception{
        return statistiqueDAO.findById(id);
    }

    public List<Statistique> getAllStatistique() throws Exception{
        return statistiqueDAO.findAll();
    }

    public BigDecimal totalPaiements() throws Exception {
        return getAllStatistique().stream()
                .map(Statistique::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalCommissions() throws Exception {
        return getAllStatistique().stream()
                .map(Statistique::getTotalComission)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
