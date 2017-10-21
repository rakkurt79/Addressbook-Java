/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adressbook;

import com.mycompany.adressbook.Database_islemleri.Database_islemleri;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Rakkurt79
 */
@ManagedBean(name = "managedbean")
@RequestScoped
public class Managedbean {
    
   
    private int id;
    private String ad ;
    private String soyad;
    private String mail;
    private int telefon;
    private String adres;
    private String ad_soyad;
    private int edit_id;
    private String edit_ad;
    private String edit_soyad;
    private String edit_mail;
    private String edit_adres;

    public static int sayac=0;

    public String getEdit_ad() {
        return edit_ad;
    }

    public void setEdit_ad(String edit_ad) {
        this.edit_ad = edit_ad;
    }

    public String getEdit_soyad() {
        return edit_soyad;
    }

    public void setEdit_soyad(String edit_soyad) {
        this.edit_soyad = edit_soyad;
    }

    public String getEdit_mail() {
        return edit_mail;
    }

    public void setEdit_mail(String edit_mail) {
        this.edit_mail = edit_mail;
    }

    public String getEdit_adres() {
        return edit_adres;
    }

    public void setEdit_adres(String edit_adres) {
        this.edit_adres = edit_adres;
    }
    

    public int getEdit_id() {
        return edit_id;
    }

    public void setEdit_id(int edit_id) {
        this.edit_id = edit_id;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getAd_soyad() {
        return ad_soyad;
    }

    public void setAd_soyad(String ad_soyad) {
        this.ad_soyad = ad_soyad;
    }
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
    
    
    
    //Tablodan tüm değerleri çekme
    public List verilerigetir()
    {
        
        if(sayac==0){
    return Database_islemleri.veri_getir();
        }else if(getAd_soyad() !=null)
        {
        return Database_islemleri.adsoyadbul(getAd_soyad());
        }
        else return null;
        
    }
   
    //Yeni kayıt 
    public String yenikayit(Managedbean adresbean)
    { 
     return Database_islemleri.veriekle(adresbean.getAd(),adresbean.getSoyad(),adresbean.getMail(),adresbean.getAdres());
    }
    
    //Kayıt silme
    public String kayitsil(int id)
    {        
     return Database_islemleri.verisil(id);    
    }
    
    //ad-soyad ile arama yapılan metod
    
    public int adsoyad_ara(){
      
      sayac +=1;
      return sayac;
    }
    
    public void detaysayfasi(){
    
        edit_id =Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sendid"));
        edit_ad=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sendname");
        edit_soyad=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sendlastname");
        edit_mail=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sendmail");
        edit_adres=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sendaddress");
        
    }
    // Güncelleme Metodu
    public String guncelleme(Managedbean gun){
    return Database_islemleri.veriguncelle(gun.getEdit_id(),
            gun.getEdit_ad(),gun.getEdit_soyad(),gun.getEdit_mail(),gun.getEdit_adres());
    }
    
    //Telefon verilerini ekleyen method
    public String telefonekleme(Managedbean telefonbean){
    
    return Database_islemleri.telefonekle(telefonbean.getEdit_id(),telefonbean.getTelefon());
    }
    
    
    //Telefon verilerini getiren method
    public List telefonverilerigetir(Managedbean telbean){
    
    return Database_islemleri.telefonverileri(telbean.getEdit_id());
    
    }
    //Telefon silme methodu
    
    public String telefonsil(int id){
    return Database_islemleri.telefonsilme(id);
    }
    
}
