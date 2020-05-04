package com.githubtest.gitrepos.dao;

import com.githubtest.gitrepos.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface ItemsDao extends JpaRepository<Items,Long> {
    Items getByDate(String date);

}
