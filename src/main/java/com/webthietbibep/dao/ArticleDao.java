package com.webthietbibep.dao;

import com.webthietbibep.model.Article;
import com.webthietbibep.model.Banner;

import java.util.List;

public class ArticleDao extends  BaseDao{



    public List<Article> getListHotArticle(){
        return  get().withHandle(h ->{
            return h.createQuery("SELECT * FROM articles order by likecount DESC LIMIT 3;").mapToBean(Article.class).list();
        });
    }
    public List<Article> getNewArticle(){
        return  get().withHandle(h ->{
            return h.createQuery("SELECT * FROM articles order by create_date DESC LIMIT 3;").mapToBean(Article.class).list();
        });
    }
    public List<Article> getFilterArticle(String f){
        return  get().withHandle(h ->{

            String query = "SELECT * FROM articles WHERE 1=1 ";
            if(f.equals("new")){query += " " + "ORDER BY create_date DESC ";}
            else if(f.equals("population")) {query += " " + "ORDER BY likecount DESC";}
            else {query += " " + "ORDER BY title ASC";}

            return h.createQuery(query).mapToBean(Article.class).list();
        });
    }
}
