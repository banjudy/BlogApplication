package progmatic.BlogSpringJu.models;

public class BlogTemplate {

    private String templateName;
    private String category;
    private String color;
    private byte[] backgroundIMG;

    public BlogTemplate(String templateName, String category, String color, byte[] backgroundIMG) {
        this.templateName = templateName;
        this.category = category;
        this.color = color;
        this.backgroundIMG = backgroundIMG;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getCategory() {
        return category;
    }

    public String getColor() {
        return color;
    }

    public byte[] getBackgroundIMG() {
        return backgroundIMG;
    }
}
