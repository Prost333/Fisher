package by.RIP.service;

import by.RIP.entity.Fisher;


import java.util.List;

public interface FisherService {
    Fisher add(Fisher fisher);
    List<Fisher> findAll();
    Fisher findById(Long id);
    void delete(Long id);
}
