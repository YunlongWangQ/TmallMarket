package com.wp.TmallMarket.dao;

import com.wp.TmallMarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNameAndPassword(String name, String password);

    @Override
    <S extends User> List<S> saveAll(Iterable<S> entities);
}
