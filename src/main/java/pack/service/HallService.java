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

    public List<Hall> findAll(){
        return hallRepo.findAll();
    }
}
