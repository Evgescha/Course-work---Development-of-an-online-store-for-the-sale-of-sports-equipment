package com.hescha.sportstore.service;

import com.hescha.sportstore.entity.Status;
import com.hescha.sportstore.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService extends CrudImpl<Status> {

    public StatusRepository repository;

    @Autowired
    public StatusService(StatusRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
