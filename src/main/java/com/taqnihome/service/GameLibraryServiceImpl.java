package com.taqnihome.service;

import javax.transaction.Transactional;

import com.taqnihome.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taqnihome.dao.GameLibraryDao;
import com.taqnihome.domain.GameLibrary;

import java.util.List;

@Service
@Transactional
public class GameLibraryServiceImpl extends
        GenericServiceImpl<String, GameLibrary> implements GameLibraryService {

    private GameLibraryDao gameLibraryDao;

    public GameLibraryServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public GameLibraryServiceImpl(GameLibraryDao gameLibraryDao) {
        super(gameLibraryDao);
        this.gameLibraryDao = gameLibraryDao;
    }

    @Override
    public List<GameLibrary> findByIsApprovedFalseAndApprovedDateNotNull() {
        return gameLibraryDao.findByIsApprovedFalseAndApprovedDateNotNull();
    }

    @Override
    public GameLibrary findByPackageName(String packageName) {
        return gameLibraryDao.findByPackageName(packageName);
    }

    @Override
    public List<GameLibrary> findByIsApprovedFalseAndApprovedDateIsNull() {
        return gameLibraryDao.findByIsApprovedFalseAndApprovedDateIsNull();
    }

    @Override
    public GameLibrary findByGameId(String gameId) {
        // TODO Auto-generated method stub
        return gameLibraryDao.findByGameId(gameId);
    }


    @Override
    public List<GameLibrary> findByAgeRatingLessThan(int ageRating) {
        return gameLibraryDao.findByAgeRatingLessThan(ageRating);
    }

    @Override
    public List<GameLibrary> findByIsApprovedFalse() {
        return gameLibraryDao.findByIsApprovedFalse();
    }

    @Override
    public List<GameLibrary> findByPackageNameInAndIsApprovedTrue(List<String> packageName) {
        return gameLibraryDao.findByPackageNameInAndIsApprovedTrue(packageName);
    }

    @Override
    public List<GameLibrary> findByAgeRatingNotNullAndAgeRatingGreaterThan(int ageRating) {
        return gameLibraryDao.findByAgeRatingNotNullAndAgeRatingGreaterThan(ageRating);
    }
}
