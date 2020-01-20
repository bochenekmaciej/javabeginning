package lang;

import com.todolist.maciek.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class LangRepository {


    List<Lang> findAll(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Lang", Lang.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    public Optional<Lang> findById(Integer id){
       var session = HibernateUtil.getSessionFactory().openSession();
       var transaction = session.beginTransaction();
       var result = session.get(Lang.class, id);
       transaction.commit();
       session.close();
       return Optional.ofNullable(result);
        //return languages.stream().filter(l -> l.getId().equals(id)).findFirst();
    }
    /*private List<Lang> languages;
    LangRepository(){
        languages = new ArrayList<>();
        languages.add(new Lang(1, "Hello", "en"));
        languages.add(new Lang(2, "Chej", "pl"));
    }*/

}
