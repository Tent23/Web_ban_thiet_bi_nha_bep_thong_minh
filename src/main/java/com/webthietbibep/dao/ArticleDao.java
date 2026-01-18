package com.webthietbibep.dao;

import com.webthietbibep.model.Article;
import com.webthietbibep.model.Banner;

import java.util.List;

public class ArticleDao extends  BaseDao{



    public List<Article> getListHotArticle(){
        return  get().withHandle(h ->{
            return h.createQuery("SELECT * FROM articles where is_active = 1  order by likecount DESC LIMIT 3;").mapToBean(Article.class).list();
        });
    }
    public List<Article> getNewArticle(){
        return  get().withHandle(h ->{
            return h.createQuery("SELECT * FROM articles where is_active = 1 order by create_date DESC LIMIT 3;").mapToBean(Article.class).list();
        });
    }
    public List<Article> getFilterArticle(String f){
        return  get().withHandle(h ->{

            String query = "SELECT * FROM articles WHERE is_active = 1 ";
            if("new".equals(f)){query += " " + "ORDER BY create_date DESC ";}
            else if("population".equals(f)) {query += " " + "ORDER BY likecount DESC";}
            else {query += " " + "ORDER BY title ASC";}

            return h.createQuery(query).mapToBean(Article.class).list();
        });
    }

    public List<Article> getFilterArticleAdmin(String filter,String search){
        return  get().withHandle(h ->{

            String query = "SELECT * FROM articles WHERE 1=1 AND ";
            if("new".equals(filter)){query += " " + " title like '%" +search+ "%' " +"ORDER BY create_date DESC ";}
            else if("old".equals(filter)) {query += " " + " title like '%" +search+ "%' " +"ORDER BY create_date ASC";}
            else if(filter.equals("type")){query += " " + "tip like '%" +search+ "%' " +"ORDER BY create_date DESC ";}
            else {query += " " + "title like '%" +search+ "%' " + "ORDER BY title ASC";}

            return h.createQuery(query).mapToBean(Article.class).list();
        });
    }
}
