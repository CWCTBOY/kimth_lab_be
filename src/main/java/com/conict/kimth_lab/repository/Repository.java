package com.conict.kimth_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository<E> extends JpaRepository<E, Long> {
}
