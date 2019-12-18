/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood;

/**
 *
 * @author Ian Mubangizi
 */
public class Helper {

    public static boolean isNotEmpty(String... text) {
        for (String s : text) {
            if (!isNotEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(String text) {
        return !text.isEmpty();
    }
}
