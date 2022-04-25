package seunghee.kakaoChatbot;

public class UebFaqVO {

    private String FAQ_NO;
    private String Category1;
    private String Category2;
    private String Category3;
    private String Category4;
    private String Category5;
    private String Question;
    private String Answer;
    private String Landing_URL;
    private String Landing_URL_Button_Name;
    private String Manage;
    private String View;
    private String Web_Answer;

    public UebFaqVO() {
    }

    public UebFaqVO(String FAQ_NO, String category1, String category2, String category3, String category4, String category5, String question, String answer, String landing_URL, String landing_URL_Button_Name, String manage, String view, String web_Answer) {
        this.FAQ_NO = FAQ_NO;
        Category1 = category1;
        Category2 = category2;
        Category3 = category3;
        Category4 = category4;
        Category5 = category5;
        Question = question;
        Answer = answer;
        Landing_URL = landing_URL;
        Landing_URL_Button_Name = landing_URL_Button_Name;
        Manage = manage;
        View = view;
        Web_Answer = web_Answer;
    }

    public String getFAQ_NO() {
        return FAQ_NO;
    }

    public void setFAQ_NO(String FAQ_NO) {
        this.FAQ_NO = FAQ_NO;
    }

    public String getCategory1() {
        return Category1;
    }

    public void setCategory1(String category1) {
        Category1 = category1;
    }

    public String getCategory2() {
        return Category2;
    }

    public void setCategory2(String category2) {
        Category2 = category2;
    }

    public String getCategory3() {
        return Category3;
    }

    public void setCategory3(String category3) {
        Category3 = category3;
    }

    public String getCategory4() {
        return Category4;
    }

    public void setCategory4(String category4) {
        Category4 = category4;
    }

    public String getCategory5() {
        return Category5;
    }

    public void setCategory5(String category5) {
        Category5 = category5;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getLanding_URL() {
        return Landing_URL;
    }

    public void setLanding_URL(String landing_URL) {
        Landing_URL = landing_URL;
    }

    public String getLanding_URL_Button_Name() {
        return Landing_URL_Button_Name;
    }

    public void setLanding_URL_Button_Name(String landing_URL_Button_Name) {
        Landing_URL_Button_Name = landing_URL_Button_Name;
    }

    public String getManage() {
        return Manage;
    }

    public void setManage(String manage) {
        Manage = manage;
    }

    public String getView() {
        return View;
    }

    public void setView(String view) {
        View = view;
    }

    public String getWeb_Answer() {
        return Web_Answer;
    }

    public void setWeb_Answer(String web_Answer) {
        Web_Answer = web_Answer;
    }
}
