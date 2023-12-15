package by.RIP.service;

import by.RIP.entity.Fish;
import by.RIP.entity.Fisher;

import java.util.List;

public interface FishService {
    Fish add(Fish fisher);
    List<Fish> findAll();
    Fish findById(Long id);
    void delete(Long id);
}
