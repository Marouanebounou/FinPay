package org.example.services;

import org.example.dao.PrestataireDao;
import org.example.model.Prestataire;

import java.util.List;

public class PrestataireService {
    private final PrestataireDao prestataireDao  = new PrestataireDao();

    public void createPrestataire(Prestataire p) throws Exception{
        prestataireDao.insert(p);
    }

    public void updatePrestataire(Prestataire p) throws Exception{
        prestataireDao.update(p);
    }

    public Prestataire findPrestatairById(int id) throws Exception{
       return prestataireDao.findById(id);
    }

    public List<Prestataire> findAll() throws Exception {
        return prestataireDao.findAll();
    }

    public boolean deleteById(int id) throws Exception{
        return prestataireDao.deleteById(id);
    }
}
