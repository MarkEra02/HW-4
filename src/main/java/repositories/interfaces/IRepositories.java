package repositories.interfaces;

import entities.Med;

import java.util.List;

public interface IRepositories {
    List<Med> searchMedByName(String name);
    Med getMedById(int id);
    boolean addMed(Med Med);
    boolean removeMedById(int id);
}
