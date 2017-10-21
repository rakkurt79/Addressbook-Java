package com.mycompany.adressbook;

import com.mycompany.adressbook.Telephone;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-22T00:28:29")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> address;
    public static volatile SingularAttribute<Users, String> mail;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile CollectionAttribute<Users, Telephone> telephoneCollection;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> lastname;

}