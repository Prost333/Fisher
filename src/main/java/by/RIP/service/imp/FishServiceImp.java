package by.RIP.service.imp;

import by.RIP.entity.Fish;
import by.RIP.repository.FishDao;
import by.RIP.service.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FishServiceImp implements FishService {

    private final FishDao dao;

    @Override
    public Fish add(Fish fisher) {
        return dao.save(fisher);
    }

    @Override
    public List<Fish> findAll() {
        return dao.findAll();
    }

    @Override
    public Fish findById(Long id) {
        Optional fish = dao.findById(id);
        return (Fish) fish.get();
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}
