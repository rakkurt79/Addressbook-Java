/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adressbook.Database_islemleri;

import com.mycompany.adressbook.Telephone;
import com.mycompany.adressbook.Users;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author Rakkurt79
 */
public class Database_islemleri {
    
    private static final String PERSISTENCE_UNIT_NAME ="myaddressbook";
    private static EntityManager em =Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    private static EntityTransaction trans = em.getTransaction();

   
    //Veri çekme Methodu
    public static List veri_getir()
    {
        
    Query myquery = em.createNamedQuery("Users.findAll");
    List mylist =myquery.getResultList();
    
    return mylist;
    }
    
    // Veri ekleme Methodu
    public static String veriekle(String ad , String soyad , String mail ,String adres)
    {
    
    trans.begin();
    Users ekleme =new Users();
    try {
    ekleme.setName(ad);
    ekleme.setLastname(soyad);
    ekleme.setMail(mail);
    ekleme.setAddress(adres);
    em.persist(ekleme);
    trans.commit();   
    return "detay.xhtml?faces-redirect=true";
    
    }catch(Exception ex)
    {
        trans.rollback();
        return "Hata oluştu:"+ex;
     
    }
    }
    
    //Veri Silme Metodu
    public static String verisil(int id){
                
        Users silme =em.find(Users.class, id);
       try{
         trans.begin();
          em.remove(silme);
          trans.commit();
          return "";
       }catch(Exception ex){
       trans.rollback();
       return "Hata oluştu:"+ex;
       }
        
    }
    
    //Ad-soyad Arama 
    public static List adsoyadbul(String gelen){
        
        String will_split=gelen;
       String[] parts = will_split.split(" ");    
        trans.begin();
        Query query = em.createNamedQuery("Users.findnameandlastname");
        query.setParameter("name", parts[0]); 
        query.setParameter("lastname", parts[1]);
        List results=query.getResultList();
        trans.commit(); 
       
        return results;
        
        }
            
        
    

    public static String veriguncelle(int id , String ad , String soyad ,String mail , String adres){
        trans.begin();
     Users guncelleme =em.find(Users.class, id);
     
     try{
        Query update=em.createNamedQuery("Users.update",Users.class);
         update.setParameter("id", id);
         update.setParameter("name", ad);
         update.setParameter("lastname", soyad);
         update.setParameter("mail", mail);
         update.setParameter("address", adres);
         update.executeUpdate();
         trans.commit();     
     }catch(Exception ex){
     System.err.printf("Hata oluştu :"+ex);
     trans.rollback();
     }
    
    return "";
    }
    
    
    //Telefon verisi ekleme methodu
    public static String telefonekle(int userid , int telefon )
    {
        trans.begin();
        Telephone addtel = new Telephone();
        Users id_con = new Users(userid);
        String tel =Integer.toString(telefon);
        try{
            addtel.setUserId(id_con);
            addtel.setTelephoneNumber(telefon);
            em.persist(addtel);
            trans.commit();
            return "Telefon Eklendi";
        
        }catch(Exception ex){
             trans.rollback();
            return "Hata oluştu:"+ex;
        
        }
              
    }
    
    //  Telefon verisi çekme methodu 
    public static List telefonverileri(int user_id){
          Users id_ =new Users(user_id);
           Query list_query =em.createNamedQuery("Telephone.All");
          list_query.setParameter("userId", id_);
           List list_tel = list_query.getResultList();
           
          return list_tel;
        }
    
    //Telefon Silme methodu
    public static String telefonsilme(int id){
      
     Telephone sil =em.find(Telephone.class,id);
        try{
         trans.begin();
          em.remove(sil);
          trans.commit();
          return "Kayıt Silindi";
       }catch(Exception ex){
       trans.rollback();
       return "Hata oluştu:"+ex;
       }

    } 
    
            
     
    

    
    
}
