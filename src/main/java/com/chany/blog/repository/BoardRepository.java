package com.chany.blog.repository;

import com.chany.blog.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("SELECT b FROM Board b " +
            "JOIN FETCH b.user u " +
            "LEFT JOIN FETCH b.replies r " +
            "LEFT JOIN FETCH r.user " +
            "WHERE b.id = :id")
    Optional<Board> findWithReplies(@Param("id") Integer id);

    @EntityGraph(attributePaths = "user")
    @Query("SELECT b FROM Board b")
    Page<Board> findAllWithUser(Pageable pageable);
}
