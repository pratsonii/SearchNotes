package com.search;

import org.springframework.data.jpa.repository.JpaRepository;

public interface INoteDao extends JpaRepository<Note, Long> {

}
