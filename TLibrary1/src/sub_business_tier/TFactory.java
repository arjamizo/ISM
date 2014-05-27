package sub_business_tier;

import java.util.Date;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TBook_period;
import sub_business_tier.entities.TTitle_book;
import sub_business_tier.entities.TTitle_book_on_tape;
import sub_business_tier.entities.TUser;

public class TFactory {

    static final long day = 24 * 60 * 60 * 1000;

    public static Date mdays(String data) {
        int daysAgo = Integer.parseInt(data);
        long initDate = new Date().getTime();
        return new Date(initDate + daysAgo * day);
    }

    static TUser create_user(String client) {
        TUser user = new TUser();
        user.setLogin(client);
        return user;
    }

    public TTitle_book create_title_book(String[] data) {
        TTitle_book title_book;
        TTitle_book_on_tape title_book2;
        switch (Integer.parseInt(data[0])) {
            case 0: {
                title_book = new TTitle_book();
                title_book.setISBN(data[1]);
                return title_book;
            }
            case 1: {
                title_book = new TTitle_book();
                title_book.setAuthor(data[1]);
                title_book.setTitle(data[2]);
                title_book.setISBN(data[3]);
                title_book.setPublisher(data[4]);
                return title_book;

            }
            case 2: {
                title_book2 = new TTitle_book_on_tape();
                title_book2.setISBN(data[1]);
                if(data[2]==null || data[2].equals("")) throw new RuntimeException("You have created book on tape without actor: "+data[2]+".");
                title_book2.setActor(data[2]);
                return title_book2;

            }
            case 3: {
                title_book2 = new TTitle_book_on_tape();
                title_book2.setAuthor(data[1]);
                title_book2.setTitle(data[2]);
                title_book2.setISBN(data[3]);
                title_book2.setPublisher(data[4]);
                if(data[5]==null || data[5].equals("")) throw new RuntimeException("You have created book on tape without actor.");
                title_book2.setActor(data[5]);
                return title_book2;

            }
            default:
                return null;
        }
    }

    public TBook create_book(String[] data) {
        switch (Integer.parseInt(data[0])) {
            case 0:
                TBook book = new TBook();
                book.setNumber(Integer.parseInt(data[1]));
                return book;
            case 1:
                TBook_period book_period = new TBook_period();
                book_period.setNumber(Integer.parseInt(data[1]));
                book_period.startPeriod(data[2]);
                return book_period;

        }
        return null;
    }
}
