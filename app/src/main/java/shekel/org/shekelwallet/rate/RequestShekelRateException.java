package shekel.org.shekelwallet.rate;

/**
 * Created by kaali on 7/5/17.
 */
public class RequestShekelRateException extends Exception {
    public RequestShekelRateException(String message) {
        super(message);
    }

    public RequestShekelRateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestShekelRateException(Exception e) {
        super(e);
    }
}
