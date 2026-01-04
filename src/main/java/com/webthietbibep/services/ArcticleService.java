package com.webthietbibep.services;

import com.webthietbibep.dao.ArticleDao;
import com.webthietbibep.model.Article;

import java.util.List;

public class ArcticleService {
    ArticleDao adao = new ArticleDao();


    public List<Article>  getListHotArticle() {
        return adao.getListHotArticle();
    }
    public List<Article> getFilterArticle(String f){
        return adao.getFilterArticle(f);
    }
    public List<Article> getNewArticle(){
        return adao.getNewArticle();
    }
}
