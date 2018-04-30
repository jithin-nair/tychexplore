/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.scheduler.model;

/**
 *
 * @author Jithin
 */
public class BlockDetails {

    private String height;

    private String hash;

    private String foundDate;

    public BlockDetails() {
    }

    public BlockDetails(String height, String hash, String foundDate) {
        this.height = height;
        this.hash = hash;
        this.foundDate = foundDate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }

}
