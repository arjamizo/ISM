/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.pwr;

import java.util.ArrayList;
import javax.ejb.Remote;
import sub_business_tier.FacadeInterface;

/**
 *
 * @author azochniak
 */
@Remote
public interface FacadeRemote extends FacadeInterface {

    @Override
    void update_data(sub_business_tier.entities.TTitle_book[] titles, sub_business_tier.entities.TBook[] books);
    public ArrayList<ArrayList<String>> titles() throws Exception;
;
}
