/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sub_business_tier.entities;

/**
 *  Note fluent interface in setters. 
 * @author artur
 */
public class TLend {
    
    private TUser user;
    private TBook book;

    public TBook getBook() {
        return book;
    }

    public TLend setBook(TBook book) {
        this.book = book;
        return this;
    }

    public TUser getUser() {
        return user;
    }

    public TLend setUser(TUser user) {
        this.user = user;
        return this;
    }

}
