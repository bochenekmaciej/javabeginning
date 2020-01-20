package lang;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "languages" )
public class Lang {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;
    private String welcomeMsg;
    private String code;
    private String deleteMsg;

    /**
     * Hibernate (JPA) needs it
     */
    @SuppressWarnings("unused")
    Lang(){}

    public Lang(Integer id, String welcomeMsg, String code, String deleteMsg) {
        this.id = id;
        this.welcomeMsg = welcomeMsg;
        this.code = code;
        this.deleteMsg = deleteMsg;
    }

    public Integer getId() {
        return id;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeleteMsg() {
        return deleteMsg;
    }

    public void setDeleteMsg(String deleteMsg) {
        this.deleteMsg = deleteMsg;
    }
}
