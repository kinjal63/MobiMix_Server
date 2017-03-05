package com.taqnihome.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taqnihome.domain.GameCategory;

public interface GameCategoryDao  extends JpaRepository<GameCategory, String>{

}
