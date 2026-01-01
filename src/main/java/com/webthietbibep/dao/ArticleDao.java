package com.webthietbibep.dao;

import com.webthietbibep.model.Article;
import com.webthietbibep.model.Banner;
import com.webthietbibep.model.totalArticlebyCate;

import java.util.List;

public class ArticleDao extends  BaseDao{



    public List<Article> getListHotArticle(){
        return  get().withHandle(h ->{
            return h.createQuery("SELECT * FROM articles order by likecount DESC LIMIT 3;").mapToBean(Article.class).list();
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

    public List<totalArticlebyCate> gtotalArticlebyCate(){
        return  get().withHandle(h ->{
            return h.createQuery("SELECT a.category_id , c.category_name, count(a.id) as total FROM articles a join categories c on a.category_id = c.category_id group by  a.category_id , c.category_name").mapToBean(totalArticlebyCate.class).list();
        });
    }


}
