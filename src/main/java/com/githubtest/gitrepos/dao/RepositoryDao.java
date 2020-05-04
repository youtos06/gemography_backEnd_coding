package com.githubtest.gitrepos.dao;


import com.githubtest.gitrepos.model.GitRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryDao extends JpaRepository<GitRepo,Long> {

}
