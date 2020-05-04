package com.githubtest.gitrepos.dao;


import com.githubtest.gitrepos.model.gitRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepositoryDao extends JpaRepository<gitRepo,Long> {

}
