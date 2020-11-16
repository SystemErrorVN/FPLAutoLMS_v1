/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Administrator
 */
public enum Server {
    HaNoi, HoChiMinh, CanTho, TayNguyen, DaNang;
    @Override
    public String toString() {
        final String site = "http://%s-lms.poly.edu.vn";
        switch (this) {
            case HaNoi:
                return String.format(site, "hn");
            case HoChiMinh:
                return String.format(site, "hcm");
            case CanTho:
            case TayNguyen:
            case DaNang:
                return String.format(site, "dn");
            default:
                return null;
        }
    }
}
