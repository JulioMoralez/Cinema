package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.model.Hall;
import pack.model.Schedule;
import pack.repository.HallRepo;

import java.util.List;

@Service
public class HallService {

    @Autowired
    private HallRepo hallRepo;

    public Hall findById(Integer id){
        return hallRepo.findById(id).get();
    }

    public List<Hall> findAll() {
        return hallRepo.findAll();
    }

    public Hall save(Hall film){
        return hallRepo.save(film);
    }

    public Hall delete(Integer id) {
        if (hallRepo.findById(id).isPresent()) {
            try {
                hallRepo.deleteById(id);
            }
            catch (Exception e){
                return new Hall(-1);
            }
        }
        return new Hall(id);
    }
}
