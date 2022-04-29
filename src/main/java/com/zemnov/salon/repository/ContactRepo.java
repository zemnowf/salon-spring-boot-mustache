package com.zemnov.salon.repository;

import com.zemnov.salon.model.Contact;
import com.zemnov.salon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact, Long> {
    List<Contact> findByClient(User client);
}
