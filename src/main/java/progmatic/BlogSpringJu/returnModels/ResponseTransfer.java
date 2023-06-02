package progmatic.BlogSpringJu.returnModels;

import org.springframework.http.HttpStatus;

public class ResponseTransfer<T> {

    private boolean success;
    private String text;
    private HttpStatus statusCode;

    private T object;

    public ResponseTransfer() {
    }

    public ResponseTransfer(boolean success, String text, HttpStatus statusCode) {
        this.success = success;
        this.text = text;
        this.statusCode = statusCode;
    }

    public ResponseTransfer(boolean success, String text, HttpStatus statusCode, T object) {
        this.success = success;
        this.text = text;
        this.statusCode = statusCode;
        this.object = object;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
