package by.RIP.service.imp;

import by.RIP.entity.Fisher;
import by.RIP.repository.FisherDao;
import by.RIP.service.FisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FisherImp implements FisherService {

    private final FisherDao fisherDao;

    @Override
    public Fisher add(Fisher fisher) {
        return fisherDao.save(fisher);
    }

    @Override
    public List<Fisher> findAll() {
        return fisherDao.findAll();
    }

    @Override
    public Fisher findById(Long id) {
        return fisherDao.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        fisherDao.deleteById(id);
    }

}
