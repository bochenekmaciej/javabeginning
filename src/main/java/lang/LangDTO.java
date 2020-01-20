package lang;

class LangDTO {
    private Integer id;
    private String code;

    public String getDeleteMsg() {
        return deleteMsg;
    }

    public void setDeleteMsg(String deleteMsg) {
        this.deleteMsg = deleteMsg;
    }

    private String deleteMsg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    LangDTO(Lang lang){
        this.id = lang.getId();
        this.code = lang.getCode();
        this.deleteMsg = lang.getDeleteMsg();
    }
}
