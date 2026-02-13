package org.example.services;

import org.example.dao.PaiementDao;
import org.example.dao.StatistiqueDAO;
import org.example.model.Paiement;
import org.example.model.Statistique;

import java.math.BigDecimal;
import java.util.List;

public class PaiementService {
    private final PaiementDao paiementDao = new PaiementDao();
    private final StatistiqueDAO statistiqueDAO = new StatistiqueDAO();

    public void effectuerPaiment(Paiement paiement)throws Exception{
        paiementDao.insert(paiement);

        Statistique statistique = new Statistique(paiement.getBalance() , paiement.getBalance().multiply(new BigDecimal("0.002")) , paiement.getId() , paiement.getDate());
        statistiqueDAO.insert(statistique);
    }

    public boolean deletePaiment(int id) throws Exception{
        return paiementDao.deleteById(id);
    }

    public Paiement getPaimentById(int id) throws Exception{
        return paiementDao.findById(id);
    }

    public List<Paiement> getAllPaiments() throws Exception{
        return paiementDao.findAll();
    }
}
